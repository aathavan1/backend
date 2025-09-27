package com.aathavan.backend;

import com.aathavan.backend.common.ApplicationConstant;
import com.aathavan.backend.common.Secutity;
import com.aathavan.backend.config.ConnectionConfig;
import com.aathavan.backend.model.ServerCredentials;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        try {
            readServerFile();
            SpringApplication.run(BackendApplication.class, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void readServerFile() throws Exception {

        CodeSource classLoader = BackendApplication.class.getProtectionDomain().getCodeSource();

        File filePath = new File(classLoader.getLocation().getFile());

        File file = new File(filePath.getParent() + "/" + "CompanyDetail.txt");

        if (!file.exists()) {
            throw new Exception("File not found...!");
        } else {
            List<String> fileDetail = Files.readAllLines(Paths.get(file.toURI()));

            ServerCredentials serverCredentials = new ServerCredentials();
            serverCredentials.setServerip(fileDetail.getFirst());
            serverCredentials.setPortno(Secutity.decrypter(fileDetail.get(1)));
            serverCredentials.setUsername(Secutity.decrypter(fileDetail.get(2)));
            serverCredentials.setPassword(Secutity.decrypter(fileDetail.get(3)));
            serverCredentials.setCompanycode(Secutity.decrypter(fileDetail.get(4)));
            serverCredentials.setCompanyname(Secutity.decrypter(fileDetail.get(5)));
            serverCredentials.setMasterdb(serverCredentials.getCompanycode() + "amaster");

            String query = "select * from filemain ";
            ConnectionConfig connectionConfig = new ConnectionConfig();
            List<Map<String, Object>> lstMap = new JdbcTemplate(connectionConfig.getDbDataSource(serverCredentials)).queryForList(query);
            if (lstMap.isEmpty()) throw new Exception("Please Run The DbInstall");
            serverCredentials.setTrandb(String.valueOf(lstMap.getFirst().get("trandbname")));

            ApplicationConstant.setServerCredentials(serverCredentials);
        }
    }
}

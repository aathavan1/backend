package com.aathavan.backend.config;

import com.aathavan.backend.common.ApplicationCommon;
import com.aathavan.backend.common.ApplicationConstant;
import com.aathavan.backend.model.ServerCredentials;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ConnectionConfig {
    private DataSource getDataSource(String serverIp, String portNo, String userName, String password, String dbName) throws Exception {
        HikariDataSource hikariDataSource = null;
        try {
            hikariDataSource = new HikariDataSource();
            hikariDataSource.setDriverClassName(ApplicationConstant.DRIVER_CLASS_NAME);
            hikariDataSource.setJdbcUrl(ApplicationCommon.prepareConnectionString(serverIp, portNo) + dbName);
            hikariDataSource.setUsername(userName);
            hikariDataSource.setPassword(password);
            hikariDataSource.getConnection();
            return hikariDataSource;
        } catch (Exception e) {
            throw new Exception("Invalid Server Credentials");
        }
    }


    public DataSource getDbDataSource(ServerCredentials serverCredentials) throws Exception {
        return getDataSource(serverCredentials.getServerip(), serverCredentials.getPortno(),
                serverCredentials.getUsername(), serverCredentials.getPassword(), serverCredentials.getMasterdb());
    }

    @Bean(name = "masterdb")
    public DataSource getMasterDataSource() throws Exception {
        ServerCredentials serverCredentials = ApplicationConstant.getServerCredentials();
        if (serverCredentials == null)
            throw new Exception("Server Credentials Not Found....!");
        return getDataSource(serverCredentials.getServerip(), serverCredentials.getPortno(),
                serverCredentials.getUsername(), serverCredentials.getPassword(), serverCredentials.getMasterdb());
    }

    @Bean(name = "transactiondb")
    public DataSource getTranDataSource() throws Exception {
        ServerCredentials serverCredentials = ApplicationConstant.getServerCredentials();
        if (serverCredentials == null)
            throw new Exception("Server Credentials Not Found....!");
        return getDataSource(serverCredentials.getServerip(), serverCredentials.getPortno(),
                serverCredentials.getUsername(), serverCredentials.getPassword(), serverCredentials.getMasterdb());
    }


}

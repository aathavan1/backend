package com.aathavan.backend.common;


import com.aathavan.backend.model.ServerCredentials;

import java.time.format.DateTimeFormatter;

public class ApplicationConstant {

    private static ServerCredentials serverCredentials;
    public static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    public static class DateFormat {
        public static final DateTimeFormatter SAVEDATEFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public static ServerCredentials getServerCredentials() {
        return serverCredentials;
    }

    public static void setServerCredentials(ServerCredentials serverCredentials) {
        ApplicationConstant.serverCredentials = serverCredentials;
    }


}

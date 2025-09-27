package com.aathavan.backend.common;


import com.aathavan.backend.model.ServerCredentials;

public class ApplicationConstant {

    private static ServerCredentials serverCredentials;
    public static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    public static ServerCredentials getServerCredentials() {
        return serverCredentials;
    }

    public static void setServerCredentials(ServerCredentials serverCredentials) {
        ApplicationConstant.serverCredentials = serverCredentials;
    }

}

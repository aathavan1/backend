package com.aathavan.backend.common;

public class ApplicationCommon {
    public static String prepareConnectionString(String serverIp, String portNo) {
        return "jdbc:mysql://" + serverIp + ":" + portNo + "/";
    }
}

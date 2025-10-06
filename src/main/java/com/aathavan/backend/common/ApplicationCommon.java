package com.aathavan.backend.common;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class ApplicationCommon {
    public static String prepareConnectionString(String serverIp, String portNo) {
        return "jdbc:mysql://" + serverIp + ":" + portNo + "/";
    }

    @Getter
    @Setter
    private static Map<String, Object> loginUser;
}

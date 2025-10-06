package com.aathavan.backend.common;


import com.aathavan.backend.model.ServerCredentials;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class ApplicationConstant {

    @Getter
    @Setter
    private static ServerCredentials serverCredentials;
    public static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    @Getter
    private static final int tokenExpiryTime = 60;

    public static class DateFormat {
        public static final DateTimeFormatter SAVEDATEFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        public static final DateTimeFormatter SAVEDATETIMEFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        public static final DateTimeFormatter DISPLAYDATETIMEFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        public static final SimpleDateFormat SIMPLEDATETIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }


}

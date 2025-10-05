package com.aathavan.backend.model;

import lombok.Data;

@Data
public class ServerCredentials {

    private String serverip;
    private String username;
    private String companyname;
    private String companycode;
    private String portno;
    private String masterdb;
    private String trandb;
    private String finyear;
    private String password;

}

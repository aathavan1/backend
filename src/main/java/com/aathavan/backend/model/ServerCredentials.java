package com.aathavan.backend.model;

public class ServerCredentials {

    private String serverip;
    private String username;
    private String companyname;
    private String companycode;
    private String portno;
    private String masterdb;
    private String trandb;
    private String finyear;

    public String getTrandb() {
        return trandb;
    }

    public void setTrandb(String trandb) {
        this.trandb = trandb;
    }

    public void setMasterdb(String masterdb) {
        this.masterdb = masterdb;
    }


    public String getServerip() {
        return serverip;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCompanyname() {
        return companyname;
    }

    public String getCompanycode() {
        return companycode;
    }

    public String getPortno() {
        return portno;
    }

    private String password;

    public void setServerip(String serverip) {
        this.serverip = serverip;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public void setPortno(String portno) {
        this.portno = portno;
    }

    public String getMasterdb() {
        return masterdb;
    }
}

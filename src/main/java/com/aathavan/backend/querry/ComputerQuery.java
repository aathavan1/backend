package com.aathavan.backend.querry;

import org.springframework.stereotype.Component;

@Component
public class ComputerQuery {

    public String getOperByComputer() {
        StringBuilder sb = new StringBuilder();
        sb.append("select o.*,c.ipid from computer as c inner join  operator as o\n");
        sb.append("on c.loginoperator = o.opercode \n");
        sb.append("where ipaddress =:ipaddress and loginoperator <> 0 \n");
        return sb.toString();
    }

    public String insertComputer() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO computer (ipaddress,ipid,createddate) \n");
        sb.append("VALUES (:ipaddress,:ipid,:createddate)");
        return sb.toString();
    }

    public String removeOtherComputerUser(){
        StringBuilder sb=new StringBuilder();
        sb.append(" UPDATE computer SET loginoperator = 0 WHERE ipaddress <> :ipaddress AND loginoperator= :loginoperator ;\n");
        return sb.toString();
    }
    public String updateComputerUser(){
        StringBuilder sb=new StringBuilder();
        sb.append(" UPDATE computer SET loginoperator= :loginoperator,lastlogin =:lastlogin WHERE ipaddress = :ipaddress  ;\n");
        return sb.toString();
    }

}

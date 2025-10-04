package com.aathavan.backend.querry;

import org.springframework.stereotype.Component;

@Component
public class ComputerQuery {

    public String getOperComputer() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM computer \n");
        sb.append(" WHERE loginoperator =:loginoperator AND ipaddress =:ipaddress");
        return sb.toString();
    }

    public String insertComputer() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO computer (ipaddress,ipid,createddate) \n");
        sb.append("VALUES (:ipaddress,:ipid,:createddate)");
        return sb.toString();
    }

    public String updateComputerUser(){
        StringBuilder sb=new StringBuilder();
        sb.append(" UPDATE computer SET loginoperator = 0 WHERE ipaddress <> :ipaddress AND loginoperator= :loginoperator \n");
        return sb.toString();
    }

}

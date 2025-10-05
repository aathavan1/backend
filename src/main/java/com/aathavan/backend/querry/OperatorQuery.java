package com.aathavan.backend.querry;

import org.springframework.stereotype.Component;

@Component
public class OperatorQuery {

    public String validateUser() {
        StringBuilder sb = new StringBuilder();
        sb.append("select opername from operator where opercode = :opercode and password =:password limit 1 \n");

        return sb.toString();
    }

    public String getOperDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from operator where opercode = ? and active = 'Y' limit 1 \n");
        return sb.toString();
    }

    public String getUserDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("select opername,op.opercode from operator as op \n");
        sb.append("inner join (select opercode,username, email from employee) as em \n");
        sb.append(" where (username =:username OR email=:username ) \n");
        sb.append("  AND active = 'Y' \n");

        return sb.toString();
    }

    public String updateToken() {
        StringBuilder sb = new StringBuilder();
        sb.append("update operator set authtoken = :authtoken where \n");
        sb.append("opercode =:opercode \n");

        return sb.toString();
    }
}

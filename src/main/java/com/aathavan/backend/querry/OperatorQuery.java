package com.aathavan.backend.querry;

import org.springframework.stereotype.Component;

@Component
public class OperatorQuery {

    public String validateUser() {
        StringBuilder sb = new StringBuilder();
        sb.append("select opername from operator where opername = :opername and password =:password \n");

        return sb.toString();
    }
}

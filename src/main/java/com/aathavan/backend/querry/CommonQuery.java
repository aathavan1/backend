package com.aathavan.backend.querry;

import org.springframework.stereotype.Component;

@Component
public class CommonQuery {

    public String checkDataExist(String tableName, String columnName, String columnValue) {
        StringBuilder sb = new StringBuilder();
        sb.append("select ").append(columnName).append(" from ").append(tableName);
        sb.append("\n where ").append(columnName).append(" = '").append(columnValue).append("' limit 1");
        return sb.toString();
    }

    public String getMaxNumber(String tableName, String columnName) {
        StringBuilder sb = new StringBuilder();
        sb.append("select ifnull(max(").append(columnName).append("),0) + 1 from ").append(tableName);
        return sb.toString();
    }

}

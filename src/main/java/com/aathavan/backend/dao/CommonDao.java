package com.aathavan.backend.dao;

import com.aathavan.backend.model.ReturnStatus;

public interface CommonDao {

    ReturnStatus checkDataExist(String tableName, String columnName, String columnValue) throws Exception;

    ReturnStatus getMaxNumber(String tableName, String columnName) throws Exception;

}

package com.aathavan.backend.dao;

import com.aathavan.backend.model.ReturnStatus;

import java.util.Map;

public interface OperatorDao {
    ReturnStatus validateUserData(Map<String, Object> userData) throws Exception;
}

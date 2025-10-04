package com.aathavan.backend.dao;

import com.aathavan.backend.model.ReturnStatus;

import java.util.Map;

public interface OperatorDao {
    ReturnStatus validateUserData(Map<String, Object> userData) throws Exception;

    ReturnStatus getOperDetails(String userid) throws Exception;

    ReturnStatus getUserDetails(Map<String, Object> userData) throws Exception;
}

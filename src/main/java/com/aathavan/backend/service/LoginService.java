package com.aathavan.backend.service;

import com.aathavan.backend.model.ReturnStatus;

import java.util.Map;

public interface LoginService {

    ReturnStatus getUserData(Map<String, Object> userData) throws Exception;

    ReturnStatus validateUserData(Map<String, Object> userData) throws Exception;

    ReturnStatus getOperDetails(String userid) throws Exception;

    void setComputerTable(Map<String, Object> userData) throws Exception;

    ReturnStatus getComputerTableData(Map<String, Object> userData) throws Exception;

}

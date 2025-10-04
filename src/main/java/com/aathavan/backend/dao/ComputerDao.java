package com.aathavan.backend.dao;

import com.aathavan.backend.model.ReturnStatus;

import java.util.Map;

public interface ComputerDao {
    ReturnStatus getOperComputer(Map<String, Object> userData) throws Exception;

    void insertComputerData(Map<String, Object> userData) throws Exception;

    void updateComputerUser(Map<String, Object> userData) throws Exception;
}

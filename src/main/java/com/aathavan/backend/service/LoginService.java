package com.aathavan.backend.service;

import com.aathavan.backend.model.ReturnStatus;

import java.util.Map;

public interface LoginService {
    ReturnStatus validateUserData(Map<String, Object> userData) throws Exception;
}

package com.aathavan.backend.logic;

import com.aathavan.backend.model.ReturnStatus;
import com.aathavan.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LoginLogic {


    @Autowired
    private LoginService loginService;

    public ReturnStatus validateUserData(Map<String, Object> userData) {
        try {
            return loginService.validateUserData(userData);
        } catch (Exception e) {
            return new ReturnStatus(false, e.getMessage());
        }
    }
}

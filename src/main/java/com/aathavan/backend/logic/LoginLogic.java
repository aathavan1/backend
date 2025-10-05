package com.aathavan.backend.logic;

import com.aathavan.backend.common.TokenGeneration;
import com.aathavan.backend.model.ReturnStatus;
import com.aathavan.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class LoginLogic {

    @Autowired
    private LoginService loginService;
    @Autowired
    private TokenGeneration generateToken;

    public ReturnStatus validateUserData(Map<String, Object> userData) {
        try {
            userData.put("opercode", (((List<Map<String, Object>>) loginService.getUserData(userData).getObjectdata())
                    .getFirst().get("opercode")));
            ReturnStatus status = loginService.validateUserData(userData);
            if (status.isStatus()) {
                loginService.setComputerTable(userData);
                userData.put("authtoken", generateToken.generateToken(String.valueOf(userData.get("opercode"))));
                loginService.updateToken(userData);
            }
            return status;
        } catch (Exception e) {
            return new ReturnStatus(false, "Invalid Token", e.getMessage());
        }
    }

    public ReturnStatus getAuthTokenDetail(String userid) {
        try {
            ReturnStatus authToken = loginService.getOperDetails(userid);
            if (authToken.isStatus()) {
                ReturnStatus status = generateToken.checkToken(((Map<String, Object>) authToken.getObjectdata()).get("authtoken"));
                if (!status.isStatus()) {
                    return status;
                }
            }
            return authToken;
        } catch (Exception e) {
            return new ReturnStatus(false, e.getMessage());
        }
    }
}

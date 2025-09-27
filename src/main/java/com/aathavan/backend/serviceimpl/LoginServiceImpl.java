package com.aathavan.backend.serviceimpl;

import com.aathavan.backend.dao.OperatorDao;
import com.aathavan.backend.model.ReturnStatus;
import com.aathavan.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LoginServiceImpl implements LoginService {
    @Autowired
    private OperatorDao operatorDao;

    @Override
    public ReturnStatus validateUserData(Map<String, Object> userData) throws Exception {
        return operatorDao.validateUserData(userData);
    }
}

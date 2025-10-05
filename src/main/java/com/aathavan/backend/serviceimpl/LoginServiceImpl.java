package com.aathavan.backend.serviceimpl;

import com.aathavan.backend.common.ApplicationConstant;
import com.aathavan.backend.dao.CommonDao;
import com.aathavan.backend.dao.ComputerDao;
import com.aathavan.backend.dao.OperatorDao;
import com.aathavan.backend.model.ReturnStatus;
import com.aathavan.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private OperatorDao operatorDao;
    @Autowired
    private ComputerDao computerDao;
    @Autowired
    private CommonDao commonDao;

    @Override
    public ReturnStatus getUserData(Map<String, Object> userData) throws Exception {
        return operatorDao.getUserDetails(userData);
    }

    @Override
    public ReturnStatus validateUserData(Map<String, Object> userData) throws Exception {
        return operatorDao.validateUserData(userData);
    }

    @Override
    public ReturnStatus getOperDetails(String userid) throws Exception {
        return operatorDao.getOperDetails(userid);
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void setComputerTable(Map<String, Object> userData) throws Exception {
        userData.put("loginoperator", userData.get("opercode"));
        computerDao.updateComputerUser(userData);
        if (!commonDao.checkDataExist("computer", "ipaddress", String.valueOf(userData.get("ipaddress"))).isStatus()) {
            userData.put("ipid", commonDao.getMaxNumber("computer", "ipid").getObjectdata());
            userData.put("createddate", ApplicationConstant.DateFormat.SAVEDATEFORMAT.format(LocalDate.now()));
            computerDao.insertComputerData(userData);
        }
    }

    @Override
    public ReturnStatus getComputerTableData(Map<String, Object> userData) throws Exception {
        userData.put("loginoperator", userData.get("opercode"));
        return computerDao.getOperComputer(userData);
    }

    @Override
    public void updateToken(Map<String, Object> userData) throws Exception {
        operatorDao.updateToken(userData);
    }


}

package com.aathavan.backend.serviceimpl;

import com.aathavan.backend.common.ApplicationCommon;
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
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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
    @Transactional(rollbackFor = Exception.class)
    public void setComputerTable(Map<String, Object> userData) throws Exception {
        userData.put("loginoperator", userData.get("opercode"));
        if (!commonDao.checkDataExist("computer", "ipaddress", String.valueOf(userData.get("ipaddress"))).isStatus()) {
            userData.put("ipid", commonDao.getMaxNumber("computer", "ipid").getObjectdata());
            userData.put("createddate", ApplicationConstant.DateFormat.SAVEDATEFORMAT.format(LocalDate.now()));
            computerDao.insertComputerData(userData);
        }
        userData.put("lastlogin", ApplicationConstant.DateFormat.SAVEDATETIMEFORMAT.format(LocalDateTime.now()));
        computerDao.updateComputerUser(userData);
    }

    @Override
    public ReturnStatus getComputerTableData(Map<String, Object> userData) throws Exception {
        List<Map<String, Object>> lstOperDetails = (List<Map<String, Object>>) computerDao.getOperComputer(userData).getObjectdata();
        if (lstOperDetails.isEmpty())
            return new ReturnStatus(false);

        LocalDateTime localDateTime = LocalDateTime.parse(String.valueOf(lstOperDetails.getFirst().get("tokenexpiry")));
        if (localDateTime.isAfter(localDateTime.now())) {
            return new ReturnStatus(false);
        }
        ApplicationCommon.setLoginUser(userData);

        return new ReturnStatus(true);
    }

    @Override
    public void updateToken(Map<String, Object> userData) throws Exception {
        userData.put("tokenexpiry", ApplicationConstant.DateFormat.SAVEDATETIMEFORMAT
                .format(LocalDateTime.now().plusSeconds(ApplicationConstant.getTokenExpiryTime())));
        operatorDao.updateToken(userData);
    }


}

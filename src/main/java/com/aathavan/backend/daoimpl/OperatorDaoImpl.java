package com.aathavan.backend.daoimpl;

import com.aathavan.backend.common.Secutity;
import com.aathavan.backend.dao.OperatorDao;
import com.aathavan.backend.model.ReturnStatus;
import com.aathavan.backend.querry.OperatorQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Repository
public class OperatorDaoImpl implements OperatorDao {
    @Autowired
    private DataSource masterdb;
    @Autowired
    private OperatorQuery operatorQuery;

    @Override
    public ReturnStatus validateUserData(Map<String, Object> userData) {
        try {
            userData.replace("password", Secutity.encrypter(String.valueOf(userData.get("password"))));
            return new ReturnStatus(true, (Object) new NamedParameterJdbcTemplate(masterdb).queryForObject(
                    operatorQuery.validateUser(), userData, String.class));
        } catch (EmptyResultDataAccessException e) {
            return new ReturnStatus(false, "Invalid User Or Password");
        } catch (IncorrectResultSizeDataAccessException e) {
            return new ReturnStatus(true, "Multiple Datas Found For this user and password");
        }
    }

    @Override
    public ReturnStatus getOperDetails(String userid) throws Exception {
        try {
            return new ReturnStatus(true, new JdbcTemplate(masterdb).queryForObject(operatorQuery.getOperDetails(),
                    new Object[]{userid}, Map.class));
        } catch (EmptyResultDataAccessException e) {
            return new ReturnStatus(false, "Invalid User ");
        }
    }

    @Override
    public ReturnStatus getUserDetails(Map<String, Object> userData) throws Exception {
        return new ReturnStatus(true, new NamedParameterJdbcTemplate(masterdb)
                .queryForList(operatorQuery.getUserDetails(), userData));
    }

    @Override
    public void updateToken(Map<String, Object> userData) throws Exception {
        new NamedParameterJdbcTemplate(masterdb).update(operatorQuery.updateToken(), userData);
    }
}

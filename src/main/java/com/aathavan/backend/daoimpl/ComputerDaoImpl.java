package com.aathavan.backend.daoimpl;

import com.aathavan.backend.dao.ComputerDao;
import com.aathavan.backend.model.ReturnStatus;
import com.aathavan.backend.querry.ComputerQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Repository
public class ComputerDaoImpl implements ComputerDao {

    @Autowired
    private DataSource masterdb;
    @Autowired
    private ComputerQuery computerQuery;

    @Override
    public ReturnStatus getOperComputer(Map<String, Object> userData) throws Exception {
        return new ReturnStatus(true, new NamedParameterJdbcTemplate(masterdb).queryForList(computerQuery.getOperByComputer(), userData));
    }

    @Override
    public void insertComputerData(Map<String, Object> userData) throws Exception {
        new NamedParameterJdbcTemplate(masterdb).update(computerQuery.insertComputer(), userData);
    }

    @Override
    public void updateComputerUser(Map<String, Object> userData) throws Exception {
        new NamedParameterJdbcTemplate(masterdb).update(computerQuery.removeOtherComputerUser(), userData);
        new NamedParameterJdbcTemplate(masterdb).update(computerQuery.updateComputerUser(), userData);
    }
}

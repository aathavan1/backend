package com.aathavan.backend.daoimpl;

import com.aathavan.backend.dao.CommonDao;
import com.aathavan.backend.model.ReturnStatus;
import com.aathavan.backend.querry.CommonQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class CommonDaoImpl implements CommonDao {

    @Autowired
    private CommonQuery commonQuery;

    @Autowired
    private DataSource masterdb;


    @Override
    public ReturnStatus checkDataExist(String tableName, String columnName, String columnValue) throws Exception {
        try {
            new JdbcTemplate(masterdb).queryForObject(commonQuery.checkDataExist(tableName, columnName, columnValue), String.class);
            return new ReturnStatus(true, "Data Found");
        } catch (EmptyResultDataAccessException e) {
            return new ReturnStatus(false, "No Data Found");
        }
    }

    @Override
    public ReturnStatus getMaxNumber(String tableName, String columnName) throws Exception {
        return new ReturnStatus(true, new JdbcTemplate(masterdb).queryForObject(commonQuery.getMaxNumber(tableName, columnName), Integer.class));
    }
}

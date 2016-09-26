package io.woolford.db.service;

import io.woolford.db.entity.CategoryCompanyRecord;
import io.woolford.db.mapper.DbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {

    @Autowired
    private DbMapper dbMapper;

    public List<CategoryCompanyRecord> getCategoryCompanyRecords() {
        return dbMapper.getCategoryCompanyList();
    }

}
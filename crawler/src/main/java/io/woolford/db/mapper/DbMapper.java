package io.woolford.db.mapper;

import io.woolford.db.entity.CategoryCompanyRecord;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DbMapper {

    @Select("SELECT category, company FROM consumer_affairs.category_company ")
    public List<CategoryCompanyRecord> getCategoryCompanyList();

}
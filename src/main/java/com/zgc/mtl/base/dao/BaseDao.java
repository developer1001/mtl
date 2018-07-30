package com.zgc.mtl.base.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public interface BaseDao<T> {

    T findById(int id);

    List<T> findAllObj();

    int add(T t);

    int update(T t);

    int deleteById(int id);
}

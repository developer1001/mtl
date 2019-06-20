package com.zgc.mtl.base.dao;

import java.util.List;

public interface BaseDao<T> {

    T findById(int id);

    List<T> findAllObj();

    int add(T t);

    int update(T t);

    int deleteById(int id);
}

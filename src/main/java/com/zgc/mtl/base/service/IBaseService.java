package com.zgc.mtl.base.service;

import java.util.List;

public interface IBaseService<T> {

	 T findById(int id);

	    List<T> findAllObj();

	    int add(T t);

	    int update(T t);

	    int deleteById(int id);
}

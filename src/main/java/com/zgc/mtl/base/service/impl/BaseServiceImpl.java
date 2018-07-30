package com.zgc.mtl.base.service.impl;

import java.util.List;
import com.zgc.mtl.base.dao.BaseDao;
import com.zgc.mtl.base.service.IBaseService;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {

	 public abstract BaseDao<T> baseDao();

	    public T findById(int id) {
	        return baseDao().findById(id);
	    }

	    @Override
	    public List<T> findAllObj() {
	        return baseDao().findAllObj();
	    }

	    public int add(T t) {
	        return baseDao().add(t);
	    }

	    public int update(T t) {
	        return  baseDao().update(t);
	    }

	    public int deleteById(int id) {
	        return   baseDao().deleteById(id);
	    }

}

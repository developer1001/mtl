package com.zgc.mtl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zgc.mtl.base.dao.BaseDao;
import com.zgc.mtl.base.service.impl.BaseServiceImpl;
import com.zgc.mtl.dao.SysUserDao;
import com.zgc.mtl.model.SysUser;
import com.zgc.mtl.service.ISysUserService;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements ISysUserService {

	@Autowired
	private SysUserDao sysUserDao;
	@Override
	public BaseDao<SysUser> baseDao() {
		// TODO Auto-generated method stub
		return sysUserDao;
	}
	@Override
	public SysUser login(String loginName, String password) {
		return sysUserDao.login(loginName, password);
	}

}

package com.zgc.mtl.dao;

import org.apache.ibatis.annotations.Param;

import com.zgc.mtl.base.dao.BaseDao;
import com.zgc.mtl.model.SysUser;

public interface SysUserDao extends BaseDao<SysUser> {
	/**
	 * 登录验证
	 * @param loginName
	 * @param password
	 * @return
	 */
	SysUser login(@Param(value = "loginName") String loginName,@Param(value = "password")String password);
}

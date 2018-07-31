package com.zgc.mtl.service;

import com.zgc.mtl.base.service.IBaseService;
import com.zgc.mtl.model.SysUser;

public interface ISysUserService extends IBaseService<SysUser>{
	/**
	 * 登录验证
	 * @param loginName
	 * @param password
	 * @return
	 */
	SysUser login(String loginName,String password);
}

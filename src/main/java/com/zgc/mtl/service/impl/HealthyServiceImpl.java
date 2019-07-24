package com.zgc.mtl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zgc.mtl.mybatisGenerator.dao.THealthyMapper;
import com.zgc.mtl.mybatisGenerator.entity.THealthy;
import com.zgc.mtl.mybatisGenerator.entity.THealthyExample;
import com.zgc.mtl.service.HealthyService;
@Service
public class HealthyServiceImpl implements HealthyService {
	@Autowired
	private THealthyMapper tHealthyMapper;
	@Override
	public Object getAll(Map<String, Object> param) {
		String userId = (String)param.get("userId");
		if(userId == null) {
			throw new RuntimeException("userId必须提供");
		}
		int pageNo =  param.get("pageNo") == null ? 1 : Integer.parseInt(param.get("pageNo").toString());
		int pageSize = param.get("pageSize") == null ? 10 : Integer.parseInt(param.get("pageSize").toString());
		THealthyExample example = new THealthyExample();
		example.setOrderByClause("create_date desc");
		THealthyExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		PageHelper.startPage(pageNo, pageSize);
		List<THealthy> selectByExample = tHealthyMapper.selectByExample(example);
		return selectByExample;
	}

}

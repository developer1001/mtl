package com.zgc.mtl.service;

import java.util.List;

import com.zgc.mtl.controller.requestParam.InvestProfitDetail;
import com.zgc.mtl.mybatisGenerator.entity.TInvestProfit;
import com.zgc.mtl.service.impl.dto.InvestOrders;

/**
 * 
 * @date 2019-10-29 18:19:55
 * @author yang
 */
public interface InvestProfitService {
	/**
	 * 
	 * @param profit
	 * @return
	 */
	int insert(TInvestProfit profit) throws Exception; 
	/**
	 * 近期收益
	 * @param request
	 * @return
	 */
	Object recentProfit(InvestProfitDetail request);
	
    /**
     * 产品列表
     * @return
     */
    List<InvestOrders> getAllOrders();
}

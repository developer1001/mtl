package com.zgc.mtl.service;

import com.zgc.mtl.controller.requestParam.InvestProfitDetail;
import com.zgc.mtl.mybatisGenerator.entity.TInvestProfit;

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
	
	Object recentProfit(InvestProfitDetail request);
}

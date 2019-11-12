package com.zgc.mtl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zgc.mtl.controller.requestParam.InvestProfitDetail;
import com.zgc.mtl.mybatisGenerator.entity.TInvestProfit;
import com.zgc.mtl.service.InvestProfitService;
import com.zgc.mtl.service.impl.dto.InvestOrders;

/**
 * 
 * @date 2019-10-29 18:23:52
 * @author yang
 */
@RestController
@RequestMapping("/investProfit")
public class InvestProfitController {
	@Autowired
	private InvestProfitService investProfitService;
	@RequestMapping("add")
	public Object insert(TInvestProfit profit) throws Exception {
		int count = investProfitService.insert(profit);
		return count;
	}
	
	@RequestMapping("recent")
	public Object recentProfit(InvestProfitDetail request){
		Object detail = investProfitService.recentProfit(request);
		return detail;
	}
	
	@RequestMapping("allOrders")
	public List<InvestOrders> getAllOrders(){
		List<InvestOrders> allOrders = investProfitService.getAllOrders();
		return allOrders;
	}
}

package com.zgc.mtl.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zgc.mtl.controller.requestParam.InvestProfitDetail;
import com.zgc.mtl.mybatisGenerator.dao.TInvestProfitExtendMapper;
import com.zgc.mtl.mybatisGenerator.dao.TInvestProfitMapper;
import com.zgc.mtl.mybatisGenerator.entity.TInvestProfit;
import com.zgc.mtl.service.InvestProfitService;
import com.zgc.mtl.service.impl.dto.AvgProfit;
import com.zgc.mtl.service.impl.dto.InvestOrders;
import com.zgc.mtl.service.impl.dto.Investprofit;
/**
 * 
 * @date 2019-10-29 18:21:50
 * @author yang
 */
@Service
public class InvestProfitServiceImpl implements InvestProfitService {
	@Autowired
	private TInvestProfitMapper profitMapper;
	@Autowired
	private TInvestProfitExtendMapper profitExtendMapper;
	@Override
	public int insert(TInvestProfit profit) throws Exception {
		Long recentTotalMoney = profitExtendMapper.getRecentTotalMoney(profit.getOrderId());
		if(recentTotalMoney == null) {
			throw new Exception("订单号不存在，请核对购买产品的订单单号");
		}
		int checkExist = profitExtendMapper.checkExist(profit.getOrderId(), profit.getProfitDay());
		if(checkExist > 0) {
			throw new Exception("订单号当日收益记录已存在，请核对数据后再提交");
		}
		profit.setCurrTotal(recentTotalMoney + profit.getProfit());
		return profitMapper.insertSelective(profit);
	}
	
	@Override
	public Object recentProfit(InvestProfitDetail request) {
		Map<String, Object> result = new HashMap<>();
		List<AvgProfit> avgProfit = profitExtendMapper.avgProfit();
		result.put("avg", avgProfit);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(request.getRecentDays() != null) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -request.getRecentDays());
			Date from = c.getTime();
			//当天不算
			Calendar c2 = Calendar.getInstance();
			c2.add(Calendar.DAY_OF_MONTH, -1);
			Date to = c2.getTime();
			request.setFrom(from);
			request.setTo(to);
		}
		int totalDays = (int) (LocalDate.parse(sdf.format(request.getTo())).toEpochDay() -
				LocalDate.parse(sdf.format(request.getFrom())).toEpochDay()) + 1;
		if(totalDays < 0 || totalDays > 90) {
			//时间不对或者跨段太长的不查询
			return result;
		}
		List<Investprofit> recentProfit = profitExtendMapper.getRecentProfit(request);
		if(recentProfit == null) {
			return result;
		}
		HashSet<String> oderIds = new HashSet<String>();
		for(Investprofit profit : recentProfit) {
			oderIds.add(profit.getOrderId());
		}
		List<TreeSet<Investprofit>> ordersProfit = new ArrayList<>();
		for(String orderId : oderIds) {
			TreeSet<Investprofit> singleOrderList = new TreeSet<>(new Comparator<Investprofit>() {
				@Override
				public int compare(Investprofit o1, Investprofit o2) {
					return (int) (LocalDate.parse(o1.getProfitDay()).toEpochDay() -
							LocalDate.parse(o2.getProfitDay()).toEpochDay());
				}
			});
			Set<String> contain = new HashSet<String>();
			for(Investprofit profit : recentProfit) {
				if(profit.getOrderId().equals(orderId)) {
					singleOrderList.add(profit);
					contain.add(profit.getProfitDay());
				}
			}
			for(int i = 0; i < totalDays; i ++) {
				Calendar c = Calendar.getInstance();
				c.setTime(request.getFrom());
				c.add(Calendar.DAY_OF_MONTH, i);
				Date tempDate = c.getTime();
				if(!contain.contains(sdf.format(tempDate.getTime()))) {
					if(singleOrderList.size() > 0) {
						Investprofit first = singleOrderList.first();
						Investprofit investprofit = new Investprofit();
						investprofit.setOrderId(first.getOrderId());
						investprofit.setProductName(first.getProductName());
						investprofit.setProfit(0);
						investprofit.setProfitDay(sdf.format(tempDate));
						singleOrderList.add(investprofit);
					}
				}
			}
			ordersProfit.add(singleOrderList);	
			result.put("detail", ordersProfit);
		}
		return result;
	}

	@Override
	public List<InvestOrders> getAllOrders() {
		return profitExtendMapper.getAllOrders();
	}

}

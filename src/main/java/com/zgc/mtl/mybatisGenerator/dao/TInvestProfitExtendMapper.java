package com.zgc.mtl.mybatisGenerator.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zgc.mtl.controller.requestParam.InvestProfitDetail;
import com.zgc.mtl.service.impl.dto.AvgProfit;
import com.zgc.mtl.service.impl.dto.Investprofit;

public interface TInvestProfitExtendMapper {
    Long getRecentTotalMoney(String orderId);
    
    List<Investprofit> getRecentProfit(InvestProfitDetail request);
    /**
     * 查询当日的记录是否已经存在
     * @param orderId
     * @param profitDay
     * @return
     */
    int checkExist(@Param("orderId")String orderId, @Param("profitDay")Date profitDay);
    
    List<AvgProfit> avgProfit();
}
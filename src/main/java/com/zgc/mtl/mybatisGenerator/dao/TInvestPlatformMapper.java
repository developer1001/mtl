package com.zgc.mtl.mybatisGenerator.dao;

import com.zgc.mtl.mybatisGenerator.entity.TInvestPlatform;
import com.zgc.mtl.mybatisGenerator.entity.TInvestPlatformExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TInvestPlatformMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_invest_platform
     *
     * @mbg.generated
     */
    long countByExample(TInvestPlatformExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_invest_platform
     *
     * @mbg.generated
     */
    int deleteByExample(TInvestPlatformExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_invest_platform
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String platformId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_invest_platform
     *
     * @mbg.generated
     */
    int insert(TInvestPlatform record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_invest_platform
     *
     * @mbg.generated
     */
    int insertSelective(TInvestPlatform record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_invest_platform
     *
     * @mbg.generated
     */
    List<TInvestPlatform> selectByExample(TInvestPlatformExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_invest_platform
     *
     * @mbg.generated
     */
    TInvestPlatform selectByPrimaryKey(String platformId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_invest_platform
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TInvestPlatform record, @Param("example") TInvestPlatformExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_invest_platform
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TInvestPlatform record, @Param("example") TInvestPlatformExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_invest_platform
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TInvestPlatform record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_invest_platform
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TInvestPlatform record);
}
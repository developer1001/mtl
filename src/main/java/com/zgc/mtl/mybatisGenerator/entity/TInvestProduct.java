package com.zgc.mtl.mybatisGenerator.entity;

import java.io.Serializable;
import java.util.Date;

public class TInvestProduct implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_invest_product.prd_id
     *
     * @mbg.generated
     */
    private Integer prdId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_invest_product.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_invest_product.platform_id
     *
     * @mbg.generated
     */
    private Integer platformId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_invest_product.type
     *
     * @mbg.generated
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_invest_product.min_buy
     *
     * @mbg.generated
     */
    private Long minBuy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_invest_product.period
     *
     * @mbg.generated
     */
    private Integer period;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_invest_product.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_invest_product.create_user
     *
     * @mbg.generated
     */
    private String createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_invest_product.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_invest_product.update_user
     *
     * @mbg.generated
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_invest_product
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_invest_product.prd_id
     *
     * @return the value of t_invest_product.prd_id
     *
     * @mbg.generated
     */
    public Integer getPrdId() {
        return prdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_invest_product.prd_id
     *
     * @param prdId the value for t_invest_product.prd_id
     *
     * @mbg.generated
     */
    public void setPrdId(Integer prdId) {
        this.prdId = prdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_invest_product.name
     *
     * @return the value of t_invest_product.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_invest_product.name
     *
     * @param name the value for t_invest_product.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_invest_product.platform_id
     *
     * @return the value of t_invest_product.platform_id
     *
     * @mbg.generated
     */
    public Integer getPlatformId() {
        return platformId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_invest_product.platform_id
     *
     * @param platformId the value for t_invest_product.platform_id
     *
     * @mbg.generated
     */
    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_invest_product.type
     *
     * @return the value of t_invest_product.type
     *
     * @mbg.generated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_invest_product.type
     *
     * @param type the value for t_invest_product.type
     *
     * @mbg.generated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_invest_product.min_buy
     *
     * @return the value of t_invest_product.min_buy
     *
     * @mbg.generated
     */
    public Long getMinBuy() {
        return minBuy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_invest_product.min_buy
     *
     * @param minBuy the value for t_invest_product.min_buy
     *
     * @mbg.generated
     */
    public void setMinBuy(Long minBuy) {
        this.minBuy = minBuy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_invest_product.period
     *
     * @return the value of t_invest_product.period
     *
     * @mbg.generated
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_invest_product.period
     *
     * @param period the value for t_invest_product.period
     *
     * @mbg.generated
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_invest_product.create_time
     *
     * @return the value of t_invest_product.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_invest_product.create_time
     *
     * @param createTime the value for t_invest_product.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_invest_product.create_user
     *
     * @return the value of t_invest_product.create_user
     *
     * @mbg.generated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_invest_product.create_user
     *
     * @param createUser the value for t_invest_product.create_user
     *
     * @mbg.generated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_invest_product.update_time
     *
     * @return the value of t_invest_product.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_invest_product.update_time
     *
     * @param updateTime the value for t_invest_product.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_invest_product.update_user
     *
     * @return the value of t_invest_product.update_user
     *
     * @mbg.generated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_invest_product.update_user
     *
     * @param updateUser the value for t_invest_product.update_user
     *
     * @mbg.generated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_invest_product
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", prdId=").append(prdId);
        sb.append(", name=").append(name);
        sb.append(", platformId=").append(platformId);
        sb.append(", type=").append(type);
        sb.append(", minBuy=").append(minBuy);
        sb.append(", period=").append(period);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
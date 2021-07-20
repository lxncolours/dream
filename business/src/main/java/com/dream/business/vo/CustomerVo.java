package com.dream.business.vo;

import com.dream.server.dto.PageVo;
import java.math.BigDecimal;

public class CustomerVo extends PageVo<CustomerVo> {

    /**
     * 用户id
     */
    private Integer customerId;

    /**
     * 用户名称
     */
    private String customerName;

    /**
     * 用户电话
     */
    private String mobile;

    /**
     * 住址
     */
    private String address;

    /**
     * 性别|枚举[SexEnum]：MALE("M", "男"), FEMALE("F", "女")
     */
    private String sex;

    /**
     * 消费金额
     */
    private BigDecimal consumptionAmount;


    /**
     * 余额
     */
    private BigDecimal balance;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public BigDecimal getConsumptionAmount() {
        return consumptionAmount;
    }

    public void setConsumptionAmount(BigDecimal consumptionAmount) {
        this.consumptionAmount = consumptionAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "CustomerVo{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", consumptionAmount=" + consumptionAmount +
                ", balance=" + balance +
                '}';
    }
}
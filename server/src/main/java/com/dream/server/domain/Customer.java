package com.dream.server.domain;

import java.math.BigDecimal;

public class Customer {
    private Integer customerId;

    private String customerName;

    private String mobile;

    private String address;

    private String sex;

    private BigDecimal consumptionAmount;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", customerId=").append(customerId);
        sb.append(", customerName=").append(customerName);
        sb.append(", mobile=").append(mobile);
        sb.append(", address=").append(address);
        sb.append(", sex=").append(sex);
        sb.append(", consumptionAmount=").append(consumptionAmount);
        sb.append("]");
        return sb.toString();
    }
}
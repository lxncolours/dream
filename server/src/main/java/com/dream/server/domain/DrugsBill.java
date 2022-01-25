package com.dream.server.domain;

import java.math.BigDecimal;
import java.util.Date;

public class DrugsBill {
    private Integer billId;

    private String customerName;

    private BigDecimal billAmount;

    private Date billDate;

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", billId=").append(billId);
        sb.append(", customerName=").append(customerName);
        sb.append(", billAmount=").append(billAmount);
        sb.append(", billDate=").append(billDate);
        sb.append("]");
        return sb.toString();
    }
}
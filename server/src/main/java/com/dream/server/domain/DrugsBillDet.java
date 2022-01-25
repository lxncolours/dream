package com.dream.server.domain;

import java.math.BigDecimal;

public class DrugsBillDet {
    private Integer billDetId;

    private Integer billId;

    private String goodsName;

    private Integer goodsNum;

    private BigDecimal salePrice;

    private BigDecimal costPrice;

    private BigDecimal goodsAmount;

    public Integer getBillDetId() {
        return billDetId;
    }

    public void setBillDetId(Integer billDetId) {
        this.billDetId = billDetId;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", billDetId=").append(billDetId);
        sb.append(", billId=").append(billId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsNum=").append(goodsNum);
        sb.append(", salePrice=").append(salePrice);
        sb.append(", costPrice=").append(costPrice);
        sb.append(", goodsAmount=").append(goodsAmount);
        sb.append("]");
        return sb.toString();
    }
}
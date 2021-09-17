package com.dream.server.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Stock {
    private Integer stockId;

    private Integer goodsId;

    private Date stockDate;

    private BigDecimal inPrice;

    private Integer inNum;

    private BigDecimal inMoney;

    private Integer stockNum;

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Date getStockDate() {
        return stockDate;
    }

    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }

    public BigDecimal getInPrice() {
        return inPrice;
    }

    public void setInPrice(BigDecimal inPrice) {
        this.inPrice = inPrice;
    }

    public Integer getInNum() {
        return inNum;
    }

    public void setInNum(Integer inNum) {
        this.inNum = inNum;
    }

    public BigDecimal getInMoney() {
        return inMoney;
    }

    public void setInMoney(BigDecimal inMoney) {
        this.inMoney = inMoney;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", stockId=").append(stockId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", stockDate=").append(stockDate);
        sb.append(", inPrice=").append(inPrice);
        sb.append(", inNum=").append(inNum);
        sb.append(", inMoney=").append(inMoney);
        sb.append(", stockNum=").append(stockNum);
        sb.append("]");
        return sb.toString();
    }
}
package com.dream.server.domain;

import java.math.BigDecimal;

public class OrderDet {
    private Integer orderDetId;

    private Integer orderId;

    private Integer goodsId;

    private Integer stockId;

    private BigDecimal inPrice;

    private BigDecimal outPrice;

    private Integer num;

    private BigDecimal inMoney;

    private BigDecimal outMoney;

    public Integer getOrderDetId() {
        return orderDetId;
    }

    public void setOrderDetId(Integer orderDetId) {
        this.orderDetId = orderDetId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public BigDecimal getInPrice() {
        return inPrice;
    }

    public void setInPrice(BigDecimal inPrice) {
        this.inPrice = inPrice;
    }

    public BigDecimal getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(BigDecimal outPrice) {
        this.outPrice = outPrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getInMoney() {
        return inMoney;
    }

    public void setInMoney(BigDecimal inMoney) {
        this.inMoney = inMoney;
    }

    public BigDecimal getOutMoney() {
        return outMoney;
    }

    public void setOutMoney(BigDecimal outMoney) {
        this.outMoney = outMoney;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderDetId=").append(orderDetId);
        sb.append(", orderId=").append(orderId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", stockId=").append(stockId);
        sb.append(", inPrice=").append(inPrice);
        sb.append(", outPrice=").append(outPrice);
        sb.append(", num=").append(num);
        sb.append(", inMoney=").append(inMoney);
        sb.append(", outMoney=").append(outMoney);
        sb.append("]");
        return sb.toString();
    }
}
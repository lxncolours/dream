package com.dream.business.vo;

import com.dream.server.dto.PageVo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

public class StockVo extends PageVo<StockVo> {

    /**
     * 库存id
     */
    private Integer stockId;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商店的名字
     */
    private String storeName;

    /**
     * 入库日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date stockDate;
    /**
     * 开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date beginDate;
    /**
     * 截止日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endDate;


    /**
     * 入库单价
     */
    private BigDecimal inPrice;

    /**
     * 入库数量
     */
    private Integer inNum;

    /**
     * 入库金额
     */
    private BigDecimal inMoney;

    /**
     * 库存数量
     */
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Date getStockDate() {
        return stockDate;
    }

    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }


    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        return "StockVo{" +
                "stockId=" + stockId +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", stockDate=" + stockDate +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", inPrice=" + inPrice +
                ", inNum=" + inNum +
                ", inMoney=" + inMoney +
                ", stockNum=" + stockNum +
                '}';
    }
}
package com.dream.system.vo;

import com.dream.server.dto.PageVo;
import java.math.BigDecimal;

public class GoodsVo extends PageVo<GoodsVo> {

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 一级分类id
     */
    private String category1Id;
    private String category1Name;

    /**
     * 二级分类id
     */
    private String category2Id;
    private String category2Name;

    /**
     * 三级分类id
     */
    private String category3Id;
    private String category3Name;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 商家名称
     */
    private String storeName;

    /**
     * 商品库存
     */
    private Integer num;

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

    public String getCategory1Id() {
        return category1Id;
    }

    public void setCategory1Id(String category1Id) {
        this.category1Id = category1Id;
    }

    public String getCategory2Id() {
        return category2Id;
    }

    public void setCategory2Id(String category2Id) {
        this.category2Id = category2Id;
    }

    public String getCategory3Id() {
        return category3Id;
    }

    public void setCategory3Id(String category3Id) {
        this.category3Id = category3Id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


    public String getCategory1Name() {
        return category1Name;
    }

    public void setCategory1Name(String category1Name) {
        this.category1Name = category1Name;
    }

    public String getCategory2Name() {
        return category2Name;
    }

    public void setCategory2Name(String category2Name) {
        this.category2Name = category2Name;
    }

    public String getCategory3Name() {
        return category3Name;
    }

    public void setCategory3Name(String category3Name) {
        this.category3Name = category3Name;
    }

    @Override
    public String toString() {
        return "GoodsVo{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", category1Id='" + category1Id + '\'' +
                ", category1Name='" + category1Name + '\'' +
                ", category2Id='" + category2Id + '\'' +
                ", category2Name='" + category2Name + '\'' +
                ", category3Id='" + category3Id + '\'' +
                ", category3Name='" + category3Name + '\'' +
                ", price=" + price +
                ", storeName='" + storeName + '\'' +
                ", num=" + num +
                '}';
    }
}
package com.dream.business.vo;

import com.dream.server.domain.OrderDet;
import com.dream.server.dto.PageVo;
import java.math.BigDecimal;
import java.util.List;

public class OrderVo extends PageVo<OrderVo> {

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 用户id
     */
    private Integer customerId;

    /**
     * 订单金额
     */
    private BigDecimal orderMoney;

    /**
     * 已支付金额
     */
    private BigDecimal payMoney;

    /**
     * 订单时间
     */
    private String orderTime;

    /**
     * 订单更新时间
     */
    private String updateTime;

    /**
     * 订单状态|枚举[OrderStatusEnum]：SETTLE("S", "结清"), UNCLEARED("U", "未结清")
     */
    private String status;

    /**
     * 下单人,对应login_name
     */
    private String operator;

    private List<OrderDet> orderDet;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public List<OrderDet> getOrderDet() {
        return orderDet;
    }

    public void setOrderDet(List<OrderDet> orderDet) {
        this.orderDet = orderDet;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", orderMoney=" + orderMoney +
                ", payMoney=" + payMoney +
                ", orderTime='" + orderTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", status='" + status + '\'' +
                ", operator='" + operator + '\'' +
                ", orderDet=" + orderDet +
                '}';
    }
}
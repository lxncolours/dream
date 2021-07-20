package com.dream.business.vo;

import com.dream.server.dto.PageVo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 余额实体
 *
 * @author Liberica
 * @date 2021-07-17
 */
public class BalVo extends PageVo<BalVo> implements Serializable {

    private static final long serialVersionUID = -580329133238419430L;
    /**
     * 客户id
     */
    private Integer customerId;

    /**
     * 充值金额
     */
    private BigDecimal balance;


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BalVo{" +
                "customerId=" + customerId +
                ", balance=" + balance +
                '}';
    }
}
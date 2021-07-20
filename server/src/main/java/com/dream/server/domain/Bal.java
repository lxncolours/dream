package com.dream.server.domain;

import java.math.BigDecimal;

public class Bal {
    private Integer balId;

    private Integer customerId;

    private BigDecimal balance;

    public Integer getBalId() {
        return balId;
    }

    public void setBalId(Integer balId) {
        this.balId = balId;
    }

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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", balId=").append(balId);
        sb.append(", customerId=").append(customerId);
        sb.append(", balance=").append(balance);
        sb.append("]");
        return sb.toString();
    }
}
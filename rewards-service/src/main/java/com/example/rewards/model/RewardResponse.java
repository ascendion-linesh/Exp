package com.example.rewards.model;

import java.math.BigDecimal;
import java.util.List;

public class RewardResponse {
    private BigDecimal discountAmount;
    private List<String> appliedRewards;
    private String message;

    public RewardResponse() {}

    public RewardResponse(BigDecimal discountAmount, List<String> appliedRewards, String message) {
        this.discountAmount = discountAmount;
        this.appliedRewards = appliedRewards;
        this.message = message;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }
    public List<String> getAppliedRewards() {
        return appliedRewards;
    }
    public void setAppliedRewards(List<String> appliedRewards) {
        this.appliedRewards = appliedRewards;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}

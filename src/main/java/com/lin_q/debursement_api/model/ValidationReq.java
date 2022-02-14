package com.lin_q.debursement_api.model;


public class ValidationReq { 
    private Integer userId;
    private Integer amountApproved;
    private Integer recipientId; 
    private String actionValue; 
    private String observation; 

    public void setUserId(Integer userId) { this.userId = userId; } 
    public void setAmountApproved(Integer amountApproved) { this.amountApproved = amountApproved; } 
    public void setRecipientId(Integer recipientId) { this.recipientId = recipientId; } 
    public void setActionValue(String actionValue) { this.actionValue = actionValue; } 
    public void setObservation(String observation) { this.observation = observation; } 
    
    public Integer getUserId() { return this.userId; }
    public Integer getAmountApproved() { return this.amountApproved; }
    public Integer getRecipientId() { return this.recipientId; }
    public String getActionValue() { return this.actionValue; } 
    public String getObservation() { return this.observation; } 
}

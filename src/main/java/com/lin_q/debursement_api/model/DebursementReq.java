package com.lin_q.debursement_api.model;

import java.util.List;

public class DebursementReq {

    private Integer budgindexId;

    private Integer userId;

    private String identifier;

    private String reason; 

    private Integer amountRequested; 

    private Integer recipientId; 


    public void setUserId(Integer userId) { this.userId = userId; } 

    public void setIdentifier(String identifier) { this.identifier = identifier; } 

    public void setReason(String reason) { this.reason = reason; } 

    public void setAmountRequested(Integer amountRequested) { this.amountRequested = amountRequested; } 

    public void setRecipientId(Integer recipientId) { this.recipientId = recipientId; } 

    public void setStatus(String status) { this.status = status; } 

    public void setReasonItems(List<ReasonItemsReq> reasonItems) { this.reasonItems = reasonItems; } 

    public Integer getBudgindexId() { 
        return this.budgindexId; }


    public Integer getUserId() { 
        return this.userId; }

    public String getIdentifier() { 
        return this.identifier; }

    public String getReason() { 
        return this.reason; }

    public Integer getAmountRequested() { 
        return this.amountRequested; } 
    public Integer getRecipientId() {

    return this.recipientId;
    } 


    private String status = "active"; 

    private List<ReasonItemsReq> reasonItems; 
    public String getStatus() { 
        return this.status; } 
    public List<ReasonItemsReq> getReasonItems() {

    return this.reasonItems;
    }
}
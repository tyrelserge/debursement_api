package com.lin_q.debursement_api.model;

public class SectorReq { 
    private Integer userId;
    private String budgsectorName;
    private String budgsectorDescription;

    public void setUserId(Integer userId) { this.userId = userId; } 
    public void setBudgsectorName(String budgsectorName) { this.budgsectorName = budgsectorName; } 
    public void setBudgsectorDescription(String budgsectorDescription) { this.budgsectorDescription = budgsectorDescription; } 
    public void setStatus(String status) { this.status = status; } 

    public Integer getUserId() { return this.userId; }
    public String getBudgsectorName() { return this.budgsectorName; } 
    public String getBudgsectorDescription() { return this.budgsectorDescription; } 
    
    private String status = "active"; 
    public String getStatus() { return this.status; }
}

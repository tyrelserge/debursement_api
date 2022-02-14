package com.lin_q.debursement_api.model;

    public class GroupedBudgetReq { 
    private Integer userId;
  
    private Integer budgsectorId;
  
  
    public void setUserId(Integer userId) { this.userId = userId; } 
    private String groupedbudgetName; 
    private String groupedbudgetDescription; 
    private Integer groupedbudgetValue; 
    public void setBudgsectorId(Integer budgsectorId) { this.budgsectorId = budgsectorId; } 
    public void setGroupedbudgetName(String groupedbudgetName) { this.groupedbudgetName = groupedbudgetName; } 
    public void setGroupedbudgetDescription(String groupedbudgetDescription) { this.groupedbudgetDescription = groupedbudgetDescription; } 
    public void setGroupedbudgetValue(Integer groupedbudgetValue) { this.groupedbudgetValue = groupedbudgetValue; } 
    public void setStatus(String status) { this.status = status; } 
    public void setRenewal(RenewalReq renewal) { this.renewal = renewal; } 
    
  
    public Integer getUserId() { return this.userId; }
  
    public Integer getBudgsectorId() { return this.budgsectorId; }
  
    public String getGroupedbudgetName() { return this.groupedbudgetName; }
  
    public String getGroupedbudgetDescription() { return this.groupedbudgetDescription; } 
    public Integer getGroupedbudgetValue() {
    return this.groupedbudgetValue;
  } 
    private String status = "active"; 
    private RenewalReq renewal; 
    public String getStatus() { return this.status; }
   
    public RenewalReq getRenewal() {
    return this.renewal;
  } 
}

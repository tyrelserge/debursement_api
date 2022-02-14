package com.lin_q.debursement_api.model;


public class BudgetIndexReq { 
private Integer userId; 
private Integer budgsectorId;
private Integer groupedbudgetId;

public void setUserId(Integer userId) { this.userId = userId; } 
private String budgindexName; 
private String budgindexDescription; 
private Integer budgindexValue; 

public void setBudgsectorId(Integer budgsectorId) { this.budgsectorId = budgsectorId; } 

public void setGroupedbudgetId(Integer groupedbudgetId) { this.groupedbudgetId = groupedbudgetId; } 

public void setBudgindexName(String budgindexName) { this.budgindexName = budgindexName; } 

public void setBudgindexDescription(String budgindexDescription) { this.budgindexDescription = budgindexDescription; } 

public void setBudgindexValue(Integer budgindexValue) { this.budgindexValue = budgindexValue; } 

public void setStatus(String status) { this.status = status; } 

public void setRenewal(RenewalReq renewal) { this.renewal = renewal; } 

public Integer getUserId() { 
return this.userId; }


public Integer getBudgsectorId() { 
return this.budgsectorId; }


public Integer getGroupedbudgetId() { 
return this.groupedbudgetId; }


public String getBudgindexName() { 
return this.budgindexName; }


public String getBudgindexDescription() { 
return this.budgindexDescription; } 

public Integer getBudgindexValue() {

return this.budgindexValue;
} 
private String status = "active"; 
private RenewalReq renewal; 

public String getStatus() { 
return this.status; }


public RenewalReq getRenewal() {

return this.renewal;
} }

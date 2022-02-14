package com.lin_q.debursement_api.model;

import java.util.Date;


public class RenewalReq {
private Date applyDate;
private Date nextRenewal;


public void setApplyDate(Date applyDate) { this.applyDate = applyDate; } 
public void setNextRenewal(Date nextRenewal) { this.nextRenewal = nextRenewal; } 
public void setStatus(String status) { this.status = status; } 

public Date getApplyDate() { return this.applyDate; } 
public Date getNextRenewal() {
return this.nextRenewal;
} private String status = "active"; 
public String getStatus() { return this.status; }

}

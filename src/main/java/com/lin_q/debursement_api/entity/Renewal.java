package com.lin_q.debursement_api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "renewal")
public class Renewal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "renewal_id")
    private Integer renewalId;
    @Column(name = "apply_date")
    private Date applyDate;
    @Column(name = "next_renewal")
    private Date nextRenewal;    
    @Column(name = "created_on") private Date createdOn; 
    @Column(name = "updated_on") private Date updatedOn; private String status; 

    public void setRenewalId(Integer renewalId) { this.renewalId = renewalId; } 
    public void setApplyDate(Date applyDate) { this.applyDate = applyDate; } 
    public void setNextRenewal(Date nextRenewal) { this.nextRenewal = nextRenewal; } 
    public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
    public void setUpdatedOn(Date updatedOn) { this.updatedOn = updatedOn; } 
    public void setStatus(String status) { this.status = status; } 
   
    public Integer getRenewalId() {
    return this.renewalId;
    } 
    public Date getApplyDate() {
    return this.applyDate;
    } 
    public Date getNextRenewal() {
    return this.nextRenewal;
    } 
    public Date getCreatedOn() {
    return this.createdOn;
    }
    
    public Date getUpdatedOn() { return this.updatedOn; } 
    public String getStatus() {
    return this.status;
    }
}

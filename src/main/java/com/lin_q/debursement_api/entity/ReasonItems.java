package com.lin_q.debursement_api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity

@Table

public class ReasonItems {

    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "reasonitem_id")    
    private Integer reasonitemId;    
    @Column(name = "debursement_id")    
    private Integer debursementId;    
    private String designation;    
    private Integer unitprice;    
    private Integer quatity; 
    private Integer totalprice; 
    @Column(name = "created_on") 
    private Date createdOn; 
    @Column(name = "updated_on") 
    private Date updatedOn; 
    private String status; 

    public void setReasonitemId(Integer reasonitemId) { this.reasonitemId = reasonitemId; } 
    public void setDebursementId(Integer debursementId) { this.debursementId = debursementId; } 
    public void setDesignation(String designation) { this.designation = designation; } 
    public void setUnitprice(Integer unitprice) { this.unitprice = unitprice; } 
    public void setQuatity(Integer quatity) { this.quatity = quatity; } 
    public void setTotalprice(Integer totalprice) { this.totalprice = totalprice; } 
    public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
    public void setUpdatedOn(Date updatedOn) { this.updatedOn = updatedOn; } 
    public void setStatus(String status) { this.status = status; } 

    public Integer getReasonitemId() {
    return this.reasonitemId;
    }
    
    public Integer getDebursementId() { return this.debursementId; }
    
    public String getDesignation() { return this.designation; }
    
    public Integer getUnitprice() { return this.unitprice; }
    
    public Integer getQuatity() { return this.quatity; } 
    public Integer getTotalprice() {
    return this.totalprice;
    } 
    public Date getCreatedOn() {
    return this.createdOn;
    }
    
    public Date getUpdatedOn() { return this.updatedOn; } 
    public String getStatus() {
    return this.status;
    }
}

package com.lin_q.debursement_api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "upwd")
public class Upwd {
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "upwd_id")    
    private Integer upwdId;    
    @Column(name = "user_id")
    private Integer userId;    
    @Column(name = "pass_string") 
    private String passString; 
    @Column(name = "created_on") 
    private Date createdOn; 
    private String status; 

    public void setUpwdId(Integer upwdId) { this.upwdId = upwdId; } 
    public void setUserId(Integer userId) { this.userId = userId; } 
    public void setPassString(String passString) { this.passString = passString; } 
    public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
    public void setStatus(String status) { this.status = status; } 

    public Integer getUpwdId() {
    return this.upwdId;
    } 
    public Integer getUserId() {
    return this.userId;
    } 
    public String getPassString() {
    return this.passString;
    }
    
    public Date getCreatedOn() { return this.createdOn; } 
    public String getStatus() {
    return this.status;
    }
}

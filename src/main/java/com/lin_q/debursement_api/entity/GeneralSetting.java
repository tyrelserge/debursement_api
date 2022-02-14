package com.lin_q.debursement_api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity    
    @Table(name = "general_setting")    
    public class GeneralSetting {    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "general_setting_id")    
    private Integer generalSettingId;    
    private String currency;    
    @Column(name = "validation_step")    
    private Integer validationStep;    
    @Column(name = "user_id") 
    private Integer userId; 
    @Column(name = "created_on") 
    private Date createdOn; 
    @Column(name = "updated_on") 
    private Date updatedOn; 
    @Column(name = "status") 
    private String status; 

    public void setGeneralSettingId(Integer generalSettingId) { this.generalSettingId = generalSettingId; } 
    public void setCurrency(String currency) { this.currency = currency; } 
    public void setValidationStep(Integer validationStep) { this.validationStep = validationStep; } 
    public void setUserId(Integer userId) { this.userId = userId; } 
    public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
    public void setUpdatedOn(Date updatedOn) { this.updatedOn = updatedOn; } 
    public void setStatus(String status) { this.status = status; } 

    public Integer getGeneralSettingId()
    {
    return this.generalSettingId; } 
    public String getCurrency() {
    return this.currency;
    } 
    public Integer getValidationStep() {
    return this.validationStep;
    } 
    public Integer getUserId() {
    return this.userId;
    } 
    public Date getCreatedOn() {
    return this.createdOn;
    } 
    public Date getUpdatedOn() {
    return this.updatedOn;
    } 
    public String getStatus() {
    return this.status;
    }
}

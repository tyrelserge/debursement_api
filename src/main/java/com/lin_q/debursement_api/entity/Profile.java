package com.lin_q.debursement_api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity    
@Table(name = "profile")
public class Profile {
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "profile_id")    
    private Integer profileId;    
    @Column(name = "user_id")    
    private Integer userId;    
    @Column(name = "profile_name")    
    private String profileName;    
    @Column(name = "profile_level") 
    private String profileLevel; 
    @Column(name = "created_on") 
    private Date createdOn; 
    @Column(name = "updated_on") 
    private Date updatedOn; 

    public void setProfileId(Integer profileId) { this.profileId = profileId; } 
    public void setUserId(Integer userId) { this.userId = userId; } 
    public void setProfileName(String profileName) { this.profileName = profileName; } 
    public void setProfileLevel(String profileLevel) { this.profileLevel = profileLevel; } 
    public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
    public void setUpdatedOn(Date updatedOn) { this.updatedOn = updatedOn; } 
    
    public Integer getProfileId() {
    return this.profileId;
    } 
    public Integer getUserId() {
    return this.userId;
    } 
    public String getProfileName() {
    return this.profileName;
    } 
    public String getProfileLevel() {
    return this.profileLevel;
    } 
    public Date getCreatedOn() {
    return this.createdOn;
    } 
    public Date getUpdatedOn() {
    return this.updatedOn;
    }
}

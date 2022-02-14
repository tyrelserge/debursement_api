package com.lin_q.debursement_api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "office")
public class Office implements Serializable {
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "office_id")    
    private Integer officeId;    
    @Column(name = "user_id")    
    private Integer userId;    
    @Column(name = "office_name")    
    private String officeName;    
    @Column(name = "created_on") 
    private Date createdOn; 
    @Column(name = "updated_on") 
    private Date updatedOn; 

    @ManyToOne(targetEntity = Department.class, cascade = {CascadeType.ALL}) 
    @JoinColumn(name = "department_id", referencedColumnName = "department_id") 
    private Department department; 

    @ManyToOne(targetEntity = Profile.class, cascade = {CascadeType.ALL}) 
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id") 
    private Profile profile; 

    public void setOfficeId(Integer officeId) { this.officeId = officeId; } 
    public void setUserId(Integer userId) { this.userId = userId; } 
    public void setOfficeName(String officeName) { this.officeName = officeName; } 
    public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
    public void setUpdatedOn(Date updatedOn) { this.updatedOn = updatedOn; } 
    public void setDepartment(Department department) { this.department = department; } 
    public void setProfile(Profile profile) { this.profile = profile; } 
    
    public Integer getOfficeId() {
    return this.officeId;
    } 
    public Integer getUserId() {
    return this.userId;
    } 
    public String getOfficeName() {
    return this.officeName;
    } 
    public Date getCreatedOn() {
    return this.createdOn;
    } 
    public Date getUpdatedOn() {
    return this.updatedOn;
    }

    public Department getDepartment() {
    return this.department;
    }
    
    public Profile getProfile() {
    return this.profile;
    }
}

package com.lin_q.debursement_api.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User {
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "user_id")    
    private Integer userId;    
    private String lastname;    
    private String firstname;    
    private String gender;    
    private String civility;    
    private String email; 
    private String mobile; 
    @Column(name = "created_on") 
    private Date createdOn; 
    @Column(name = "updated_on") 
    private Date updatedOn; 
    private String status; 

    @OneToMany(targetEntity = Office.class) 
    @JoinTable(name = "user_office", joinColumns = {
    @JoinColumn(name = "user_id")}, inverseJoinColumns = {
    @JoinColumn(name = "office_id")}) 
    private List<Office> offices; 

    public void setUserId(Integer userId) { this.userId = userId; } 
    public void setLastname(String lastname) { this.lastname = lastname; } 
    public void setFirstname(String firstname) { this.firstname = firstname; } 
    public void setGender(String gender) { this.gender = gender; } 
    public void setCivility(String civility) { this.civility = civility; } 
    public void setEmail(String email) { this.email = email; } 
    public void setMobile(String mobile) { this.mobile = mobile; } 
    public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
    public void setUpdatedOn(Date updatedOn) { this.updatedOn = updatedOn; } 
    public void setStatus(String status) { this.status = status; } 
    public void setOffices(List<Office> offices) { this.offices = offices; } 

    public User() {}
    
    public User(Integer userId, String lastname, String firstname, String gender, String civility, String email, String mobile, Date createdOn, Date updatedOn, String status, List<Office> offices) {
    this.userId = userId; this.lastname = lastname; this.firstname = firstname; this.gender = gender; this.civility = civility; this.email = email; this.mobile = mobile; this.createdOn = createdOn; this.updatedOn = updatedOn; this.status = status; this.offices = offices;
    }
    
    public Integer getUserId() {
    return this.userId;
    } 
    public String getLastname() { return this.lastname; }
    
    public String getFirstname() { return this.firstname; }
    
    public String getGender() { return this.gender; }
    
    public String getCivility() { return this.civility; }
    
    public String getEmail() { return this.email; } 
    public String getMobile() {
    return this.mobile;
    } 
    public Date getCreatedOn() {
    return this.createdOn;
    }
    
    public Date getUpdatedOn() { return this.updatedOn; } 
    public String getStatus() {
    return this.status;
    }

    public List<Office> getOffices() {
    return this.offices;
    }
}
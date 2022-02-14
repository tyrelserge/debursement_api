package com.lin_q.debursement_api.model;

import java.util.Date;

public class UserModel { private Integer userId;
    private String lastname;
    private String firstname;
    private String gender;

    public void setUserId(Integer userId) { this.userId = userId; } private String email; private String mobile; private Date createdOn; private Date updatedOn; private String status; 
    public void setLastname(String lastname) { this.lastname = lastname; } 
    public void setFirstname(String firstname) { this.firstname = firstname; } 
    public void setGender(String gender) { this.gender = gender; } 
    public void setEmail(String email) { this.email = email; } 
    public void setMobile(String mobile) { this.mobile = mobile; } 
    public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
    public void setUpdatedOn(Date updatedOn) { this.updatedOn = updatedOn; } 
    public void setStatus(String status) { this.status = status; } 

    public Integer getUserId() { return this.userId; }
    public String getLastname() { return this.lastname; }
    public String getFirstname() { return this.firstname; }
    public String getGender() { return this.gender; }
    public String getEmail() { return this.email; } 
    public String getMobile() { return this.mobile; }
    public Date getCreatedOn() { return this.createdOn; }
    public Date getUpdatedOn() { return this.updatedOn; } 
    public String getStatus() { return this.status; } 
}

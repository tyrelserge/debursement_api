package com.lin_q.debursement_api.model;

import java.util.Optional;

public class UserReq {

    private String lastname;
    private String firstname;
    private String civility;
    private String email; 
    private String mobile; 
    private String password; 
    private Optional<Integer[]> officeIds; 
    
    public void setLastname(String lastname) { this.lastname = lastname; } 
    public void setFirstname(String firstname) { this.firstname = firstname; } 
    public void setCivility(String civility) { this.civility = civility; } 
    public void setEmail(String email) { this.email = email; } 
    public void setMobile(String mobile) { this.mobile = mobile; } 
    public void setPassword(String password) { this.password = password; } 
    public void setOfficeIds(Optional<Integer[]> officeIds) { this.officeIds = officeIds; } 

    public String getLastname() { return this.lastname; }
    public String getFirstname() { return this.firstname; }
    public String getCivility() { return this.civility; }
    public String getEmail() { return this.email; }
    public String getMobile() { return this.mobile; }
    public String getPassword() { return this.password; } 
    public Optional<Integer[]> getOfficeIds() { return this.officeIds; }
}
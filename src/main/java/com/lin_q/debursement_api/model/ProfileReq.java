package com.lin_q.debursement_api.model;


public class ProfileReq {
private Integer userId;
private String profileName;
private String profileLevel;


public void setUserId(Integer userId) { this.userId = userId; } 
public void setProfileName(String profileName) { this.profileName = profileName; } 
public void setProfileLevel(String profileLevel) { this.profileLevel = profileLevel; } 

public ProfileReq() {} 
public ProfileReq(Integer userId, String profileName, String profileLevel) {
this.userId = userId; this.profileName = profileName; this.profileLevel = profileLevel;
}

public Integer getUserId() { return this.userId; }

public String getProfileName() { return this.profileName; } 
public String getProfileLevel() {
return this.profileLevel;
}
}

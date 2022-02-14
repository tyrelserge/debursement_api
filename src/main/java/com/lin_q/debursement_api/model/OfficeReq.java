package com.lin_q.debursement_api.model;

import com.lin_q.debursement_api.entity.Department;

public class OfficeReq {
  
    
    private Integer userId;
  
    
    private Integer departmentId;
  
  
    public void setUserId(Integer userId) { 
        this.userId = userId; 
    } 
    
    private Integer profileId; 
    
    private String officeName; 
    
    private Department Department; 

    public void setDepartmentId(Integer departmentId) { 
        this.departmentId = departmentId; 
    } 
    public void setProfileId(Integer profileId) { 
        this.profileId = profileId; 
    } 
    public void setOfficeName(String officeName) { 
        this.officeName = officeName; 
    } 
    public void setDepartment(Department department) { 
        this.Department = department; 
    } 
  
    public Integer getUserId() { 
        return this.userId; }
  
    public Integer getDepartmentId() { 
        return this.departmentId; }
  
    public Integer getProfileId() { 
        return this.profileId; }
  
    public String getOfficeName() { 
        return this.officeName; 
    } 
    public Department getDepartment() {
    return this.Department;
  }
}
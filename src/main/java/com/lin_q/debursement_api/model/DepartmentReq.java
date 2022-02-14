package com.lin_q.debursement_api.model;


public class DepartmentReq {
    
    private Integer userId;
    private String departmentName;

    public void setUserId(Integer userId) { this.userId = userId; } 
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; } 

    public DepartmentReq() {} 
    public DepartmentReq(Integer userId, String departmentName) {
    this.userId = userId; this.departmentName = departmentName;
    }

    public Integer getUserId() { return this.userId; } 
    public String getDepartmentName() {
    return this.departmentName;
    }
}

package com.lin_q.debursement_api.model;


public class RoleReq {
    private Integer userId;
    private String roleName;
    private Integer roleLevel;


    public void setUserId(Integer userId) { this.userId = userId; } 
    public void setRoleName(String roleName) { this.roleName = roleName; } 
    public void setRoleLevel(Integer roleLevel) { this.roleLevel = roleLevel; } 

    public RoleReq() {} 
    public RoleReq(Integer userId, String roleName, Integer roleLevel) {
        this.userId = userId; this.roleName = roleName; this.roleLevel = roleLevel;
    }

    public Integer getUserId() { return this.userId; }
    public String getRoleName() { return this.roleName; } 
    public Integer getRoleLevel() {return this.roleLevel;}
}

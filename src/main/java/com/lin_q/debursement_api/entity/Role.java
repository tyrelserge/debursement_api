package com.lin_q.debursement_api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "role_level") 
    private Integer roleLevel; 
    @Column(name = "created_date") 
    private Date createdDate; 
    @Column(name = "updated_date") 

    public void setRoleId(Integer roleId) { this.roleId = roleId; } 
    private Date updatedDate; 
    public void setUserId(Integer userId) { this.userId = userId; } 
    public void setRoleName(String roleName) { this.roleName = roleName; } 
    public void setRoleLevel(Integer roleLevel) { this.roleLevel = roleLevel; } 
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; } 
    public void setUpdatedDate(Date updatedDate) { this.updatedDate = updatedDate; } 

    public Integer getRoleId() {
    return this.roleId;
    } 
    public Integer getUserId() {
    return this.userId;
    } 
    public String getRoleName() {
    return this.roleName;
    } 
    public Integer getRoleLevel() {
    return this.roleLevel;
    } 
    public Date getCreatedDate() {
    return this.createdDate;
    } 
    public Date getUpdatedDate() {
    return this.updatedDate;
    }
}
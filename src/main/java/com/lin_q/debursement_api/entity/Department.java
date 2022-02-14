package com.lin_q.debursement_api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")

public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Integer departmentId;
    @Column(name = "user_id")
    private Integer userId;


    public void setDepartmentId(Integer departmentId) { this.departmentId = departmentId; } @Column(name = "department_name") private String departmentName; @Column(name = "created_on") private Date createdOn; @Column(name = "updated_on") private Date updatedOn; 
    public void setUserId(Integer userId) { this.userId = userId; } 
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; } 
    public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
    public void setUpdatedOn(Date updatedOn) { this.updatedOn = updatedOn; } 


    public Integer getDepartmentId() {
        return this.departmentId;
    } 
    public Integer getUserId() {
        return this.userId;
    } 
    public String getDepartmentName() {
        return this.departmentName;
    } 
    public Date getCreatedOn() {
        return this.createdOn;
    } 
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
}

package com.lin_q.debursement_api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "department")
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Integer departmentId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "created_on")
    private Date createdOn; 
    @Column(name = "updated_on")
    private Date updatedOn;
}

package com.lin_q.debursement_api.entity;

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
    private Date updatedDate;
}

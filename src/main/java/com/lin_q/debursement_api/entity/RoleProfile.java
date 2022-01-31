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
@Table(name = "role_profile")
public class RoleProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleprofile_id")
    private Integer roleprofileId;
    @Column(name = "roleprofile_name")
    private String roleprofileName;
    @Column(name = "role_level")
    private String roleLevel;
    @Column(name = "created_on")
    private Date createdOn; 
    @Column(name = "updated_on")
    private Date updatedOn;
}

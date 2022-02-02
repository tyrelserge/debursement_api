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
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Integer profileId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "profile_name")
    private String profileName;
    @Column(name = "profile_level")
    private String profileLevel;
    @Column(name = "created_on")
    private Date createdOn; 
    @Column(name = "updated_on")
    private Date updatedOn;
}

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
@Table(name = "role_level")
public class RoleLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "level_id")
    private Integer levelId;
    @Column(name = "level_name") 
    private String level_name;
    @Column(name = "level_value")
    private Integer levelValue;
    @Column(name = "created_date")
    private Date createdDate; 
    @Column(name = "updated_date")
    private Date updateDdate;
}

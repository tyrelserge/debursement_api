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
@Table(name = "office")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "office_id")
    private Integer officeId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "service_id")
    private Integer serviceId;
    @Column(name = "roleprofile_id")
    private Integer roleprofileId;
    @Column(name = "office_name")
    private String officeName;
    @Column(name = "created_on")
    private Date  createdOn; 
    @Column(name = "update_on")
    private Date  updateOn;
}

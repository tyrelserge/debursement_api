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
@Table(name = "general_setting")
public class GeneralSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "general_setting_id")
    private Integer generalSettingId;
    private String currency;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "created_on")
    private Date createdOn; 
    @Column(name = "updated_on")
    private Date updatedOn; 
    @Column(name = "status")
    private String status;
}

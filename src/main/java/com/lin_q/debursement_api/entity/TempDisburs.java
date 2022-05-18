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
@Table(name = "temp_disburs")
public class TempDisburs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "temp_disb_id")
    private Integer tempDisbId;
    @Column(name = "treated_by")
    private Integer treatedBy;
    @Column(name = "temp_fullname")
    private String tempFullname;
    @Column(name = "temp_mobile")
    private String tempMobile;
    @Column(name = "temp_amount")
    private String tempAmount;
    @Column(name = "temp_reason")
    private String tempReason;
    @Column(name = "temp_assignment")
    private String tempAssignment;
    @Column(name = "temp_groupbudget")
    private String tempGroupbudget;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "temp_status")
    private String tempStatus;
    @Column(name = "update_on")
    private Date updateOn;    
    @Column(name = "claimant_token")
    private String claimantToken;
}

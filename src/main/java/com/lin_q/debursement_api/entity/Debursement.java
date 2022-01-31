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
@Table(name = "debursement")
public class Debursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "debursement_id")
    private Integer debursementId;
    @Column(name = "budgindex_id")
    private Integer budgindexId;
    @Column(name = "user_id")
    private Integer userId;
    private String registration;
    @Column(name = "created_on")
    private Date createdOn; 
    private String reason;
    @Column(name = "amount_requested")
    private Integer amountRequested;
    @Column(name = "updated_on")
    private Date updatedOn; 
    @Column(name = "amount_approved")
    private Integer amountApproved;
    @Column(name = "recipient_id")
    private Integer recipientId;
    @Column(name = "activate_debursement")
    private Date activateDebursement;
}

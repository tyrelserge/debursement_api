package com.lin_q.debursement_api.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
    private String identifier;    
    private String reason;
    @Column(name = "amount_requested")    
    private Integer amountRequested;
    @Column(name = "recipient_id")    
    private Integer recipientId;
    @Column(name = "created_on")    
    private Date createdOn; 
    
    @OneToMany(targetEntity = ReasonItems.class, cascade = {CascadeType.ALL}) 
    @JoinColumn(name = "debursement_id", referencedColumnName = "debursement_id")   
    private List<ReasonItems> reasonItems; 

    // at the first validation (1)

    @Column(name = "amount_approved")   
    private Integer amountApproved;
    @Column(name = "activate_debursement")  
    private Date activateDebursement; 
    
    // at each step of validation (123..n)

    @Column(name = "updated_on")    
    private Date updatedOn; 
    private String status;      // 1 submitted | 2 approuved | confimed | (rejected | treated)
    
    @OneToMany(targetEntity = ValidationAction.class, cascade = {CascadeType.ALL}) 
    @JoinColumn(name = "debursement_id", referencedColumnName = "debursement_id")   
    private List<ValidationAction> validations; 

    @Column(name = "current_step") 
    private Integer currentStep;
    
 }
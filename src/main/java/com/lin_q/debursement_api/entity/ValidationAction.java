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
@Table(name = "validation_action")
public class ValidationAction { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_id")
    private Integer actionId; 
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "debursement_id")
    private Integer debursementId;
    @Column(name = "action_type")
    private Integer actionType;
    @Column(name = "action_value") 
    private String actionValue; 
    private String observation; 
    @Column(name = "validated_date") 
    private Date validatedDate; 
    @Column(name = "updated_on") 
    private Date updatedOn; 
}
package com.lin_q.debursement_api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


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


    public void setActionId(Integer actionId) { 
        this.actionId = actionId; 
    } 
    @Column(name = "action_value") 
    private String actionValue; 
    private String observation; 
    @Column(name = "validated_date") 
    private Date validatedDate; 
    @Column(name = "updated_on") 
    private Date updatedOn; 
    public void setUserId(Integer userId) { 
        this.userId = userId; 
    } 
    public void setDebursementId(Integer debursementId) { 
        this.debursementId = debursementId; 
    } 
    public void setActionType(Integer actionType) { 
        this.actionType = actionType; 
    } 
    public void setActionValue(String actionValue) { 
        this.actionValue = actionValue; 
    } 
    public void setObservation(String observation) { 
        this.observation = observation; 
    } 
    public void setValidatedDate(Date validatedDate) { 
        this.validatedDate = validatedDate; 
    } 
    public void setUpdatedOn(Date updatedOn) { 
        this.updatedOn = updatedOn; 
    } 

    public Integer getActionId() {

        return this.actionId;

    } 
    public Integer getUserId() {

        return this.userId;

    } 
    public Integer getDebursementId() {

        return this.debursementId;

    } 
    public Integer getActionType() {

        return this.actionType;
}

    public String getActionValue() { 

        return this.actionValue; 
    } 
    public String getObservation() {

        return this.observation;

    } 
    public Date getValidatedDate() {

        return this.validatedDate;

    } 
    public Date getUpdatedOn() {

        return this.updatedOn;

    } 
}
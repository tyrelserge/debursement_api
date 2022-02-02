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
@Table(name = "action")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_id")
    private Integer actionId;
    @Column(name = "action_type")
    private String actionType;
    @Column(name = "action_value")
    private String actionValue;
    @Column(name = "created_on")
    private Date createdOn; 
    @Column(name = "updated_on")
    private Date updatedOn;
}

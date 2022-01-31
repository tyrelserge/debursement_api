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
@Table(name = "validation_chain")
public class ValidationChain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chain_id")
    private Integer chainId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "debursement_id")
    private Integer debursementId;
    @Column(name = "action_id")
    private Integer actionId;
    private String observation;
    @Column(name = "validated_date")
    private Date validatedDate;
    @Column(name = "updated_on")
    private Date updatedOn;
}

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
@Table
public class ReasonItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reasonitem_id")
    private Integer reasonitemId;
    @Column(name = "debursement_id")
    private Integer debursementId;
    private String designation;
    private Integer unitprice;
    private Integer quatity;
    private Integer totalprice;
    private Date createdOn;
}

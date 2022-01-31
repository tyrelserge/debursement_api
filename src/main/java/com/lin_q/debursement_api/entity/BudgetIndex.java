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
@Table(name = "budget_index")
public class BudgetIndex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budgindex_id")
    private Integer budgindexId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "budgsector_id")
    private Integer budgsector_id;
    @Column(name = "groupedbubget_id")
    private Integer groupedbubgetId;
    @Column(name = "budgindex_name")
    private String budgindexName;
    @Column(name = "budgetindex_description")
    private String budgetindexDescription;
    @Column(name = "budgindex_periodic")
    private Integer budgindexPeriodic;
    @Column(name = "debitem_value")
    private Integer debitemValue;
    @Column(name = "created_on")
    private Date createdOn; 
    @Column(name = "updated_on")
    private Date updatedOn; 
    private String status;
}

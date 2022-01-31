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
@Table(name = "grouped_budget")
public class GroupedBudget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupedbudget_id")
    private Integer groupedbudgetId;
    @Column(name = "budgsector_id")
    private Integer budgsectorId;
    @Column(name = "groupedbudget_name")
    private String groupedbudgetName;
    @Column(name = "groupedbudget_description")
    private String groupedbudgetDescription;
    @Column(name = "groupedbudget_periodic")
    private Integer groupedbudgetPeriodic;
    @Column(name = "groupedbudget_value")
    private Integer groupedbudgetValue;
    @Column(name = "created_on")
    private Date createdOn; 
    @Column(name = "updated_on")
    private Date updatedOn; 
    @Column(name = "status")
    private String status;
}

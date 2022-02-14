package com.lin_q.debursement_api.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


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
    private Integer budgsectorId;
    @Column(name = "groupedbudget_id")
    private Integer groupedbudgetId;
    @Column(name = "budgindex_name")
    private String budgindexName;
    @Column(name = "budgindex_description") 
    private String budgindexDescription; 
    @Column(name = "budgindex_value") 
    private Integer budgindexValue; 
    @Column(name = "created_on") 
    private Date createdOn; 
    @Column(name = "updated_on") 
    private Date updatedOn; 
    private String status; 
    
    @OneToMany(targetEntity = Renewal.class) 
    @JoinTable(name = "group_renewal", joinColumns = {
    @JoinColumn(name = "groupedbudget_id")}, inverseJoinColumns = {
    @JoinColumn(name = "renewal_id")}) 
    private List<Renewal> renewal; 

    public void setBudgindexId(Integer budgindexId) { this.budgindexId = budgindexId; } 
    public void setUserId(Integer userId) { this.userId = userId; } 
    public void setBudgsectorId(Integer budgsectorId) { this.budgsectorId = budgsectorId; } 
    public void setGroupedbudgetId(Integer groupedbudgetId) { this.groupedbudgetId = groupedbudgetId; } 
    public void setBudgindexName(String budgindexName) { this.budgindexName = budgindexName; } 
    public void setBudgindexDescription(String budgindexDescription) { this.budgindexDescription = budgindexDescription; } 
    public void setBudgindexValue(Integer budgindexValue) { this.budgindexValue = budgindexValue; } 
    public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
    public void setUpdatedOn(Date updatedOn) { this.updatedOn = updatedOn; } 
    public void setStatus(String status) { this.status = status; } 
    public void setRenewal(List<Renewal> renewal) { this.renewal = renewal; } 

    public BudgetIndex() {}
    public BudgetIndex(Integer budgindexId, Integer userId, Integer budgsectorId, Integer groupedbudgetId, String budgindexName, String budgindexDescription, Integer budgindexValue, Date createdOn, Date updatedOn, String status, List<Renewal> renewal) {
        this.budgindexId = budgindexId; this.userId = userId; this.budgsectorId = budgsectorId; this.groupedbudgetId = groupedbudgetId; this.budgindexName = budgindexName; this.budgindexDescription = budgindexDescription; this.budgindexValue = budgindexValue; this.createdOn = createdOn; this.updatedOn = updatedOn; this.status = status; this.renewal = renewal;
    }

    public Integer getBudgindexId() {
    return this.budgindexId;
    } 
    public Integer getUserId() {
    return this.userId;
    } 
    public Integer getBudgsectorId() {
    return this.budgsectorId;
    } 
    public Integer getGroupedbudgetId() {
    return this.groupedbudgetId;
    } 
    public String getBudgindexName() {
    return this.budgindexName;
    } 
    public String getBudgindexDescription() {
    return this.budgindexDescription;
    } 
    public Integer getBudgindexValue() {
    return this.budgindexValue;
    } 
    public Date getCreatedOn() {
    return this.createdOn;
    }
    public Date getUpdatedOn() { 
        return this.updatedOn; 
    } 
    public String getStatus() {
    return this.status;
    }

    public List<Renewal> getRenewal() {
    return this.renewal;
    }
}
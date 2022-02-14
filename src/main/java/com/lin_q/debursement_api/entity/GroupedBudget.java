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
@Table(name = "grouped_budget")    
public class GroupedBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupedbudget_id")
    private Integer groupedbudgetId;
    @Column(name = "user_id")    
    private Integer userId;
    @Column(name = "budgsector_id")    
    private Integer budgsectorId;
    @Column(name = "groupedbudget_name")    
    private String groupedbudgetName;
    @Column(name = "groupedbudget_description")    
    private String groupedbudgetDescription;
    
    @Column(name = "groupedbudget_value") 
    private Integer groupedbudgetValue; 
    @Column(name = "created_on") 
    private Date createdOn; 
    @Column(name = "updated_on") 
    private Date updatedOn; 
    @Column(name = "status") 
    private String status; 
    @OneToMany(targetEntity = Renewal.class) 
    @JoinTable(name = "group_renewal", joinColumns = {
    @JoinColumn(name = "groupedbudget_id")}, inverseJoinColumns = {
    @JoinColumn(name = "renewal_id")}) 
    private List<Renewal> renewal; 

    public void setGroupedbudgetId(Integer groupedbudgetId) { this.groupedbudgetId = groupedbudgetId; } 
    public void setUserId(Integer userId) { this.userId = userId; } 
    public void setBudgsectorId(Integer budgsectorId) { this.budgsectorId = budgsectorId; } 
    public void setGroupedbudgetName(String groupedbudgetName) { this.groupedbudgetName = groupedbudgetName; } 
    public void setGroupedbudgetDescription(String groupedbudgetDescription) { this.groupedbudgetDescription = groupedbudgetDescription; } 
    public void setGroupedbudgetValue(Integer groupedbudgetValue) { this.groupedbudgetValue = groupedbudgetValue; } 
    public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
    public void setUpdatedOn(Date updatedOn) { this.updatedOn = updatedOn; } 
    public void setStatus(String status) { this.status = status; } 
    public void setRenewal(List<Renewal> renewal) { this.renewal = renewal; } 
    
    public Integer getGroupedbudgetId() {
    return this.groupedbudgetId;
    } 
    public Integer getUserId() {
    return this.userId;
    } 
    public Integer getBudgsectorId() {
    return this.budgsectorId;
    } 
    public String getGroupedbudgetName() {
    return this.groupedbudgetName;
    } 
    public String getGroupedbudgetDescription() {
    return this.groupedbudgetDescription;
    } 
    public Integer getGroupedbudgetValue() {
    return this.groupedbudgetValue;
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

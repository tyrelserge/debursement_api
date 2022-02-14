package com.lin_q.debursement_api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "budgetary_sector")
public class BudgetarySector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budgsector_id")
    private Integer budgsectorId;
    @Column(name = "budgsector_name")
    private String budgsectorName;
    @Column(name = "budgsector_description")
    private String budgsectorDescription;
    public void setBudgsectorId(Integer budgsectorId) { this.budgsectorId = budgsectorId; } 
    @Column(name = "created_on") 
    private Date createdOn; 
    @Column(name = "updated_on") 
    private Date updatedOn; 
    private String status; 
    
    public void setBudgsectorName(String budgsectorName) { this.budgsectorName = budgsectorName; } 
    public void setBudgsectorDescription(String budgsectorDescription) { this.budgsectorDescription = budgsectorDescription; } 
    public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
    public void setUpdatedOn(Date updatedOn) { this.updatedOn = updatedOn; } 
    public void setStatus(String status) { this.status = status; } 

    public Integer getBudgsectorId() { return this.budgsectorId; } 
    public String getBudgsectorName() { return this.budgsectorName; } 
    public String getBudgsectorDescription() {return this.budgsectorDescription;} 
    public Date getCreatedOn() { return this.createdOn; }
    public Date getUpdatedOn() { return this.updatedOn; } 
    public String getStatus() { return this.status; }
}

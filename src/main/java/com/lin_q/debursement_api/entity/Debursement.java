package com.lin_q.debursement_api.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "debursement")
public class Debursement { 


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "debursement_id")
    private Integer debursementId; 
    @Column(name = "budgindex_id")    
    private Integer budgindexId;
    @Column(name = "user_id")    
    private Integer userId;    
    private String identifier;    
    private String reason;
    @Column(name = "amount_requested")    
    private Integer amountRequested;
    @Column(name = "recipient_id")    
    private Integer recipientId;
    public void setDebursementId(Integer debursementId) { 
        this.debursementId = debursementId; 
    } 
    @Column(name = "created_on")    
    private Date createdOn; 
    @Column(name = "amount_approved")   
    private Integer amountApproved; 
    @Column(name = "activate_debursement")  
    private Date activateDebursement; 
    @Column(name = "updated_on")    
    private Date updatedOn; 
    @Column(name = "current_step")  
    private Integer currentStep;     
    private String status; 

    @OneToMany(targetEntity = ReasonItems.class, cascade = {CascadeType.ALL}) 
    @JoinColumn(name = "debursement_id", referencedColumnName = "debursement_id")   
    private List<ReasonItems> reasonItems; 
    @OneToMany(targetEntity = ValidationAction.class, cascade = {CascadeType.ALL}) 
    @JoinColumn(name = "debursement_id", referencedColumnName = "debursement_id")   
    private List<ValidationAction> validations; 
    public void setBudgindexId(Integer budgindexId) { 
        this.budgindexId = budgindexId; 
    } 
    public void setUserId(Integer userId) { 
        this.userId = userId; 
    } 
    public void setIdentifier(String identifier) { 
        this.identifier = identifier; 
    } 
    public void setReason(String reason) { 
        this.reason = reason; 
    } 
    public void setAmountRequested(Integer amountRequested) { 
        this.amountRequested = amountRequested; 
    } 
    public void setRecipientId(Integer recipientId) { 
        this.recipientId = recipientId; 
    } 
    public void setCreatedOn(Date createdOn) { 
        this.createdOn = createdOn; 
    } 
    public void setAmountApproved(Integer amountApproved) { 
        this.amountApproved = amountApproved; 
    } 
    public void setActivateDebursement(Date activateDebursement) { 
        this.activateDebursement = activateDebursement; 
    } 
    public void setUpdatedOn(Date updatedOn) { 
        this.updatedOn = updatedOn; 
    } 
    public void setCurrentStep(Integer currentStep) { 
        this.currentStep = currentStep; 
    } 
    public void setStatus(String status) { 
        this.status = status; 
    } 
    public void setReasonItems(List<ReasonItems> reasonItems) { 
        this.reasonItems = reasonItems; 
    } 
    public void setValidations(List<ValidationAction> validations) { 
        this.validations = validations; 
    }

    public Integer getDebursementId() {
return this.debursementId;

} 
    public Integer getBudgindexId() {
return this.budgindexId;
}

    public Integer getUserId() { 
        return this.userId; }

    public String getIdentifier() { 
        return this.identifier; 
    } 
    public String getReason() {
return this.reason;

} 
    public Integer getAmountRequested() {
return this.amountRequested;

} 
    public Integer getRecipientId() {
return this.recipientId;

} 
    public Date getCreatedOn() {
return this.createdOn;

} 
    public Integer getAmountApproved() {
return this.amountApproved;

} 
    public Date getActivateDebursement() {
return this.activateDebursement;

} 
    public Date getUpdatedOn() {
return this.updatedOn;
}

    public Integer getCurrentStep() { 
        return this.currentStep; 
    } 
    public String getStatus() {
return this.status;
}


    public List<ReasonItems> getReasonItems() {
return this.reasonItems;
}


    public List<ValidationAction> getValidations() {
return this.validations;

} }
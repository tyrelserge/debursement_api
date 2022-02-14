package com.lin_q.debursement_api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.lin_q.debursement_api.entity.Debursement;
import com.lin_q.debursement_api.entity.ReasonItems;
import com.lin_q.debursement_api.entity.ValidationAction;
import com.lin_q.debursement_api.model.DebursementReq;
import com.lin_q.debursement_api.model.ReasonItemsReq;
import com.lin_q.debursement_api.model.ValidationReq;
import com.lin_q.debursement_api.repository.DebursementRepository;
import com.lin_q.debursement_api.repository.ReasonItemsRepository;
import com.lin_q.debursement_api.repository.ValidationActionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisbursementServiceImpl implements DisbursementService {
    
  @Autowired
  private DebursementRepository debursementRepository;
  @Autowired
  private ReasonItemsRepository reasonItemsRepository;
  @Autowired
  private ValidationActionRepository validationActionRepository;
  
  public List<Debursement> getUserDisbursementRequestList(Integer userId) {
    return this.debursementRepository.fetchUserDisbursementRequestList(userId);
  }

  
  public Debursement getUserDisbursementRequest(Integer disbursId) {
    return this.debursementRepository.findById(disbursId).get();
  }


  
  public Debursement toDisbursementRequest(DebursementReq debursementData) {
    Debursement debursement = new Debursement();
    
    debursement.setBudgindexId(debursementData.getBudgindexId());
    debursement.setUserId(debursementData.getUserId());
    debursement.setIdentifier(debursementData.getIdentifier());
    debursement.setReason(debursementData.getReason());
    debursement.setAmountRequested(debursementData.getAmountRequested());
    debursement.setRecipientId(debursementData.getRecipientId());
    debursement.setCreatedOn(new Date());
    debursement.setStatus(debursementData.getStatus());
    
    Debursement savedDebursement = (Debursement)this.debursementRepository.save(debursement);
    Integer debursementId = savedDebursement.getDebursementId();
    List<ReasonItemsReq> reasonDataList = debursementData.getReasonItems();
    
    if (reasonDataList != null && !reasonDataList.isEmpty()) {
      List<ReasonItems> reasonItemsList = new ArrayList<>();
      for (ReasonItemsReq reasonData : reasonDataList) {
        ReasonItems reasonItems = toSaveReasonItems(debursementId, reasonData);
        reasonItemsList.add(reasonItems);
      } 
      savedDebursement.setReasonItems(reasonItemsList);
    } 
    
    return savedDebursement;
  }


  
  public Debursement toUpdateDisbursementRequest(Integer disbursId, DebursementReq debursementData) {
    Optional<Debursement> currendDebursement = this.debursementRepository.findById(disbursId);
    
    if (!currendDebursement.isPresent()) {
      return null;
    }
    Debursement debursement = new Debursement();
    
    debursement.setDebursementId(disbursId);
    debursement.setBudgindexId(debursementData.getBudgindexId());
    debursement.setUserId(debursementData.getUserId());
    debursement.setIdentifier(debursementData.getIdentifier());
    debursement.setReason(debursementData.getReason());
    debursement.setAmountRequested(debursementData.getAmountRequested());
    debursement.setRecipientId(debursementData.getRecipientId());
    debursement.setCreatedOn(((Debursement)currendDebursement.get()).getCreatedOn());
    debursement.setUpdatedOn(new Date());
    debursement.setAmountApproved(((Debursement)currendDebursement.get()).getAmountApproved());
    debursement.setActivateDebursement(((Debursement)currendDebursement.get()).getActivateDebursement());
    debursement.setCurrentStep(((Debursement)currendDebursement.get()).getCurrentStep());
    debursement.setReasonItems(((Debursement)currendDebursement.get()).getReasonItems());
    debursement.setStatus(debursementData.getStatus());
    
    Debursement savedDebursement = (Debursement)this.debursementRepository.save(debursement);
    Integer debursementId = savedDebursement.getDebursementId();
    List<ReasonItemsReq> reasonDataList = debursementData.getReasonItems();
    
    if (reasonDataList != null && !reasonDataList.isEmpty()) {
      List<ReasonItems> reasonItemsList = new ArrayList<>();
      for (ReasonItems reasonItems : ((Debursement)currendDebursement.get()).getReasonItems()) {
        reasonItemsList.add(reasonItems);
      }
      for (ReasonItemsReq reasonData : reasonDataList) {
        ReasonItems reasonItems = toSaveReasonItems(debursementId, reasonData);
        reasonItemsList.add(reasonItems);
      } 
      savedDebursement.setReasonItems(reasonItemsList);
    } 
    
    return savedDebursement;
  }


  
  public ReasonItems toSaveReasonItems(Integer debursementId, ReasonItemsReq reasonData) {
    Optional<Debursement> debursement = this.debursementRepository.findById(debursementId);
    
    if (!debursement.isPresent()) {
      return null;
    }
    ReasonItems reasonItems = new ReasonItems();
    
    int pu = reasonData.getUnitprice().intValue();
    int qt = reasonData.getQuatity().intValue();
    
    reasonItems.setDebursementId(debursementId);
    reasonItems.setDesignation(reasonData.getDesignation());
    reasonItems.setQuatity(reasonData.getQuatity());
    reasonItems.setUnitprice(reasonData.getUnitprice());
    reasonItems.setTotalprice(Integer.valueOf(pu * qt));
    reasonItems.setCreatedOn(new Date());
    reasonItems.setStatus(reasonData.getStatus());
    
    return (ReasonItems)this.reasonItemsRepository.save(reasonItems);
  }


  
  public ReasonItems toUpdateReasonItems(Integer reasonId, ReasonItemsReq reasonData) {
    Optional<ReasonItems> currentReason = this.reasonItemsRepository.findById(reasonId);
    
    if (!currentReason.isPresent()) {
      return null;
    }
    ReasonItems reasonItems = new ReasonItems();
    
    int pu = reasonData.getUnitprice().intValue();
    int qt = reasonData.getQuatity().intValue();
    
    reasonItems.setReasonitemId(reasonId);
    reasonItems.setDebursementId(((ReasonItems)currentReason.get()).getDebursementId());
    reasonItems.setDesignation(reasonData.getDesignation());
    reasonItems.setQuatity(reasonData.getQuatity());
    reasonItems.setUnitprice(reasonData.getUnitprice());
    reasonItems.setTotalprice(Integer.valueOf(pu * qt));
    reasonItems.setCreatedOn(((ReasonItems)currentReason.get()).getCreatedOn());
    reasonItems.setUpdatedOn(new Date());
    reasonItems.setStatus(reasonData.getStatus());
    
    return (ReasonItems)this.reasonItemsRepository.save(reasonItems);
  }


  
  public Debursement setDisbursementValidation(Integer disbursId, ValidationReq validateData) {
    Optional<Debursement> optional = this.debursementRepository.findById(disbursId);
    
    if (!optional.isPresent()) {
      return null;
    }
    Debursement currendDebursement = optional.get();
    
    Debursement debursement = new Debursement();
    
    debursement.setDebursementId(disbursId);
    debursement.setBudgindexId(currendDebursement.getBudgindexId());
    debursement.setUserId(currendDebursement.getUserId());
    debursement.setIdentifier(currendDebursement.getIdentifier());
    debursement.setReason(currendDebursement.getReason());
    debursement.setAmountRequested(currendDebursement.getAmountRequested());
    debursement.setCreatedOn(currendDebursement.getCreatedOn());
    debursement.setUpdatedOn(new Date());
    debursement.setAmountApproved(currendDebursement.getAmountApproved());
    debursement.setRecipientId(currendDebursement.getRecipientId());
    debursement.setActivateDebursement(currendDebursement.getActivateDebursement());
    debursement.setStatus(validateData.getActionValue());
    debursement.setReasonItems(currendDebursement.getReasonItems());
    
    Integer currentStep = currendDebursement.getCurrentStep();
    
    if (currentStep == null || currentStep.intValue() < 1) {
      debursement.setAmountApproved(validateData.getAmountApproved());
      debursement.setRecipientId(validateData.getRecipientId());
      debursement.setActivateDebursement(new Date());
      currentStep = Integer.valueOf(1);
    }
    else {
      
      debursement.setAmountApproved(currendDebursement.getAmountApproved());
      debursement.setRecipientId(currendDebursement.getRecipientId());
      debursement.setActivateDebursement(currendDebursement.getActivateDebursement());
      currentStep = Integer.valueOf(currentStep.intValue() + 1);
    } 

    
    debursement.setCurrentStep(currentStep);
    Debursement savedDebursement = (Debursement)this.debursementRepository.save(debursement);


    
    ValidationAction vAction = new ValidationAction();
    vAction.setUserId(validateData.getUserId());
    vAction.setDebursementId(disbursId);
    vAction.setActionType(currentStep);
    vAction.setActionValue(validateData.getActionValue());
    vAction.setObservation(validateData.getObservation());
    vAction.setValidatedDate(new Date());
    
    ValidationAction savedValidation = (ValidationAction)this.validationActionRepository.save(vAction);
    List<ValidationAction> validationToArray = new ArrayList<>();
    validationToArray.add(savedValidation);
    savedDebursement.setValidations(validationToArray);
    
    return savedDebursement;
  }
}
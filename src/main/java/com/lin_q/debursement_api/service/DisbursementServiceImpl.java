package com.lin_q.debursement_api.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.lin_q.debursement_api.entity.BudgetIndex;
import com.lin_q.debursement_api.entity.BudgetarySector;
import com.lin_q.debursement_api.entity.Debursement;
import com.lin_q.debursement_api.entity.ReasonItems;
import com.lin_q.debursement_api.entity.ValidationAction;
import com.lin_q.debursement_api.exception.ResourceNotFoundException;
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
    List<Debursement> disburs = this.debursementRepository.fetchUserDisbursementRequestList(userId);
    for(Debursement disb: disburs) {
      disb.setIdentifier(formatRefID(disb));
    }
    return disburs;
  }

  
  public Debursement getUserDisbursementRequest(Integer disbursId) {    
    Debursement disburs = this.debursementRepository.findById(disbursId).orElseThrow(
      ()->new ResourceNotFoundException("Disbursement not found"));
      disburs.setIdentifier(formatRefID(disburs));
      return disburs;
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

    Integer[] reasonIds = debursementData.getReasonItemsIds();
    
    if (reasonIds!=null && 0 < reasonIds.length) {
      List<ReasonItems> reasonItemsList = new ArrayList<>();
      for (Integer reasonId : reasonIds) {
        ReasonItems reasonItems = toAssignReasonItems(reasonId, debursementId);
        reasonItemsList.add(reasonItems);
      } 
      savedDebursement.setReasonItems(reasonItemsList);
    }  
    savedDebursement.setIdentifier(formatRefID(savedDebursement));
    return savedDebursement;
  }
  
  public Debursement toUpdateDisbursementRequest(Integer disbursId, DebursementReq debursementData) {
    Optional<Debursement> optional = this.debursementRepository.findById(disbursId);
    
    if (!optional.isPresent()) 
      return null;
    
    Debursement currendDebursement = optional.get();
    Debursement debursement = new Debursement();
    
    debursement.setDebursementId(disbursId);
    debursement.setBudgindexId(debursementData.getBudgindexId());
    debursement.setUserId(debursementData.getUserId());
    debursement.setIdentifier(debursementData.getIdentifier());
    debursement.setReason(debursementData.getReason());
    debursement.setAmountRequested(debursementData.getAmountRequested());
    debursement.setRecipientId(debursementData.getRecipientId());
    debursement.setCreatedOn(currendDebursement.getCreatedOn());
    debursement.setUpdatedOn(new Date());
    debursement.setAmountApproved(currendDebursement.getAmountApproved());
    debursement.setActivateDebursement(currendDebursement.getActivateDebursement());
    debursement.setCurrentStep(currendDebursement.getCurrentStep());
    debursement.setReasonItems(currendDebursement.getReasonItems());
    debursement.setStatus(debursementData.getStatus());
    
    Debursement savedDebursement = (Debursement)this.debursementRepository.save(debursement);
    Integer debursementId = savedDebursement.getDebursementId();

    Integer[] reasonIds = debursementData.getReasonItemsIds();

    List<ReasonItems> reasonItemsList = new ArrayList<>();
    
    for (ReasonItems reasonItems : currendDebursement.getReasonItems()) {
      reasonItemsList.add(reasonItems);
    }

    if (reasonIds != null && 0 < reasonIds.length) {
      for (Integer reasonId : reasonIds) {
        ReasonItems reasonItems = toAssignReasonItems(reasonId, debursementId);
        reasonItemsList.add(reasonItems);
      } 
      savedDebursement.setReasonItems(reasonItemsList);
    } 
    savedDebursement.setIdentifier(formatRefID(savedDebursement));
    return savedDebursement;
  }

  
  public ReasonItems toSaveReasonItems(ReasonItemsReq reasonData) {

    if(reasonData.getDebursementId()!=null) {
      Optional<Debursement> debursement = this.debursementRepository.findById(reasonData.getDebursementId());
      if (!debursement.isPresent()) 
        return null;
    }

    ReasonItems reasonItems = new ReasonItems();
    
    int pu = reasonData.getUnitprice().intValue();
    int qt = reasonData.getQuatity().intValue();
    
    reasonItems.setDebursementId(reasonData.getDebursementId());
    reasonItems.setDesignation(reasonData.getDesignation());
    reasonItems.setQuatity(reasonData.getQuatity());
    reasonItems.setUnitprice(reasonData.getUnitprice());
    reasonItems.setTotalprice(Integer.valueOf(pu * qt));
    reasonItems.setCreatedOn(new Date());
    reasonItems.setStatus(reasonData.getStatus());
    
    return (ReasonItems)this.reasonItemsRepository.save(reasonItems);
  }


  @Override
  public ReasonItems toAssignReasonItems(Integer reasonId, Integer debursementId) {
    Optional<ReasonItems> optional = this.reasonItemsRepository.findById(reasonId);
    
    if (!optional.isPresent()) 
      return null;
    
    ReasonItems currentReason = optional.get();
    if(currentReason.getDebursementId()!=null)
      return null;

    ReasonItems reasonItems = new ReasonItems();
    
    reasonItems.setReasonitemId(reasonId);
    reasonItems.setDebursementId(debursementId);
    reasonItems.setDesignation(currentReason.getDesignation());
    reasonItems.setQuatity(currentReason.getQuatity());
    reasonItems.setUnitprice(currentReason.getUnitprice());
    reasonItems.setTotalprice(currentReason.getTotalprice());
    reasonItems.setCreatedOn(currentReason.getCreatedOn());
    reasonItems.setUpdatedOn(new Date());
    reasonItems.setStatus(currentReason.getStatus());
    
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


  @Override
  public List<Debursement> getAllDisbursements() {
    List<Debursement> disburs = this.debursementRepository.findAll();
    for(Debursement disb: disburs) {
      disb.setIdentifier(formatRefID(disb));
    }
    return disburs;
  }

  
  @Override
  public List<Debursement> getDisbursementWaitingValidation() {
    List<Debursement> disburs = this.debursementRepository.fetchDisbursementWaitingValidation();
    for(Debursement disb: disburs) {
      disb.setIdentifier(formatRefID(disb));
    }
    return disburs;
  }

  
  public ValidationAction setDisbursementValidation(Integer disbursId, ValidationReq validateData) {
    
    Optional<Debursement> optional = this.debursementRepository.findById(disbursId);
    
    if (!optional.isPresent())
      return null;
    
    Debursement current = optional.get();    
    
    Integer amountApproved = current.getAmountApproved();
    Date activatedDate = current.getActivateDebursement();

    Integer currentStep = current.getCurrentStep()==null ? 0 :
                        Integer.parseInt(current.getCurrentStep().toString());

    if (currentStep.intValue() < 1) {
      amountApproved = validateData.getAmountApproved();
      activatedDate = new Date();
      currentStep = 1;
    }
    else {
      currentStep += 1;
    } 

    String action = validateData.getPayment()!=null && !validateData.getPayment().isBlank() 
    ? "treated" : validateData.getActionValue();

    Integer saveResponset = debursementRepository.updateDisbursementToValidation(
      disbursId,
      action.equalsIgnoreCase("rejected") ? null : amountApproved,
      activatedDate,
      currentStep,
      action,
      validateData.getPayment()!=null ? validateData.getPayment() : null
    );
    
    if (saveResponset!=1)
      return null;
      
    ValidationAction actionReq = new ValidationAction();
    actionReq.setDebursementId(disbursId);                              // request id
    actionReq.setUserId(validateData.getUserId());                      // validator id
    actionReq.setActionType(currentStep);                               // steps
    actionReq.setActionValue(action);                                   // approuved | verified | rejected
    actionReq.setObservation(validateData.getObservation());
    actionReq.setValidatedDate(new Date());
    
    ValidationAction savedValidation = validationActionRepository.save(actionReq);
    
    return savedValidation;
  }


  @Override
  public String getGeneratedRegistrationNumber() {
    String currentMonth = new SimpleDateFormat("MM").format(new Date());
    String currentYear = new SimpleDateFormat("yyyy").format(new Date());
    String deb = debursementRepository.fetchCurrentMonthLastDisbursement(currentYear, currentMonth);
    if (deb==null) return String.format("%03d", 1);

    Integer nextNumber = 0;
    try {
      nextNumber = Integer.parseInt(deb.toString()) + 1;
    } catch (Exception e) {
      nextNumber = 1;
    }    
    return String.format("%03d", nextNumber);
  }

  @Autowired
  private BudgetService budgetService;

  private String formatRefID(Debursement disburs) {
    BudgetIndex index = budgetService.getBudgetIndex(disburs.getBudgindexId());
    BudgetarySector sector = budgetService.getBudgetarySector(index.getBudgsectorId());
    return new SimpleDateFormat("yyMM").format(disburs.getCreatedOn()) + "/" + sector.getBudgsectorChar() + disburs.getIdentifier();
  }  

}
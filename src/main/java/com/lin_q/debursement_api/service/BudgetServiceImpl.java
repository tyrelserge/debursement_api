package com.lin_q.debursement_api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.lin_q.debursement_api.entity.BudgetIndex;
import com.lin_q.debursement_api.entity.BudgetarySector;
import com.lin_q.debursement_api.entity.GroupedBudget;
import com.lin_q.debursement_api.entity.Renewal;
import com.lin_q.debursement_api.exception.ResourceNotFoundException;
import com.lin_q.debursement_api.model.BudgetIndexReq;
import com.lin_q.debursement_api.model.GroupedBudgetReq;
import com.lin_q.debursement_api.model.RenewalReq;
import com.lin_q.debursement_api.model.SectorReq;
import com.lin_q.debursement_api.repository.BudgetIndexRepository;
import com.lin_q.debursement_api.repository.BudgetarySectorRepository;
import com.lin_q.debursement_api.repository.GroupedBudgetRepository;
import com.lin_q.debursement_api.repository.RenewalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetServiceImpl implements BudgetService {
  
  @Autowired
  private BudgetarySectorRepository budgetarySectorRepository;
  @Autowired
  private GroupedBudgetRepository groupedBudgetRepository;
  @Autowired
  private BudgetIndexRepository budgetIndexRepository;
  @Autowired
  private RenewalRepository renewalRepository;
  
  public List<BudgetarySector> getBudgetarySectorList() {
    return this.budgetarySectorRepository.findAll();
  }

  
  public BudgetarySector getBudgetarySector(Integer sectorId) {
    return (BudgetarySector)this.budgetarySectorRepository.findById(sectorId).orElseThrow(() -> new ResourceNotFoundException("ID not found"));
  }


  
  public BudgetarySector toCreateBudgetarySector(SectorReq sectorData) {
    BudgetarySector sector = new BudgetarySector();
    sector.setBudgsectorName(sectorData.getBudgsectorName());
    sector.setBudgsectorDescription(sectorData.getBudgsectorDescription());
    sector.setBudgsectorImg(sectorData.getBudgsectorImg());
    sector.setCreatedOn(new Date());
    sector.setStatus(sectorData.getStatus());
    return (BudgetarySector)this.budgetarySectorRepository.save(sector);
  }


  
  public BudgetarySector toUpdateBudgetarySector(Integer sectorId, SectorReq sectorData) {
    Optional<BudgetarySector> currentSector = this.budgetarySectorRepository.findById(sectorId);
    if (!currentSector.isPresent()) {
      return null;
    }
    BudgetarySector oldSector = currentSector.get();
    
    BudgetarySector sector = new BudgetarySector();
    
    sector.setBudgsectorId(oldSector.getBudgsectorId());
    sector.setBudgsectorName(sectorData.getBudgsectorName());
    sector.setBudgsectorDescription(sectorData.getBudgsectorDescription());
    sector.setBudgsectorImg(sectorData.getBudgsectorImg());
    sector.setCreatedOn(oldSector.getCreatedOn());
    sector.setUpdatedOn(new Date());
    sector.setStatus(sectorData.getStatus());
    
    return sector;
  }

  
  public List<GroupedBudget> getGroupedBudgetList() {
    return this.groupedBudgetRepository.findAll();
  }

  
  public GroupedBudget getGroupedBudget(Integer grbgId) {
    return (GroupedBudget)this.groupedBudgetRepository.findById(grbgId).orElseThrow(() -> new ResourceNotFoundException("ID not found"));
  }



  
  public GroupedBudget toCreateGroupedBudget(GroupedBudgetReq grpBudgetData) {
    GroupedBudget gbudget = new GroupedBudget();
    
    gbudget.setUserId(grpBudgetData.getUserId());
    gbudget.setBudgsectorId(grpBudgetData.getBudgsectorId());
    gbudget.setGroupedbudgetName(grpBudgetData.getGroupedbudgetName());
    gbudget.setGroupedbudgetDescription(grpBudgetData.getGroupedbudgetDescription());
    gbudget.setGroupedbudgetValue(grpBudgetData.getGroupedbudgetValue());
    gbudget.setStatus(grpBudgetData.getStatus());
    gbudget.setCreatedOn(new Date());
    gbudget.setStatus(grpBudgetData.getStatus());
    
    GroupedBudget savedGBudget = (GroupedBudget)this.groupedBudgetRepository.save(gbudget);
    Integer groupedbudgetId = savedGBudget.getGroupedbudgetId();
    RenewalReq renewalData = grpBudgetData.getRenewal();
    
    if (renewalData != null) {
      Renewal renewal = toCreateGroupedbudgetRenewal(groupedbudgetId, renewalData);
      List<Renewal> renewalList = new ArrayList<>();
      renewalList.add(renewal);
      savedGBudget.setRenewal(renewalList);
    } 
    
    return savedGBudget;
  }


  
  public GroupedBudget toUpdateGroupedBudget(Integer grbgId, GroupedBudgetReq grpBudgetData) {
    Optional<GroupedBudget> currendGroupedBudget = this.groupedBudgetRepository.findById(grbgId);
    if (!currendGroupedBudget.isPresent()) {
      return null;
    }
    GroupedBudget gbudget = new GroupedBudget();
    
    gbudget.setGroupedbudgetId(((GroupedBudget)currendGroupedBudget.get()).getGroupedbudgetId());
    gbudget.setUserId(grpBudgetData.getUserId());
    gbudget.setBudgsectorId(grpBudgetData.getBudgsectorId());
    gbudget.setGroupedbudgetName(grpBudgetData.getGroupedbudgetName());
    gbudget.setGroupedbudgetDescription(grpBudgetData.getGroupedbudgetDescription());
    gbudget.setGroupedbudgetValue(grpBudgetData.getGroupedbudgetValue());
    gbudget.setStatus(grpBudgetData.getStatus());
    gbudget.setCreatedOn(((GroupedBudget)currendGroupedBudget.get()).getCreatedOn());
    gbudget.setUpdatedOn(new Date());
    gbudget.setStatus(grpBudgetData.getStatus());
    
    return (GroupedBudget)this.groupedBudgetRepository.save(gbudget);
  }

  
  public List<BudgetIndex> getBudgetIndexList() {
    return this.budgetIndexRepository.findAll();
  }

  
  public BudgetIndex getBudgetIndex(Integer bgIndexId) {
    return (BudgetIndex)this.budgetIndexRepository.findById(bgIndexId).orElseThrow(() -> new ResourceNotFoundException("ID not found"));
  }



  
  public BudgetIndex toCreateBudgetIndex(BudgetIndexReq budgetIndexData) {
    BudgetIndex budgetIndex = new BudgetIndex();
    
    budgetIndex.setUserId(budgetIndexData.getUserId());
    budgetIndex.setBudgsectorId(budgetIndexData.getBudgsectorId());
    budgetIndex.setGroupedbudgetId(budgetIndexData.getGroupedbudgetId());
    budgetIndex.setBudgindexName(budgetIndexData.getBudgindexName());
    budgetIndex.setBudgindexDescription(budgetIndexData.getBudgindexDescription());
    budgetIndex.setBudgindexValue(budgetIndexData.getBudgindexValue());
    budgetIndex.setCreatedOn(new Date());
    budgetIndex.setStatus(budgetIndexData.getStatus());
    budgetIndex.setStatus(budgetIndexData.getStatus());
    
    BudgetIndex savedBudgetIndex = (BudgetIndex)this.budgetIndexRepository.save(budgetIndex);
    Integer budgindexId = savedBudgetIndex.getBudgindexId();
    RenewalReq renewalData = budgetIndexData.getRenewal();
    
    if (renewalData != null) {
      Renewal renewal = toCreateBudgetindexRenewal(budgindexId, renewalData);
      List<Renewal> renewalList = new ArrayList<>();
      renewalList.add(renewal);
      savedBudgetIndex.setRenewal(renewalList);
    } 
    
    return savedBudgetIndex;
  }


  
  public BudgetIndex toUpdateBudgetIndex(Integer bgIndexId, BudgetIndexReq budgetIndexData) {
    Optional<BudgetIndex> currendBudgetIndex = this.budgetIndexRepository.findById(bgIndexId);
    if (!currendBudgetIndex.isPresent()) {
      return null;
    }
    BudgetIndex budgetIndex = new BudgetIndex();
    
    budgetIndex.setBudgindexId(((BudgetIndex)currendBudgetIndex.get()).getBudgindexId());
    budgetIndex.setUserId(budgetIndexData.getUserId());
    budgetIndex.setBudgsectorId(budgetIndexData.getBudgsectorId());
    budgetIndex.setGroupedbudgetId(budgetIndexData.getGroupedbudgetId());
    budgetIndex.setBudgindexName(budgetIndexData.getBudgindexName());
    budgetIndex.setBudgindexDescription(budgetIndexData.getBudgindexDescription());
    budgetIndex.setBudgindexValue(budgetIndexData.getBudgindexValue());
    budgetIndex.setCreatedOn(((BudgetIndex)currendBudgetIndex.get()).getCreatedOn());
    budgetIndex.setUpdatedOn(new Date());
    budgetIndex.setStatus(budgetIndexData.getStatus());
    budgetIndex.setStatus(budgetIndexData.getStatus());
    
    return (BudgetIndex)this.budgetIndexRepository.save(budgetIndex);
  }


  
  public Renewal toCreateGroupedbudgetRenewal(Integer grbgId, RenewalReq renewalData) {
    Renewal res = toCreateRenewal(renewalData);
    Integer linkSaved = Integer.valueOf(0);
    
    if (res != null) {
      linkSaved = this.renewalRepository.saveGroupedBudgetRenewal(res.getRenewalId(), grbgId);
    }
    return (linkSaved.intValue() == 1) ? res : null;
  }


  
  public Renewal toCreateBudgetindexRenewal(Integer bgIndexId, RenewalReq renewalData) {
    Renewal res = toCreateRenewal(renewalData);
    Integer linkSaved = Integer.valueOf(0);
    
    if (res != null) {
      linkSaved = this.renewalRepository.saveBudgetindexRenewal(bgIndexId, res.getRenewalId());
    }
    return (linkSaved.intValue() == 1) ? res : null;
  }




  
  public Renewal toCreateRenewal(RenewalReq renewalData) {
    Renewal renewal = new Renewal();
    
    renewal.setApplyDate(renewalData.getApplyDate());
    renewal.setNextRenewal(renewalData.getNextRenewal());
    renewal.setCreatedOn(new Date());
    renewal.setStatus(renewalData.getStatus());
    
    return (Renewal)this.renewalRepository.save(renewal);
  }


  
  public Renewal toUpdateRenewal(Integer renewalId, RenewalReq renewalData) {
    Optional<Renewal> currentRenewal = this.renewalRepository.findById(renewalId);
    if (!currentRenewal.isPresent()) {
      return null;
    }
    Renewal renewal = new Renewal();
    
    renewal.setRenewalId(renewalId);
    renewal.setApplyDate(renewalData.getApplyDate());
    renewal.setNextRenewal(renewalData.getNextRenewal());
    renewal.setCreatedOn(((Renewal)currentRenewal.get()).getCreatedOn());
    renewal.setUpdatedOn(new Date());
    renewal.setStatus(renewalData.getStatus());
    
    return (Renewal)this.renewalRepository.save(renewal);
  }
}
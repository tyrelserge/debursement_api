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


    @Override
    public List<BudgetarySector> getBudgetarySectorList() {
        return budgetarySectorRepository.findAll();
    }

    @Override
    public BudgetarySector getBudgetarySector(Integer sectorId) {
        return budgetarySectorRepository.findById(sectorId).orElseThrow(
            () -> new ResourceNotFoundException("ID not found"));
    }

    @Override
    public BudgetarySector toCreateBudgetarySector(SectorReq sectorData) {
        BudgetarySector sector = new BudgetarySector();
        sector.setBudgsectorName(sectorData.getBudgsectorName());
        sector.setBudgsectorDescription(sectorData.getBudgsectorDescription());
        sector.setCreatedOn(new Date());
        sector.setStatus(sectorData.getStatus());
        return budgetarySectorRepository.save(sector);
    }

    @Override
    public BudgetarySector toUpdateBudgetarySector(Integer sectorId, SectorReq sectorData) {

        Optional<BudgetarySector> currentSector = budgetarySectorRepository.findById(sectorId);
        if(!currentSector.isPresent())
            return null;
    
        BudgetarySector oldSector = currentSector.get();

        BudgetarySector sector = new BudgetarySector();

        sector.setBudgsectorId(oldSector.getBudgsectorId());
        sector.setBudgsectorName(sectorData.getBudgsectorName());
        sector.setBudgsectorDescription(sectorData.getBudgsectorDescription());
        sector.setCreatedOn(oldSector.getCreatedOn());
        sector.setUpdatedOn(new Date());
        sector.setStatus(sectorData.getStatus());
        
        return sector;
    }

    @Override
    public List<GroupedBudget> getGroupedBudgetList() {
        return groupedBudgetRepository.findAll();
    }

    @Override
    public GroupedBudget getGroupedBudget(Integer grbgId) {
        return groupedBudgetRepository.findById(grbgId).orElseThrow(
            () -> new ResourceNotFoundException("ID not found"));
    }

    @Override
    public GroupedBudget toCreateGroupedBudget(GroupedBudgetReq grpBudgetData) {
        
        GroupedBudget gbudget = new GroupedBudget();

        gbudget.setUserId(grpBudgetData.getUserId());
        gbudget.setBudgsectorId(grpBudgetData.getBudgsectorId());
        gbudget.setGroupedbudgetName(grpBudgetData.getGroupedbudgetName());
        gbudget.setGroupedbudgetDescription(grpBudgetData.getGroupedbudgetDescription());
        gbudget.setGroupedbudgetValue(grpBudgetData.getGroupedbudgetValue());
        gbudget.setGroupedbudgetPeriodic(grpBudgetData.getGroupedbudgetPeriodic());
        gbudget.setStatus(grpBudgetData.getStatus());
        gbudget.setCreatedOn(new Date());

        GroupedBudget savedGBudget = groupedBudgetRepository.save(gbudget);
        Integer groupedbudgetId = savedGBudget.getGroupedbudgetId();
        RenewalReq renewalData = grpBudgetData.getRenewal();

        if(renewalData!=null) {
            Renewal renewal = toCreateGroupedbudgetRenewal(groupedbudgetId, renewalData);
            List<Renewal> renewalList = new ArrayList<>();
            renewalList.add(renewal);
            savedGBudget.setRenewal(renewalList);
        }

        return groupedBudgetRepository.findById(groupedbudgetId).get();
    }

    @Override
    public GroupedBudget toUpdateGroupedBudget(Integer grbgId, GroupedBudgetReq grpBudgetData) {
        
        Optional<GroupedBudget> currendGroupedBudget = groupedBudgetRepository.findById(grbgId);
        if (!currendGroupedBudget.isPresent())
            return null;

        GroupedBudget gbudget = new GroupedBudget();

        gbudget.setGroupedbudgetId(currendGroupedBudget.get().getGroupedbudgetId());
        gbudget.setUserId(grpBudgetData.getUserId());
        gbudget.setBudgsectorId(grpBudgetData.getBudgsectorId());
        gbudget.setGroupedbudgetName(grpBudgetData.getGroupedbudgetName());
        gbudget.setGroupedbudgetDescription(grpBudgetData.getGroupedbudgetDescription());
        gbudget.setGroupedbudgetValue(grpBudgetData.getGroupedbudgetValue());
        gbudget.setGroupedbudgetPeriodic(grpBudgetData.getGroupedbudgetPeriodic());
        gbudget.setStatus(grpBudgetData.getStatus());
        gbudget.setCreatedOn(currendGroupedBudget.get().getCreatedOn());
        gbudget.setUpdatedOn(new Date());

        return groupedBudgetRepository.save(gbudget);
    }
  
    @Override
    public List<BudgetIndex> getBudgetIndexList() {
        return budgetIndexRepository.findAll();
    }

    @Override
    public BudgetIndex getBudgetIndex(Integer bgIndexId) {
        return budgetIndexRepository.findById(bgIndexId).orElseThrow(
            () -> new ResourceNotFoundException("ID not found"));
    }

    @Override
    public BudgetIndex toCreateBudgetIndex(BudgetIndexReq grpBudgetData) {
        
        BudgetIndex budgetIndex = new BudgetIndex();

        budgetIndex.setUserId(grpBudgetData.getUserId());
        budgetIndex.setBudgsectorId(grpBudgetData.getBudgsectorId());
        budgetIndex.setGroupedbudgetId(grpBudgetData.getGroupedbudgetId());
        budgetIndex.setBudgindexName(grpBudgetData.getBudgindexName());
        budgetIndex.setBudgindexDescription(grpBudgetData.getBudgindexDescription());
        budgetIndex.setBudgindexPeriodic(grpBudgetData.getBudgindexPeriodic());
        budgetIndex.setBudgindexValue(grpBudgetData.getBudgindexValue());
        budgetIndex.setCreatedOn(new Date());
        budgetIndex.setStatus(grpBudgetData.getStatus());

        BudgetIndex savedBudgetIndex = budgetIndexRepository.save(budgetIndex);
        Integer budgindexId = savedBudgetIndex.getBudgindexId();
        RenewalReq renewalData = grpBudgetData.getRenewal();
        
        if(renewalData!=null) {
            Renewal renewal = toCreateGroupedbudgetRenewal(budgindexId, renewalData);
            List<Renewal> renewalList = new ArrayList<>();
            renewalList.add(renewal);
            savedBudgetIndex.setRenewal(renewalList);
        }

        return savedBudgetIndex;
    }

    @Override
    public BudgetIndex toUpdateBudgetIndex(Integer bgIndexId, BudgetIndexReq grpBudgetData) {
        
        Optional<BudgetIndex> currendBudgetIndex = budgetIndexRepository.findById(bgIndexId);
        if (!currendBudgetIndex.isPresent())
            return null;

        BudgetIndex budgetIndex = new BudgetIndex();

        budgetIndex.setBudgindexId(currendBudgetIndex.get().getBudgindexId());
        budgetIndex.setUserId(grpBudgetData.getUserId());
        budgetIndex.setBudgsectorId(grpBudgetData.getBudgsectorId());
        budgetIndex.setGroupedbudgetId(grpBudgetData.getGroupedbudgetId());
        budgetIndex.setBudgindexName(grpBudgetData.getBudgindexName());
        budgetIndex.setBudgindexDescription(grpBudgetData.getBudgindexDescription());
        budgetIndex.setBudgindexPeriodic(grpBudgetData.getBudgindexPeriodic());
        budgetIndex.setBudgindexValue(grpBudgetData.getBudgindexValue());
        budgetIndex.setCreatedOn(currendBudgetIndex.get().getCreatedOn());
        budgetIndex.setUpdatedOn(new Date());
        budgetIndex.setStatus(grpBudgetData.getStatus());

        return budgetIndexRepository.save(budgetIndex);
    }

    @Override
    public Renewal toCreateGroupedbudgetRenewal(Integer grbgId, RenewalReq renewalData) {
        
        Renewal res = toCreateRenewal(renewalData);
        Integer linkSaved = 0;

        if (res != null)
            linkSaved = renewalRepository.saveGroupedBudgetRenewal(res.getRenewalId(), grbgId);

        return linkSaved==1 ? res : null;
    }

    @Override
    public Renewal toCreateBudgetindexRenewal(Integer bgIndexId, RenewalReq renewalData) {
        
        Renewal res = toCreateRenewal(renewalData);
        Integer linkSaved = 0;

        if (res != null)
            linkSaved = renewalRepository.saveBudgetindexRenewal(bgIndexId, res.getRenewalId());

        return linkSaved==1 ? res : null;
        
    }

    
    @Override
    public Renewal toCreateRenewal(RenewalReq renewalData) {

        Renewal renewal = new Renewal();
        
        renewal.setApplyDate(renewalData.getApplyDate()); 
        renewal.setNextRenewal(renewalData.getNextRenewal());
        renewal.setCreatedOn(new Date());
        renewal.setStatus(renewalData.getStatus());
        
        return renewalRepository.save(renewal);
    }

    @Override
    public Renewal toUpdateRenewal(Integer renewalId, RenewalReq renewalData) {
        
        Optional<Renewal> currentRenewal = renewalRepository.findById(renewalId);
        if (!currentRenewal.isPresent())
            return null;

        Renewal renewal = new Renewal();
        
        renewal.setRenewalId(renewalId);
        renewal.setApplyDate(renewalData.getApplyDate()); 
        renewal.setNextRenewal(renewalData.getNextRenewal());
        renewal.setCreatedOn(currentRenewal.get().getCreatedOn());
        renewal.setUpdatedOn(new Date());
        renewal.setStatus(renewalData.getStatus());
        
        return renewalRepository.save(renewal);
    }


}

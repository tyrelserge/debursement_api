package com.lin_q.debursement_api.service;

import java.util.List;

import com.lin_q.debursement_api.entity.BudgetIndex;
import com.lin_q.debursement_api.entity.BudgetarySector;
import com.lin_q.debursement_api.entity.GroupedBudget;
import com.lin_q.debursement_api.entity.Renewal;
import com.lin_q.debursement_api.model.BudgetIndexReq;
import com.lin_q.debursement_api.model.GroupedBudgetReq;
import com.lin_q.debursement_api.model.RenewalReq;
import com.lin_q.debursement_api.model.SectorReq;

public interface BudgetService {

    public List<BudgetarySector> getBudgetarySectorList();
    public BudgetarySector getBudgetarySector(Integer sectorId);
    public BudgetarySector toCreateBudgetarySector(SectorReq sectorData);
    public BudgetarySector toUpdateBudgetarySector(Integer sectorId, SectorReq sectorData);
    
    public List<GroupedBudget> getGroupedBudgetList();
    public GroupedBudget getGroupedBudget(Integer grbgId);
    public GroupedBudget toCreateGroupedBudget(GroupedBudgetReq grpBudgetData);
    public GroupedBudget toUpdateGroupedBudget(Integer grbgId, GroupedBudgetReq grpBudgetData);
    
    public List<BudgetIndex> getBudgetIndexList();
    public BudgetIndex getBudgetIndex(Integer bgIndexId);
    public BudgetIndex toCreateBudgetIndex(BudgetIndexReq grpBudgetData);
    public BudgetIndex toUpdateBudgetIndex(Integer bgIndexId, BudgetIndexReq grpBudgetData);
    
    public Renewal toCreateRenewal(RenewalReq renewalData);
    public Renewal toUpdateRenewal(Integer renewalId, RenewalReq renewalData);
    public Renewal toCreateGroupedbudgetRenewal(Integer grbgId, RenewalReq renewalData);
    public Renewal toCreateBudgetindexRenewal(Integer bgIndexId, RenewalReq renewalData);
    
}

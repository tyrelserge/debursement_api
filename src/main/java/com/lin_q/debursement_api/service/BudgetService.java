package com.lin_q.debursement_api.service;

import com.lin_q.debursement_api.entity.BudgetIndex;
import com.lin_q.debursement_api.entity.BudgetarySector;
import com.lin_q.debursement_api.entity.GroupedBudget;
import com.lin_q.debursement_api.entity.Renewal;
import com.lin_q.debursement_api.model.BudgetIndexReq;
import com.lin_q.debursement_api.model.GroupedBudgetReq;
import com.lin_q.debursement_api.model.RenewalReq;
import com.lin_q.debursement_api.model.SectorReq;
import java.util.List;

public interface BudgetService {
  List<BudgetarySector> getBudgetarySectorList();
  BudgetarySector getBudgetarySector(Integer paramInteger);
  BudgetarySector toCreateBudgetarySector(SectorReq paramSectorReq);
  BudgetarySector toUpdateBudgetarySector(Integer paramInteger, SectorReq paramSectorReq);
  List<GroupedBudget> getGroupedBudgetList();
  GroupedBudget getGroupedBudget(Integer paramInteger);
  GroupedBudget toCreateGroupedBudget(GroupedBudgetReq paramGroupedBudgetReq);
  GroupedBudget toUpdateGroupedBudget(Integer paramInteger, GroupedBudgetReq paramGroupedBudgetReq);
  List<BudgetIndex> getBudgetIndexList();
  BudgetIndex getBudgetIndex(Integer paramInteger);
  BudgetIndex toCreateBudgetIndex(BudgetIndexReq paramBudgetIndexReq);
  BudgetIndex toUpdateBudgetIndex(Integer paramInteger, BudgetIndexReq paramBudgetIndexReq);
  Renewal toCreateRenewal(RenewalReq paramRenewalReq);
  Renewal toUpdateRenewal(Integer paramInteger, RenewalReq paramRenewalReq);
  Renewal toCreateGroupedbudgetRenewal(Integer paramInteger, RenewalReq paramRenewalReq);
  Renewal toCreateBudgetindexRenewal(Integer paramInteger, RenewalReq paramRenewalReq);
}

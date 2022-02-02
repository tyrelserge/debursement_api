package com.lin_q.debursement_api.service;

import java.util.List;

import com.lin_q.debursement_api.entity.BudgetarySector;
import com.lin_q.debursement_api.model.SectorReq;

import org.springframework.stereotype.Service;

@Service
public interface BudgetService {

    public List<BudgetarySector> getBudgetarySectorList();
    public BudgetarySector getBudgetarySector(Integer sectorId);
    public BudgetarySector toCreateBudgetarySector(SectorReq sectorData);
    public BudgetarySector toUpdateBudgetarySector(Integer sectorId, SectorReq sectorData);
    
}

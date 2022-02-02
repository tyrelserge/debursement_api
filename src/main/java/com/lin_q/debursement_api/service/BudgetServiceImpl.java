package com.lin_q.debursement_api.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.lin_q.debursement_api.entity.BudgetarySector;
import com.lin_q.debursement_api.exception.ResourceNotFoundException;
import com.lin_q.debursement_api.model.SectorReq;
import com.lin_q.debursement_api.repository.BudgetarySectorRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetarySectorRepository budgetarySectorRepository;

    @Override
    public List<BudgetarySector> getBudgetarySectorList() {
        List<BudgetarySector> sector = budgetarySectorRepository.findAll();
        return sector;
    }

    @Override
    public BudgetarySector getBudgetarySector(Integer sectorId) {
        BudgetarySector sector = budgetarySectorRepository.findById(sectorId).orElseThrow(
            () -> new ResourceNotFoundException("The sector id " + sectorId + " is not found"));
        return sector;
    }

    @Override
    public BudgetarySector toCreateBudgetarySector(SectorReq sectorData) {
        BudgetarySector sector = new BudgetarySector();
        sector.setBudgsectorName(sectorData.getBudgsectorName());
        sector.setBudgsectorDescription(sector.getBudgsectorDescription());
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
        sector.setBudgsectorName(sectorData.getBudgsectorName());
        sector.setBudgsectorDescription(sector.getBudgsectorDescription());
        sector.setCreatedOn(oldSector.getCreatedOn());
        sector.setUpdatedOn(new Date());
        sector.setStatus(sectorData.getStatus());
        
        return sector;
    }
    
}

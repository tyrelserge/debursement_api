package com.lin_q.debursement_api.controller;

import java.util.List;

import com.lin_q.debursement_api.Constants;
import com.lin_q.debursement_api.entity.BudgetarySector;
import com.lin_q.debursement_api.model.ResponseDto;
import com.lin_q.debursement_api.model.SectorReq;
import com.lin_q.debursement_api.service.BudgetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/budget"})
public class BudgetController {

    // private static final Logger log = LoggerFactory.getLogger(BudgetController.class);

    @Autowired
    private BudgetService budjetService;
    
    @GetMapping(value = "/sectors")
    public ResponseEntity<ResponseDto<List<BudgetarySector>>> SectorList() {
        List<BudgetarySector> res = budjetService.getBudgetarySectorList();
        return res != null ? ResponseEntity.ok(new ResponseDto<List<BudgetarySector>>(Constants.SUCCESS, res)) : 
            ResponseEntity.ok(new ResponseDto<List<BudgetarySector>>(Constants.SUCCESS, res));
    }

    @GetMapping(value = "/sector/{budgsectorId}")
    public ResponseEntity<ResponseDto<BudgetarySector>> Sector(@PathVariable("budgsectorId") Integer sectorId) {
        BudgetarySector res = budjetService.getBudgetarySector(sectorId);
        return res != null ? ResponseEntity.ok(new ResponseDto<BudgetarySector>(Constants.SUCCESS, res)) : 
            ResponseEntity.ok(new ResponseDto<BudgetarySector>(Constants.SUCCESS, res));
    }

    @PostMapping(value = "/sector")
    public ResponseEntity<ResponseDto<BudgetarySector>> CreateSector(@RequestBody SectorReq SectorData) {
        BudgetarySector res = budjetService.toCreateBudgetarySector(SectorData);
        return res != null ? ResponseEntity.ok(new ResponseDto<BudgetarySector>(Constants.SUCCESS, res)) : 
            ResponseEntity.ok(new ResponseDto<BudgetarySector>(Constants.SUCCESS, res));
    }

    @PutMapping(value = "/sector/{sectorId}")
    public ResponseEntity<ResponseDto<BudgetarySector>> UpdateSector(
        @PathVariable("sectorId") Integer sectorId, @RequestBody SectorReq SectorData) {
        BudgetarySector res = budjetService.toUpdateBudgetarySector(sectorId, SectorData);
        return res != null ? ResponseEntity.ok(new ResponseDto<BudgetarySector>(Constants.SUCCESS, res)) : 
            ResponseEntity.ok(new ResponseDto<BudgetarySector>(Constants.SUCCESS, res));
    }
}

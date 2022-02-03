package com.lin_q.debursement_api.controller;

import java.util.List;

import com.lin_q.debursement_api.Constants;
import com.lin_q.debursement_api.entity.BudgetarySector;
import com.lin_q.debursement_api.entity.GroupedBudget;
import com.lin_q.debursement_api.entity.Renewal;
import com.lin_q.debursement_api.entity.BudgetIndex;
import com.lin_q.debursement_api.model.BudgetIndexReq;
import com.lin_q.debursement_api.model.GroupedBudgetReq;
import com.lin_q.debursement_api.model.RenewalReq;
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


    /*=====================================*/
    /* Budgetary Sector                    */
    /*=====================================*/

    @Autowired
    private BudgetService BudgetService;
    
    @GetMapping(value = "/sectors")
    public ResponseEntity<ResponseDto<List<BudgetarySector>>> SectorList() {
        List<BudgetarySector> res = BudgetService.getBudgetarySectorList();
        return res != null ? ResponseEntity.ok(new ResponseDto<List<BudgetarySector>>(Constants.SUCCESS, res)) : 
            ResponseEntity.ok(new ResponseDto<List<BudgetarySector>>(Constants.ERROR, null));
    }

    @GetMapping(value = "/sector/{grbgId}")
    public ResponseEntity<ResponseDto<BudgetarySector>> Sector(@PathVariable("grbgId") Integer sectorId) {
        BudgetarySector res = BudgetService.getBudgetarySector(sectorId);
        return res != null ? ResponseEntity.ok(new ResponseDto<BudgetarySector>(Constants.SUCCESS, res)) : 
            ResponseEntity.ok(new ResponseDto<BudgetarySector>(Constants.ERROR, null));
    }

    @PostMapping(value = "/sector")
    public ResponseEntity<ResponseDto<BudgetarySector>> CreateSector(@RequestBody SectorReq sectorData) {
        BudgetarySector res = BudgetService.toCreateBudgetarySector(sectorData);
        return res != null ? ResponseEntity.ok(new ResponseDto<BudgetarySector>(Constants.SUCCESS, res)) : 
            ResponseEntity.ok(new ResponseDto<BudgetarySector>(Constants.ERROR, null));
    }

    @PutMapping(value = "/sector/{sectorId}")
    public ResponseEntity<ResponseDto<BudgetarySector>> UpdateSector(
        @PathVariable("sectorId") Integer sectorId, @RequestBody SectorReq sectorData) {
        BudgetarySector res = BudgetService.toUpdateBudgetarySector(sectorId, sectorData);
        return res != null ? ResponseEntity.ok(new ResponseDto<BudgetarySector>(Constants.SUCCESS, res)) : 
            ResponseEntity.ok(new ResponseDto<BudgetarySector>(Constants.ERROR, null));
    }

    /*=====================================*/
    /* Grouped Budget                      */
    /*=====================================*/

    @GetMapping(value = "/groupedbudgets")
    public ResponseEntity<ResponseDto<List<GroupedBudget>>> GroupedBudgetList() {
        List<GroupedBudget> res = BudgetService.getGroupedBudgetList();
        return res != null ? ResponseEntity.ok(new ResponseDto<List<GroupedBudget>>(Constants.SUCCESS, res)) : 
            ResponseEntity.ok(new ResponseDto<List<GroupedBudget>>(Constants.ERROR, null));
    }

    @GetMapping(value = "/groupedbudget/{grbgId}")
    public ResponseEntity<ResponseDto<GroupedBudget>> GroupedBudget(
        @PathVariable("grbgId") Integer grbgId) {
        GroupedBudget res = BudgetService.getGroupedBudget(grbgId);
        return res != null ? ResponseEntity.ok(new ResponseDto<GroupedBudget>(Constants.SUCCESS, res)) : 
            ResponseEntity.ok(new ResponseDto<GroupedBudget>(Constants.ERROR, null));
    }

    @PostMapping(value = "/groupedbudget")
    public ResponseEntity<ResponseDto<GroupedBudget>> CreateGroupedBudget(
        @RequestBody GroupedBudgetReq grpBudgetData) {
        GroupedBudget res = BudgetService.toCreateGroupedBudget(grpBudgetData);
        return res != null ? ResponseEntity.ok(new ResponseDto<GroupedBudget>(Constants.SUCCESS, res)) : 
            ResponseEntity.ok(new ResponseDto<GroupedBudget>(Constants.ERROR, null));
    }

    @PutMapping(value = "/groupedbudget/{grbgId}")
    public ResponseEntity<ResponseDto<GroupedBudget>> UpdateSector(
        @PathVariable("grbgId") Integer grbgId, @RequestBody GroupedBudgetReq grpBudgetData) {
        GroupedBudget res = BudgetService.toUpdateGroupedBudget(grbgId, grpBudgetData);
        return res != null ? ResponseEntity.ok(new ResponseDto<GroupedBudget>(Constants.SUCCESS, res)) : 
            ResponseEntity.ok(new ResponseDto<GroupedBudget>(Constants.ERROR, null));
    }
    
    /*=====================================*/
    /* Grouped Budget                      */
    /*=====================================*/

    @GetMapping(value = "/budgetindexs")
    public ResponseEntity<ResponseDto<List<BudgetIndex>>> BudgetIndexList() {
        List<BudgetIndex> res = BudgetService.getBudgetIndexList();
        return res != null ? ResponseEntity.ok(new ResponseDto<List<BudgetIndex>>(Constants.SUCCESS, res)) : 
            ResponseEntity.ok(new ResponseDto<List<BudgetIndex>>(Constants.ERROR, null));
    }

    @GetMapping(value = "/budgetindex/{bgIndexId}")
    public ResponseEntity<ResponseDto<BudgetIndex>> BudgetIndex(
        @PathVariable("bgIndexId") Integer bgIndexId) {
        BudgetIndex res = BudgetService.getBudgetIndex(bgIndexId);
        return res != null ? ResponseEntity.ok(new ResponseDto<BudgetIndex>(Constants.SUCCESS, res)) : 
            ResponseEntity.ok(new ResponseDto<BudgetIndex>(Constants.ERROR, null));
    }

    @PostMapping(value = "/budgetindex")
    public ResponseEntity<ResponseDto<BudgetIndex>> CreateBudgetIndex(
        @RequestBody BudgetIndexReq grpBudgetData) {
        BudgetIndex res = BudgetService.toCreateBudgetIndex(grpBudgetData);
        return res != null ? ResponseEntity.ok(new ResponseDto<BudgetIndex>(Constants.SUCCESS, res)) : 
            ResponseEntity.ok(new ResponseDto<BudgetIndex>(Constants.ERROR, null));
    }

    @PutMapping(value = "/budgetindex/{bgIndexId}")
    public ResponseEntity<ResponseDto<BudgetIndex>> UpdateSector(
        @PathVariable("bgIndexId") Integer bgIndexId, @RequestBody BudgetIndexReq grpBudgetData) {
        BudgetIndex res = BudgetService.toUpdateBudgetIndex(bgIndexId, grpBudgetData);
        return res != null ? ResponseEntity.ok(new ResponseDto<BudgetIndex>(Constants.SUCCESS, res)) : 
            ResponseEntity.ok(new ResponseDto<BudgetIndex>(Constants.ERROR, null));
    }

    /*=====================================*/
    /* Budget & Index Renewal                      */
    /*=====================================*/
    
    @PostMapping(value = "/groupedbudget/{grbgId}/renewal")
    public ResponseEntity<ResponseDto<Renewal>> CreateGroupedbudgetRenewal(
        @PathVariable("grbgId") Integer grbgId, @RequestBody RenewalReq renewalData) {
        Renewal res = BudgetService.toCreateGroupedbudgetRenewal(grbgId, renewalData);
        return res != null ? ResponseEntity.ok(new ResponseDto<Renewal>(Constants.SUCCESS, res)) : 
            ResponseEntity.ok(new ResponseDto<Renewal>(Constants.ERROR, null));
    }

    @PostMapping(value = "/budgetindex/{bgIndexId}/renewal")
    public ResponseEntity<ResponseDto<Renewal>> CreateBudgetindexRenewal(
        @PathVariable("bgIndexId") Integer bgIndexId, @RequestBody RenewalReq renewalData) {
        Renewal res = BudgetService.toCreateBudgetindexRenewal(bgIndexId, renewalData);
        return res != null ? ResponseEntity.ok(new ResponseDto<Renewal>(Constants.SUCCESS, res)) : 
            ResponseEntity.ok(new ResponseDto<Renewal>(Constants.ERROR, null));
    }

    @PutMapping(value = "/renewal/{renewalId}")
    public ResponseEntity<ResponseDto<Renewal>> UpdateRenewal(
        @PathVariable("renewalId") Integer renewalId, @RequestBody RenewalReq renewalData) {
        Renewal res = BudgetService.toUpdateRenewal(renewalId, renewalData);
        return res != null ? ResponseEntity.ok(new ResponseDto<Renewal>(Constants.SUCCESS, res)) : 
            ResponseEntity.ok(new ResponseDto<Renewal>(Constants.ERROR, null));
    }


}

package com.lin_q.debursement_api.controller;

import com.lin_q.debursement_api.Constants;
import com.lin_q.debursement_api.entity.BudgetIndex;
import com.lin_q.debursement_api.entity.BudgetarySector;
import com.lin_q.debursement_api.entity.GroupedBudget;
import com.lin_q.debursement_api.entity.Renewal;
import com.lin_q.debursement_api.model.BudgetIndexReq;
import com.lin_q.debursement_api.model.GroupedBudgetReq;
import com.lin_q.debursement_api.model.RenewalReq;
import com.lin_q.debursement_api.model.ResponseDto;
import com.lin_q.debursement_api.model.SectorReq;
import com.lin_q.debursement_api.service.BudgetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping({"/budget"})
public class BudgetController
{
  @Autowired
  private BudgetService budgetService;
  
  @GetMapping({"/sectors"})
  public ResponseEntity<ResponseDto<List<BudgetarySector>>> SectorList() {
    List<BudgetarySector> res = this.budgetService.getBudgetarySectorList();
    return (res != null && !res.isEmpty()) ? ResponseEntity.ok(new ResponseDto<List<BudgetarySector>>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<List<BudgetarySector>>("ERROR", null));
  }
  
  @GetMapping({"/sector/{grbgId}"})
  public ResponseEntity<ResponseDto<BudgetarySector>> Sector(@PathVariable("grbgId") Integer sectorId) {
    BudgetarySector res = this.budgetService.getBudgetarySector(sectorId);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<BudgetarySector>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<BudgetarySector>("ERROR", null));
  }
  
  @PostMapping({"/sector"})
  public ResponseEntity<ResponseDto<BudgetarySector>> CreateSector(@RequestBody SectorReq sectorData) {
    BudgetarySector res = this.budgetService.toCreateBudgetarySector(sectorData);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<BudgetarySector>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<BudgetarySector>("ERROR", null));
  }

  
  @PutMapping({"/sector/{sectorId}"})
  public ResponseEntity<ResponseDto<BudgetarySector>> UpdateSector(@PathVariable("sectorId") Integer sectorId, @RequestBody SectorReq sectorData) {
    BudgetarySector res = this.budgetService.toUpdateBudgetarySector(sectorId, sectorData);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<BudgetarySector>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<BudgetarySector>("ERROR", null));
  }

  
  @GetMapping({"/groupedbudgets"})
  public ResponseEntity<ResponseDto<List<GroupedBudget>>> GroupedBudgetList() {
    List<GroupedBudget> res = this.budgetService.getGroupedBudgetList();
    return (res != null && !res.isEmpty()) ? ResponseEntity.ok(new ResponseDto<List<GroupedBudget>>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<List<GroupedBudget>>("ERROR", null));
  }

  
  @GetMapping({"/groupedbudget/{grbgId}"})
  public ResponseEntity<ResponseDto<GroupedBudget>> GroupedBudget(@PathVariable("grbgId") Integer grbgId) {
    GroupedBudget res = this.budgetService.getGroupedBudget(grbgId);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<GroupedBudget>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<GroupedBudget>("ERROR", null));
  }

  
  @PostMapping({"/groupedbudget"})
  public ResponseEntity<ResponseDto<GroupedBudget>> CreateGroupedBudget(@RequestBody GroupedBudgetReq grpBudgetData) {
    GroupedBudget res = this.budgetService.toCreateGroupedBudget(grpBudgetData);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<GroupedBudget>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<GroupedBudget>("ERROR", null));
  }

  
  @PutMapping({"/groupedbudget/{grbgId}"})
  public ResponseEntity<ResponseDto<GroupedBudget>> UpdateSector(@PathVariable("grbgId") Integer grbgId, @RequestBody GroupedBudgetReq grpBudgetData) {
    GroupedBudget res = this.budgetService.toUpdateGroupedBudget(grbgId, grpBudgetData);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<GroupedBudget>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<GroupedBudget>("ERROR", null));
  }

  
  @GetMapping({"/budgetindexs"})
  public ResponseEntity<ResponseDto<List<BudgetIndex>>> BudgetIndexList() {
    List<BudgetIndex> res = this.budgetService.getBudgetIndexList();
    return (res != null && !res.isEmpty()) ? ResponseEntity.ok(new ResponseDto<List<BudgetIndex>>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<List<BudgetIndex>>("ERROR", null));
  }

  
  @GetMapping({"/budgetindex/{bgIndexId}"})
  public ResponseEntity<ResponseDto<BudgetIndex>> BudgetIndex(@PathVariable("bgIndexId") Integer bgIndexId) {
    BudgetIndex res = this.budgetService.getBudgetIndex(bgIndexId);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<BudgetIndex>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<BudgetIndex>("ERROR", null));
  }

  
  @PostMapping({"/budgetindex"})
  public ResponseEntity<ResponseDto<BudgetIndex>> CreateBudgetIndex(@RequestBody BudgetIndexReq grpBudgetData) {
    BudgetIndex res = this.budgetService.toCreateBudgetIndex(grpBudgetData);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<BudgetIndex>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<BudgetIndex>("ERROR", null));
  }

  
  @PutMapping({"/budgetindex/{bgIndexId}"})
  public ResponseEntity<ResponseDto<BudgetIndex>> UpdateSector(@PathVariable("bgIndexId") Integer bgIndexId, @RequestBody BudgetIndexReq grpBudgetData) {
    BudgetIndex res = this.budgetService.toUpdateBudgetIndex(bgIndexId, grpBudgetData);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<BudgetIndex>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<BudgetIndex>("ERROR", null));
  }


  
  @PostMapping({"/groupedbudget/{grbgId}/renewal"})
  public ResponseEntity<ResponseDto<Renewal>> CreateGroupedbudgetRenewal(@PathVariable("grbgId") Integer grbgId, @RequestBody RenewalReq renewalData) {
    Renewal res = this.budgetService.toCreateGroupedbudgetRenewal(grbgId, renewalData);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<Renewal>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<Renewal>("ERROR", null));
  }

  
  @PostMapping({"/budgetindex/{bgIndexId}/renewal"})
  public ResponseEntity<ResponseDto<Renewal>> CreateBudgetindexRenewal(@PathVariable("bgIndexId") Integer bgIndexId, @RequestBody RenewalReq renewalData) {
    Renewal res = this.budgetService.toCreateBudgetindexRenewal(bgIndexId, renewalData);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<Renewal>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<Renewal>("ERROR", null));
  }

  
  @PutMapping({"/renewal/{renewalId}"})
  public ResponseEntity<ResponseDto<Renewal>> UpdateRenewal(@PathVariable("renewalId") Integer renewalId, @RequestBody RenewalReq renewalData) {
    Renewal res = this.budgetService.toUpdateRenewal(renewalId, renewalData);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<Renewal>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<Renewal>("ERROR", null));
  }

    
  @GetMapping({"/groupedbudget/search/{input}"})
  public ResponseEntity<ResponseDto<List<GroupedBudget>>> SearchInputGroupedBudget(
    @PathVariable("input") String input) {
    List<GroupedBudget> res = this.budgetService.toSearchInputGroupedBudget(input);    
    return ResponseEntity.ok(new ResponseDto<List<GroupedBudget>>(Constants.SUCCESS, res));  
  }

  @GetMapping({"/groupedbudget/{groupedbudgetId}/budgetindex/search/{input}"})
  public ResponseEntity<ResponseDto<List<BudgetIndex>>> SearchInputIndexOfGroupedBudget(
    @PathVariable("groupedbudgetId") Integer groupedbudgetId, @PathVariable("input") String input) {
    List<BudgetIndex> res = this.budgetService.toSearchInputIndexOfGroupedBudget(groupedbudgetId, input);    
    return ResponseEntity.ok(new ResponseDto<List<BudgetIndex>>(Constants.SUCCESS, res));  
  }
  
  @GetMapping({"/groupedbudget/{groupedbudgetId}/budgetindex/search"})
  public ResponseEntity<ResponseDto<List<BudgetIndex>>> SearchInputEmptyIndexOfGroupedBudget(
    @PathVariable("groupedbudgetId") Integer groupedbudgetId) {
    List<BudgetIndex> res = this.budgetService.toSearchInputEmptyIndexOfGroupedBudget(groupedbudgetId);    
    return ResponseEntity.ok(new ResponseDto<List<BudgetIndex>>(Constants.SUCCESS, res));  
  }

}

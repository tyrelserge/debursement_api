package com.lin_q.debursement_api.controller;

import com.lin_q.debursement_api.Constants;
import com.lin_q.debursement_api.entity.Debursement;
import com.lin_q.debursement_api.entity.ReasonItems;
import com.lin_q.debursement_api.entity.TempDisburs;
import com.lin_q.debursement_api.entity.ValidationAction;
import com.lin_q.debursement_api.model.DebursementReq;
import com.lin_q.debursement_api.model.ReasonAssignReq;
import com.lin_q.debursement_api.model.ReasonItemsReq;
import com.lin_q.debursement_api.model.ResponseDto;
import com.lin_q.debursement_api.model.TempDisbReq;
import com.lin_q.debursement_api.model.TempDisbStatus;
import com.lin_q.debursement_api.model.ValidationReq;
import com.lin_q.debursement_api.service.DisbursementService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
@RequestMapping({"/disbusement"})
public class DisbursementController
{
  @Autowired
  private DisbursementService disbursementService;
  
  @GetMapping({"/user/{userId}/requests"})
  public ResponseEntity<ResponseDto<List<Debursement>>> UserDisbursementRequestList(@PathVariable("userId") Integer userId) {
    List<Debursement> res = this.disbursementService.getUserDisbursementRequestList(userId);
    return (res != null && !res.isEmpty()) ? ResponseEntity.ok(new ResponseDto<List<Debursement>>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<List<Debursement>>("ERROR", null));
  }

  
  @GetMapping({"/request/{disbursId}"})
  public ResponseEntity<ResponseDto<Debursement>> UserDisbursementRequest(@PathVariable("disbursId") Integer disbursId) {
    Debursement res = this.disbursementService.getUserDisbursementRequest(disbursId);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<Debursement>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<Debursement>("ERROR", null));
  }

  
  @PostMapping({"/request"})
  public ResponseEntity<ResponseDto<Debursement>> DisbursementRequest(@RequestBody DebursementReq debursementData) {
    Debursement res = this.disbursementService.toDisbursementRequest(debursementData);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<Debursement>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<Debursement>("ERROR", null));
  }

  
  @PutMapping({"/request/{disbursId}"})
  public ResponseEntity<ResponseDto<Debursement>> UpdateDisbursementRequest(@PathVariable("disbursId") Integer disbursId, @RequestBody DebursementReq debursementData) {
    Debursement res = this.disbursementService.toUpdateDisbursementRequest(disbursId, debursementData);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<Debursement>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<Debursement>("ERROR", null));
  }

  
  @PostMapping({"/request/reason"})
  public ResponseEntity<ResponseDto<ReasonItems>> SaveReasonItems(@RequestBody ReasonItemsReq ReasonData) {
    ReasonItems res = this.disbursementService.toSaveReasonItems(ReasonData);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<ReasonItems>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<ReasonItems>("ERROR", null));
  }
  
  
  @PutMapping({"/reason/{reasonId}/assign"})
  public ResponseEntity<ResponseDto<ReasonItems>> AssignReasonItems(@PathVariable("reasonId") Integer reasonId, @RequestBody ReasonAssignReq ReasonData) {
    ReasonItems res = this.disbursementService.toAssignReasonItems(reasonId, ReasonData.getDebursementId());
    return (res != null) ? ResponseEntity.ok(new ResponseDto<ReasonItems>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<ReasonItems>("ERROR", null));
  }

  
  @PutMapping({"/reason/{reasonId}"})
  public ResponseEntity<ResponseDto<ReasonItems>> UpdateReasonItems(@PathVariable("reasonId") Integer reasonId, @RequestBody ReasonItemsReq ReasonData) {
    ReasonItems res = this.disbursementService.toUpdateReasonItems(reasonId, ReasonData);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<ReasonItems>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<ReasonItems>("ERROR", null));
  }


  @GetMapping({"/requests"})
  public ResponseEntity<ResponseDto<List<Debursement>>> AllDisbursement() {
    List<Debursement> res = this.disbursementService.getAllDisbursements();
    return (res != null) ? ResponseEntity.ok(new ResponseDto<List<Debursement>>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<List<Debursement>>("ERROR", null));
  }


  @GetMapping({"/requests/waiting"})
  public ResponseEntity<ResponseDto<List<Debursement>>> DisbursementWaitingValidation() {
    List<Debursement> res = this.disbursementService.getDisbursementWaitingValidation();
    return (res != null) ? ResponseEntity.ok(new ResponseDto<List<Debursement>>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<List<Debursement>>("ERROR", null));
  }

  
  @PostMapping({"/validation/request/{disbursId}"})
  public ResponseEntity<ResponseDto<ValidationAction>> DisbursementValidation(@PathVariable("disbursId") Integer disbursId, @RequestBody ValidationReq validationData) {
    ValidationAction res = this.disbursementService.setDisbursementValidation(disbursId, validationData);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<ValidationAction>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<ValidationAction>("ERROR", null));
  }

  @GetMapping({"/registration/nextnumber"})
  public ResponseEntity<ResponseDto<String>> GenerateRegistrationNumber() {
    String res = this.disbursementService.getGeneratedRegistrationNumber();
    return (res != null) ? ResponseEntity.ok(new ResponseDto<String>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<String>("ERROR", null));
  }
  
  @GetMapping({"/temps"})
  public ResponseEntity<ResponseDto<List<TempDisburs>>> TempsDisbursement() {
    List<TempDisburs> res = disbursementService.getTempsDisburs(Sort.by(Sort.Direction.DESC, "temp_disb_id"));
    return res!=null && !res.isEmpty() ? ResponseEntity.ok(new ResponseDto<List<TempDisburs>>(Constants.SUCCESS, res)) : 
      ResponseEntity.ok(new ResponseDto<List<TempDisburs>>(Constants.ERROR, null));
  }
    
  @GetMapping({"/temps/untreated"})
  public ResponseEntity<ResponseDto<List<TempDisburs>>> UntreatedTempsDisbursement() {
    List<TempDisburs> res = disbursementService.getUntreatedTempsDisburs();
    return res!=null && !res.isEmpty() ? ResponseEntity.ok(new ResponseDto<List<TempDisburs>>(Constants.SUCCESS, res)) : 
      ResponseEntity.ok(new ResponseDto<List<TempDisburs>>(Constants.ERROR, null));
  }
  
  @PostMapping({"/create/temp"})
  public ResponseEntity<ResponseDto<TempDisburs>> CreateTempDisbursement(@RequestBody TempDisbReq TempData) {
    TempDisburs res = disbursementService.toCreateTempDisbursement(TempData);
    return res!=null ? ResponseEntity.ok(new ResponseDto<TempDisburs>(Constants.SUCCESS, res)) : 
      ResponseEntity.ok(new ResponseDto<TempDisburs>(Constants.ERROR, null));
  }
  

  @PutMapping({"/process/temp/{tempdisbId}"})
  public ResponseEntity<ResponseDto<TempDisburs>> SetTempDisbursement(
    @PathVariable("tempdisbId") Integer tempdisbId, @RequestBody TempDisbStatus TempData) {
    TempDisburs res = disbursementService.toSetTempDisbursementStatus(tempdisbId, TempData);
    return res!=null ? ResponseEntity.ok(new ResponseDto<TempDisburs>(Constants.SUCCESS, res)) : 
      ResponseEntity.ok(new ResponseDto<TempDisburs>(Constants.ERROR, null));
  }

}
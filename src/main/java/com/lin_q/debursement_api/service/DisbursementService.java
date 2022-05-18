package com.lin_q.debursement_api.service;

import java.util.List;

import com.lin_q.debursement_api.entity.Debursement;
import com.lin_q.debursement_api.entity.ReasonItems;
import com.lin_q.debursement_api.entity.TempDisburs;
import com.lin_q.debursement_api.entity.ValidationAction;
import com.lin_q.debursement_api.model.DebursementReq;
import com.lin_q.debursement_api.model.ReasonItemsReq;
import com.lin_q.debursement_api.model.TempDisbReq;
import com.lin_q.debursement_api.model.TempDisbStatus;
import com.lin_q.debursement_api.model.ValidationReq;

import org.springframework.data.domain.Sort;

public interface DisbursementService {
  List<Debursement> getUserDisbursementRequestList(Integer userId);  
  Debursement getUserDisbursementRequest(Integer disbursId);  
  Debursement toDisbursementRequest(DebursementReq debursementData);  
  Debursement toUpdateDisbursementRequest(Integer disbursId, DebursementReq debursementData);  
  ReasonItems toSaveReasonItems(ReasonItemsReq ReasonData);  
  ReasonItems toAssignReasonItems(Integer reasonId, Integer debursementId);
  ReasonItems toUpdateReasonItems(Integer reasonId, ReasonItemsReq ReasonData);  
  List<Debursement> getAllDisbursements();
  List<Debursement> getDisbursementWaitingValidation();
  ValidationAction setDisbursementValidation(Integer disbursId, ValidationReq validationReq);
  String getGeneratedRegistrationNumber();
  List<TempDisburs> getTempsDisburs(Sort sort);
  TempDisburs toCreateTempDisbursement(TempDisbReq tempData);
  TempDisburs toSetTempDisbursementStatus(Integer tempDisbId, TempDisbStatus tempData);
List<TempDisburs> getUntreatedTempsDisburs();
}

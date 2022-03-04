package com.lin_q.debursement_api.service;

import java.util.List;

import com.lin_q.debursement_api.entity.Debursement;
import com.lin_q.debursement_api.entity.ReasonItems;
import com.lin_q.debursement_api.entity.ValidationAction;
import com.lin_q.debursement_api.model.DebursementReq;
import com.lin_q.debursement_api.model.ReasonItemsReq;
import com.lin_q.debursement_api.model.ValidationReq;

public interface DisbursementService {
  List<Debursement> getUserDisbursementRequestList(Integer paramInteger);  
  Debursement getUserDisbursementRequest(Integer paramInteger);  
  Debursement toDisbursementRequest(DebursementReq paramDebursementReq);  
  Debursement toUpdateDisbursementRequest(Integer paramInteger, DebursementReq paramDebursementReq);  
  ReasonItems toSaveReasonItems(ReasonItemsReq ReasonData);  
  ReasonItems toAssignReasonItems(Integer reasonId, Integer debursementId);
  ReasonItems toUpdateReasonItems(Integer reasonId, ReasonItemsReq ReasonData);  
  List<Debursement> getAllDisbursements();
  List<Debursement> getDisbursementWaitingValidation();
  ValidationAction setDisbursementValidation(Integer disbursId, ValidationReq validationReq);
  String getGeneratedRegistrationNumber();
}

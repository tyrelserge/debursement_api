package com.lin_q.debursement_api.service;

import com.lin_q.debursement_api.entity.Debursement;
import com.lin_q.debursement_api.entity.ReasonItems;
import com.lin_q.debursement_api.model.DebursementReq;
import com.lin_q.debursement_api.model.ReasonItemsReq;
import com.lin_q.debursement_api.model.ValidationReq;
import java.util.List;

public interface DisbursementService {
  List<Debursement> getUserDisbursementRequestList(Integer paramInteger);  
  Debursement getUserDisbursementRequest(Integer paramInteger);  
  Debursement toDisbursementRequest(DebursementReq paramDebursementReq);  
  Debursement toUpdateDisbursementRequest(Integer paramInteger, DebursementReq paramDebursementReq);  
  ReasonItems toSaveReasonItems(Integer paramInteger, ReasonItemsReq paramReasonItemsReq);  
  ReasonItems toUpdateReasonItems(Integer paramInteger, ReasonItemsReq paramReasonItemsReq);  
  Debursement setDisbursementValidation(Integer paramInteger, ValidationReq paramValidationReq);
}

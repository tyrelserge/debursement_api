package com.lin_q.debursement_api.controller;

import java.util.List;

import com.lin_q.debursement_api.entity.Debursement;
import com.lin_q.debursement_api.model.DebursementReq;
import com.lin_q.debursement_api.model.ResponseDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/disbusement"})
public class DisbursementController {
    
    @GetMapping(value = "/{userId}")
    public ResponseEntity<ResponseDto<List<Debursement>>> UserDisbursementRequestList(
        @PathVariable("userId") Integer userId) {

        return null;
    }
    
    @GetMapping(value = "/{disbursementId}")
    public ResponseEntity<ResponseDto<Debursement>> UserDisbursementRequest(
        @PathVariable("disbursementId") Integer disbursId) {

        return null;
    }

    @PostMapping(value = "/disbursement/request")
    public ResponseEntity<ResponseDto<Debursement>> DisbursementRequest(
        @RequestBody DebursementReq DebursementData) {

        return null;
    }

    @PostMapping(value = "/disbursement/request/{disbursementId}")
    public ResponseEntity<ResponseDto<Debursement>> CancelDisbursementRequest(
        @PathVariable("disbursementId") Integer disbursId) {

        return null;
    }

    @PutMapping(value = "/disbursement/{disbursementId}/validation")
    public ResponseEntity<ResponseDto<Debursement>> Disbursementvalidation(
        @RequestBody DebursementReq DebursementData) {

        return null;
    }
}

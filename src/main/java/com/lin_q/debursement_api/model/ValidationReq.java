package com.lin_q.debursement_api.model;

import lombok.Data;

@Data
public class ValidationReq {
    private Integer userId;
    private Integer amountApproved;
    private Integer recipientId; 
    private String actionValue; 
    private String observation; 
}

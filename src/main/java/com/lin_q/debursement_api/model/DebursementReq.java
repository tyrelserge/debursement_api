package com.lin_q.debursement_api.model;

import lombok.Data;

@Data
public class DebursementReq {

    private Integer budgindexId;
    private Integer userId;
    private String identifier;
    private String reason; 
    private Integer amountRequested; 
    private Integer recipientId; 
    private String status = "active"; 
    private Integer[] reasonItemsIds;
}
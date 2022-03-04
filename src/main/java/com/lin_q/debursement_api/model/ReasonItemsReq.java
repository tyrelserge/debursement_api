package com.lin_q.debursement_api.model;

import lombok.Data;

@Data
public class ReasonItemsReq {
    private Integer debursementId;
    private String designation;
    private Integer unitprice; 
    private Integer quatity; 
    private String status = "active"; 
}

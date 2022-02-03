package com.lin_q.debursement_api.model;

import java.util.Date;

import lombok.Data;

@Data
public class RenewalReq {
    private Date applyDate; 
    private Date nextRenewal;
    private String status = "active";
}

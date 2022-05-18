package com.lin_q.debursement_api.model;

import lombok.Data;

@Data
public class TempDisbReq {
    private String tempFullname;
    private String tempMobile;
    private String tempAmount;
    private String tempReason;
    private String tempAssignment;
    private String tempGroupbudget;
    private String claimantToken;
}

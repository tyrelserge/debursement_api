package com.lin_q.debursement_api.model;

import lombok.Data;

@Data
public class ExportPeriodReq {
    private String budgetSectorId;
    private String from;
    private String to;
}

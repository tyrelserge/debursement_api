package com.lin_q.debursement_api.model;

import lombok.Data;

@Data
public class BudgetIndexReq {
    private Integer userId;
    private Integer budgsectorId;
    private Integer groupedbudgetId;
    private String budgindexName;
    private String budgindexDescription;
    private Integer budgindexPeriodic;
    private Integer budgindexValue;
    private String status = "active";

    private RenewalReq renewal;
}

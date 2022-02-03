package com.lin_q.debursement_api.model;

import lombok.Data;

@Data
public class GroupedBudgetReq {
    private Integer userId;
    private Integer budgsectorId;
    private String groupedbudgetName;
    private String groupedbudgetDescription;
    private Integer groupedbudgetPeriodic;
    private Integer groupedbudgetValue;
    private String status = "active";

    private RenewalReq renewal;
}

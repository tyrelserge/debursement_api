package com.lin_q.debursement_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DebursementExcelData {
    private Integer debursementId;
    private String reason;
    private String budgsectorId;
    private String budgsectorName;
    private String groupedbudgetName;
    private String civility;
    private String firstname;
    private String lastname;
    private String identifier;
    private String createdOn;
    private String updatedOn;
    private String amountRequested;
    private String amountApproved;
    private String status;

    public DebursementExcelData(Object[] list) {        
        this.debursementId = Integer.parseInt(String.valueOf(list[0]));
        this.reason = String.valueOf(list[1]);
        this.budgsectorId = String.valueOf(list[2]);
        this.budgsectorName = String.valueOf(list[3]);
        this.groupedbudgetName = String.valueOf(list[4]);
        this.civility = String.valueOf(list[5]);
        this.firstname = String.valueOf(list[6]);
        this.lastname = String.valueOf(list[7]);
        this.identifier = String.valueOf(list[8]);
        this.createdOn = String.valueOf(list[9]);
        this.updatedOn = String.valueOf(list[10]);
        this.amountRequested = String.valueOf(list[11]);
        this.amountApproved = String.valueOf(list[12]);
        this.status = String.valueOf(list[13]);
    }
}

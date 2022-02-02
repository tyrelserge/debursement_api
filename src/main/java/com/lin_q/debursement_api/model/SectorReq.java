
package com.lin_q.debursement_api.model;

import lombok.Data;

@Data
public class SectorReq {
    private String budgsectorName;
    private String budgsectorDescription;
    private String status = "active";
}

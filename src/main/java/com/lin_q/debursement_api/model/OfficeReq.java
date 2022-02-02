package com.lin_q.debursement_api.model;

import com.lin_q.debursement_api.entity.Department;

import lombok.Data;

@Data
public class OfficeReq {
    private Integer userId;
    private Integer departmentId;
    private Integer profileId;
    private String officeName;
    private Department Department;
}

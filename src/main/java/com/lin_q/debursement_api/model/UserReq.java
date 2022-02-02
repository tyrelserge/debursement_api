package com.lin_q.debursement_api.model;

import lombok.Data;

@Data
public class UserReq {
    // private Integer profileId;
    private String lastname;
    private String firstname;
    private String gender;
    private String email;
    private String mobile;
    private String password;
}

package com.lin_q.debursement_api.model;

import java.util.Optional;

import lombok.Data;

@Data
public class UserReq {

    private String lastname;
    private String firstname;
    private String civility;
    private String email; 
    private String mobile; 
    private String password; 
    private Optional<Integer[]> officeIds; 
    private String status;
}
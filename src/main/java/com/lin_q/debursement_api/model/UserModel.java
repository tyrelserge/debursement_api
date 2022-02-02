package com.lin_q.debursement_api.model;

import java.util.Date;

import lombok.Data;

@Data
public class UserModel {
    private Integer userId;
    private String lastname;
    private String firstname;
    private String gender;
    private String email;
    private String mobile;
    // private String password;
    private Date createdOn;
    private Date updatedOn;
    private String status;
}

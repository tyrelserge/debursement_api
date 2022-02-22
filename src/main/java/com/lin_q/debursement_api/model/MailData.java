package com.lin_q.debursement_api.model;

import lombok.Data;

@Data
public class MailData {
    private String address;
    private String subject;
    private String content;
}

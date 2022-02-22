package com.lin_q.debursement_api.model;

import lombok.Data;

@Data
public class NotificationReq {
    private Integer userId;
    private String notificationSubject;
    private String notificationDetails;
}

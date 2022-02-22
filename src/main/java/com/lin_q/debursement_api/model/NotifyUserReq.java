package com.lin_q.debursement_api.model;

import lombok.Data;

@Data
public class NotifyUserReq {
    private Integer userId;
    private Integer notificationId;
    private String notificationLink;
}

package com.lin_q.debursement_api.model;

import lombok.Data;

@Data
public class FCMTokenReq {
	private Integer userId;
	private String fcmTokenAccess;
}

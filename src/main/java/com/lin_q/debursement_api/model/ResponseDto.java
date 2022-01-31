package com.lin_q.debursement_api.model;

import com.lin_q.debursement_api.Util;
import com.lin_q.debursement_api.entity.ActionRequest;

import lombok.Data;

@Data
public class ResponseDto<T> {
    
    private String statusCode;
	private T response;
	
	public ResponseDto(String statusCode, T response) {
		super();
		this.statusCode = statusCode;
		this.response = response;
	}

	public ResponseDto(String statusCode, T response, ActionRequest action) {
		super();
		this.statusCode = statusCode;
		this.response = response;
		Util.arSave(action.getIpAddress(), action.getRequest(), statusCode.toLowerCase());
	}
    
}

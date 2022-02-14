package com.lin_q.debursement_api.model;

import com.lin_q.debursement_api.Util;
import com.lin_q.debursement_api.entity.ActionRequest;

public class ResponseDto<T> {
    private String statusCode;
    private T response;

    public void setStatusCode(String statusCode) { this.statusCode = statusCode; } 
    public void setResponse(T response) { this.response = response; } 
    public String getStatusCode() { return this.statusCode; } 
    public T getResponse() { return this.response; }
    
    public ResponseDto(String statusCode, T response) {
        this.statusCode = statusCode;
        this.response = response;
    }

    public ResponseDto(String statusCode, T response, ActionRequest action) {
        this.statusCode = statusCode;
        this.response = response;
        Util.arSave(action.getIpAddress(), action.getRequest(), statusCode.toLowerCase());
    }
}

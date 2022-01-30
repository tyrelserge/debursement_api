package com.lin_q.debursement_api.exception;

import java.util.Date;

import lombok.Data;

@Data
public class NotFoundExceptionMessage {
    
    private String statusCode;
    private Date timestamp;
    private String message;
    private String source;

    public NotFoundExceptionMessage(String statusCode, Date timestamp, String message, String source) {
        super();
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.source = source;
    }
    
}

package com.lin_q.debursement_api.exception;

import java.util.Date;


public class NotFoundExceptionMessage {
    
    private String statusCode;
    private Date timestamp;

    public void setStatusCode(String statusCode) { this.statusCode = statusCode; } private String message; private String source; 
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; } 
    public void setMessage(String message) { this.message = message; } 
    public void setSource(String source) { this.source = source; } 
   
    public String getStatusCode() { return this.statusCode; }        
    public Date getTimestamp() { return this.timestamp; }        
    public String getMessage() { return this.message; } 
    public String getSource() { return this.source; }
    
    public NotFoundExceptionMessage(String statusCode, Date timestamp, String message, String source) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.source = source;
    }
}
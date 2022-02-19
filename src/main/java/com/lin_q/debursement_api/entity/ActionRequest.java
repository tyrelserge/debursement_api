package com.lin_q.debursement_api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "action_request")
public class ActionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_request_id")
    private Long actionRequestId;
    @Column(name = "ip_address")
    private String ipAddress;
    public void setActionRequestId(Long actionRequestId) { this.actionRequestId = actionRequestId; } 
    private String request; 
    @Column(name = "requested_at") 
    private String requestedAt; 
    private String status; 
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; } 
    public void setRequest(String request) { this.request = request; } 
    public void setRequestedAt(String requestedAt) { this.requestedAt = requestedAt; } 
    public void setStatus(String status) { this.status = status; } 

    public ActionRequest() {}
    public ActionRequest(Long actionRequestId, String ipAddress, String request, String requestedAt, String status) {
        this.actionRequestId = actionRequestId; this.ipAddress = ipAddress; this.request = request; this.requestedAt = requestedAt; this.status = status;
    }

    public Long getActionRequestId() {
        return this.actionRequestId;
    }

    public String getIpAddress() { return this.ipAddress; } 
    public String getRequest() {
        return this.request;
    }

    public String getRequestedAt() { return this.requestedAt; } 
    public String getStatus() {
        return this.status;
    }

    public ActionRequest(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}

package com.lin_q.debursement_api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "action_request")
public class ActionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_request_id")
    private Long actionRequestId;
    @Column(name = "ip_address")
    private String ipAddress;
    private String request;
    @Column(name = "requested_at")
    private String requestedAt;
    private String status;
    
    public ActionRequest(String ipAddress){
        this.ipAddress = ipAddress;
    }
}

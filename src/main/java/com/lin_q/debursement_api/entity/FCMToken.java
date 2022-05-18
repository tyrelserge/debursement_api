package com.lin_q.debursement_api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "fcm_token")
public class FCMToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    @Column(name = "fcm_token_id") 
    private Integer fcmTokenId;
    @Column(name = "user_id") 
	private Integer userId;
    @Column(name = "fcm_token_access") 
	private String fcmTokenAccess;
    @Column(name = "fcm_token_updatedOn") 
	private Date fcmTokenUpdatedOn;
}

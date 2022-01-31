package com.lin_q.debursement_api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "general_setting")
public class GeneralSetting {
    @Id
    private String currency;
}

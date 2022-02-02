package com.lin_q.debursement_api.repository;

import com.lin_q.debursement_api.entity.GeneralSetting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralSettingRepository extends JpaRepository<GeneralSetting, String> {

    public GeneralSetting findByStatus(String status);
    
}

package com.lin_q.debursement_api.repository;

import com.lin_q.debursement_api.entity.Office;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Integer> {
    
}

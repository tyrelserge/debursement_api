package com.lin_q.debursement_api.repository;

import com.lin_q.debursement_api.entity.ActionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRequestRepository extends JpaRepository<ActionRequest, Integer> {}
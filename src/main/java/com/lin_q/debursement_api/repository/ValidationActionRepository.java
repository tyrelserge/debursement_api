package com.lin_q.debursement_api.repository;

import com.lin_q.debursement_api.entity.ValidationAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationActionRepository extends JpaRepository<ValidationAction, Integer> {}

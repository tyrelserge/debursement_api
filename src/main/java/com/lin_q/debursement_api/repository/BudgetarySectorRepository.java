package com.lin_q.debursement_api.repository;

import com.lin_q.debursement_api.entity.BudgetarySector;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetarySectorRepository extends JpaRepository<BudgetarySector, Integer> {
    
}

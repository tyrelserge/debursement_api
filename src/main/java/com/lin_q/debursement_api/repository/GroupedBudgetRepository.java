package com.lin_q.debursement_api.repository;

import com.lin_q.debursement_api.entity.GroupedBudget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupedBudgetRepository extends JpaRepository<GroupedBudget, Integer> {
    
}

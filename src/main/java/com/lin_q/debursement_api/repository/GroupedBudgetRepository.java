package com.lin_q.debursement_api.repository;

import java.util.List;

import com.lin_q.debursement_api.entity.GroupedBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupedBudgetRepository extends JpaRepository<GroupedBudget, Integer> {

    @Query(value = "SELECT * FROM grouped_budget WHERE groupedbudget_name LIKE %:input%", nativeQuery = true)
    List<GroupedBudget> fetchSearchInputGroupedBudget(@Param("input") String input);
}

package com.lin_q.debursement_api.repository;

import java.util.List;

import com.lin_q.debursement_api.entity.BudgetIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetIndexRepository extends JpaRepository<BudgetIndex, Integer> {

    @Query(value = "SELECT * FROM budget_index  WHERE groupedbudget_id=:budgetid AND budgindex_name LIKE %:input%", nativeQuery = true)
    List<BudgetIndex> fetchSearchInputIndexOfGroupedBudget(@Param("budgetid") Integer groupedbudgetId, @Param("input") String input);

    @Query(value = "SELECT * FROM budget_index  WHERE groupedbudget_id=?", nativeQuery = true)
    List<BudgetIndex> fetchSearchInputEmptyIndexOfGroupedBudget(Integer groupedbudgetId);

}

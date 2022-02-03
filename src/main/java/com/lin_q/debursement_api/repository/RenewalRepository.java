package com.lin_q.debursement_api.repository;

import com.lin_q.debursement_api.entity.Renewal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RenewalRepository extends JpaRepository<Renewal, Integer> {

    @Modifying
    @Query(value = "INSERT INTO group_renewal(renewal_id, groupedbudget_id) VALUES (?,?)", nativeQuery = true)
    public Integer saveGroupedBudgetRenewal(Integer renewalId, Integer bgIndexId);
    
    @Modifying
    @Query(value = "INSERT INTO index_renewal(budgindex_id, renewal_id) VALUES (?,?)", nativeQuery = true)
    public Integer saveBudgetindexRenewal(Integer bgIndexId, Integer renewalId);
    
}

package com.lin_q.debursement_api.repository;

import com.lin_q.debursement_api.entity.Debursement;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DebursementRepository extends JpaRepository<Debursement, Integer> {

  @Query(value = "SELECT * FROM debursement WHERE user_id=?1 OR recipient_id=?1 ORDER BY debursement_id DESC", nativeQuery = true)
  List<Debursement> fetchUserDisbursementRequestList(Integer paramInteger);
  
  @Query(value = "SELECT * FROM debursement WHERE status<>'treated' AND status<>'rejected' ORDER BY debursement_id DESC", nativeQuery = true)
  List<Debursement> fetchDisbursementWaitingValidation();

  @Modifying
  @Query(value = "UPDATE debursement SET amount_approved=:amount,activate_debursement=:activated,updated_on=NOW(),current_step=:step, status=:status WHERE debursement_id=:disburs", nativeQuery = true)
  Integer updateDisbursementToValidation(
    @Param("disburs") Integer disbursId, 
    @Param("amount") Integer amountApproved, 
    @Param("activated") Date activatedDate, 
    @Param("step") Integer currentStep,
    @Param("status") String status);
  
}

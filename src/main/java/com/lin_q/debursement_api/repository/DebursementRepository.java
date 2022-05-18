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
  @Query(value = "UPDATE debursement SET amount_approved=:amount,activate_debursement=:activated,updated_on=NOW(),current_step=:step, status=:status, payment=:payment WHERE debursement_id=:disburs", nativeQuery = true)
  Integer updateDisbursementToValidation(
    @Param("disburs") Integer disbursId, 
    @Param("amount") Integer amountApproved, 
    @Param("activated") Date activatedDate, 
    @Param("step") Integer currentStep,
    @Param("status") String status,
    @Param("payment") String payment);

  @Query(value = "SELECT identifier FROM debursement WHERE year(created_on)=?1 AND month(created_on)=?2 ORDER BY debursement_id DESC LIMIT 1", nativeQuery = true)
  String fetchCurrentMonthLastDisbursement(String currentYear, String currentMonth);

  @Query(value = "SELECT d.debursement_id, d.reason, s.budgsector_id, s.budgsector_name, g.groupedbudget_name, u.civility, u.firstname, u.lastname, d.identifier, d.created_on, d.updated_on, d.amount_requested, d.amount_approved, d.status "+
  "FROM debursement d INNER JOIN user u, grouped_budget g, budget_index i, budgetary_sector s "+
  "WHERE i.budgindex_id = d.budgindex_id AND i.groupedbudget_id = g.groupedbudget_id AND s.budgsector_id = i.budgsector_id AND u.user_id = d.recipient_id "+
  "AND s.budgsector_id=?1 AND (d.created_on BETWEEN ?1 AND ?2 ' 23:59:59' OR d.updated_on BETWEEN ?1 AND ?2 ' 23:59:59') ORDER BY d.debursement_id DESC", nativeQuery = true)  //  AND d.activate_debursement IS NOT NULL
  List<Object[]> fetchDisbursementsByPeriodAndBgIndex(String budgindexId, String from, String to);

  @Query(value = "SELECT d.debursement_id, d.reason, s.budgsector_id, s.budgsector_name, g.groupedbudget_name, u.civility, u.firstname, u.lastname, d.identifier, d.created_on, d.updated_on, d.amount_requested, d.amount_approved, d.status "+
  "FROM debursement d INNER JOIN user u, grouped_budget g, budget_index i, budgetary_sector s "+
  "WHERE i.budgindex_id = d.budgindex_id AND i.groupedbudget_id = g.groupedbudget_id AND s.budgsector_id = i.budgsector_id AND u.user_id = d.recipient_id "+
  "AND (d.created_on BETWEEN ?1 AND ?2 ' 23:59:59' OR d.updated_on BETWEEN ?1 AND ?2 ' 23:59:59') ORDER BY d.debursement_id DESC", nativeQuery = true) //  AND d.activate_debursement IS NOT NULL
  List<Object[]> fetchDisbursementsByPeriod(String from, String to);

  @Query(value = "SELECT d.debursement_id, d.reason, s.budgsector_id, s.budgsector_name, g.groupedbudget_name, u.civility, u.firstname, u.lastname, d.identifier, d.created_on, d.updated_on, d.amount_requested, d.amount_approved, d.status "+
  "FROM debursement d INNER JOIN user u, grouped_budget g, budget_index i, budgetary_sector s "+
  "WHERE i.budgindex_id = d.budgindex_id AND i.groupedbudget_id = g.groupedbudget_id AND s.budgsector_id = i.budgsector_id AND u.user_id = d.recipient_id "+
  "AND d.status='treated' AND s.budgsector_id=?1 AND d.updated_on BETWEEN ?1 AND ?2 ' 23:59:59' ORDER BY d.debursement_id DESC", nativeQuery = true)
  List<Object[]> fetchTreatedDisbursementsByPeriodAndBgIndex(String sectorId, String from, String to);

  @Query(value = "SELECT d.debursement_id, d.reason, s.budgsector_id, s.budgsector_name, g.groupedbudget_name, u.civility, u.firstname, u.lastname, d.identifier, d.created_on, d.updated_on, d.amount_requested, d.amount_approved, d.status "+
  "FROM debursement d INNER JOIN user u, grouped_budget g, budget_index i, budgetary_sector s "+
  "WHERE i.budgindex_id = d.budgindex_id AND i.groupedbudget_id = g.groupedbudget_id AND s.budgsector_id = i.budgsector_id AND u.user_id = d.recipient_id "+
  "AND d.status='treated' AND d.updated_on BETWEEN ?1 AND ?2 ' 23:59:59' ORDER BY d.debursement_id DESC", nativeQuery = true)
  List<Object[]> fetchTreatedDisbursementsByPeriod(String from, String to);
  
}

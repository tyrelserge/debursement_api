package com.lin_q.debursement_api.repository;

import com.lin_q.debursement_api.entity.Debursement;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DebursementRepository extends JpaRepository<Debursement, Integer> {

  @Query(value = "SELECT * FROM debursement WHERE user_id=?1 OR recipient_id=?1 ORDER BY debursement_id DESC", nativeQuery = true)
  List<Debursement> fetchUserDisbursementRequestList(Integer paramInteger);
}

package com.lin_q.debursement_api.repository;

import com.lin_q.debursement_api.entity.Upwd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UpwdRepository extends JpaRepository<Upwd, Integer> {
  @Modifying
  @Query(value = "UPDATE upwd SET status='old' WHERE user_id=? AND status='active'", nativeQuery = true)
  Integer setOldPassword(Integer paramInteger);
  
  @Query(value = "SELECT pass_string FROM upwd WHERE user_id=? AND status='active'", nativeQuery = true)
  String fetchUserCurrentPwd(Integer paramInteger);
}

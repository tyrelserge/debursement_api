package com.lin_q.debursement_api.repository;

import com.lin_q.debursement_api.entity.Office;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface OfficeRepository extends JpaRepository<Office, Integer> {
  List<Office> findByUserId(Integer paramInteger);
  
  @Modifying
  @Query(value = "DELETE FROM user_office WHERE user_id=?", nativeQuery = true)
  void clearUserOffice(Integer paramInteger);
  
  @Modifying
  @Query(value = "INSERT INTO user_office(user_id, office_id) VALUES (?,?)", nativeQuery = true)
  Integer updateSetUserOffice(Integer paramInteger1, Integer paramInteger2);
  
  @Modifying
  @Query(value = "INSERT INTO office(user_id, department_id, profile_id, office_name, created_on, updated_on) VALUES (?1, ?2, ?3, ?4, ?5, NULL)", nativeQuery = true)
  Integer saveOffice(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, String paramString1, String paramString2);
  
  @Query(value = "SELECT * FROM office WHERE user_id=? ORDER BY office_id DESC LIMIT 1", nativeQuery = true)
  Office fetchUserLastOfficeEntered(Integer paramInteger);
}
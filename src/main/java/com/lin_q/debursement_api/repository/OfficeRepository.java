package com.lin_q.debursement_api.repository;

import java.util.List;

import com.lin_q.debursement_api.entity.Office;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface OfficeRepository extends JpaRepository<Office, Integer> {

    public List<Office> findByUserId(Integer userId);

    @Modifying
    @Query(value = "DELETE FROM user_office WHERE user_id=?", nativeQuery = true)
    public void clearUserOffice(Integer userId);

    @Modifying
    @Query(value = "INSERT INTO user_office(user_id, office_id) VALUES (?,?)", nativeQuery = true)
    public Integer updateSetUserOffice(Integer userId, Integer officeId);

    @Modifying
    @Query(value = "INSERT INTO office(user_id, department_id, profile_id, office_name, created_on, updated_on) "
        +"VALUES (?1, ?2, ?3, ?4, ?5, NULL)", nativeQuery = true)
    public Integer saveOffice(Integer userId, Integer departId, Integer profileId, String name, String createDate);
 
}

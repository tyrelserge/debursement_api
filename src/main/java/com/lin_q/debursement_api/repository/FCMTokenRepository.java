package com.lin_q.debursement_api.repository;

import java.util.Optional;

import com.lin_q.debursement_api.entity.FCMToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FCMTokenRepository extends JpaRepository<FCMToken, Integer> {

    Optional<FCMToken> findByUserId(Integer userId);
    
}

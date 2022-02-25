package com.lin_q.debursement_api.repository;

import com.lin_q.debursement_api.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String paramString);

  @Modifying
  @Query(value = "UPDATE user SET status=:status WHERE user_id=:userid", nativeQuery = true)
  Integer executeSetUserStatus(@Param("userid") Integer userId, @Param("status") String status);
}
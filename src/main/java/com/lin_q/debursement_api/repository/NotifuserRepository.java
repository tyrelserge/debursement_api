package com.lin_q.debursement_api.repository;

import java.util.List;

import com.lin_q.debursement_api.entity.Notifuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface NotifuserRepository extends JpaRepository<Notifuser, Integer> {

    @Query(value = "SELECT * FROM notifuser WHERE user_id = ? ORDER BY notifuser_id DESC", nativeQuery = true)
    List<Notifuser> fetchUserNotificationsList(Integer userId);

    @Modifying
    @Query(value = "INSERT INTO notifuser(user_id, notification_id, notification_date, notification_seen, notification_opened, notification_link) VALUES (?1, ?2, NOW(), NULL, NULL, ?3)", nativeQuery = true)
    Integer saveUserNotification(Integer userId, Integer notificationId, String notificationLink);
    
    @Query(value = "SELECT * FROM notifuser WHERE user_id=? ORDER BY notifuser_id DESC LIMIT 1", nativeQuery = true)
    Notifuser fetchLastUserDavingNotification(Integer userId);

    @Modifying
    @Query(value = "UPDATE notifuser SET notification_seen=NOW() WHERE notifuser_id=?1", nativeQuery = true)
    int executeSetSeenNotification(Integer notificationId);

    @Modifying
    @Query(value = "UPDATE notifuser SET notification_opened=NOW() WHERE notifuser_id=?1", nativeQuery = true)
    Integer executeSetOpenedNotification(Integer notifuserId);
}

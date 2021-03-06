package com.lin_q.debursement_api.service;

import com.lin_q.debursement_api.entity.Department;
import com.lin_q.debursement_api.entity.FCMToken;
import com.lin_q.debursement_api.entity.GeneralSetting;
import com.lin_q.debursement_api.entity.Notification;
import com.lin_q.debursement_api.entity.Notifuser;
import com.lin_q.debursement_api.entity.Office;
import com.lin_q.debursement_api.entity.Profile;
import com.lin_q.debursement_api.entity.Role;
import com.lin_q.debursement_api.entity.User;
import com.lin_q.debursement_api.model.DepartmentReq;
import com.lin_q.debursement_api.model.FCMTokenReq;
import com.lin_q.debursement_api.model.GSettingsReq;
import com.lin_q.debursement_api.model.Login;
import com.lin_q.debursement_api.model.NotificationReq;
import com.lin_q.debursement_api.model.NotifyUserReq;
import com.lin_q.debursement_api.model.OfficeReq;
import com.lin_q.debursement_api.model.OfficeSet;
import com.lin_q.debursement_api.model.ProfileReq;
import com.lin_q.debursement_api.model.RoleReq;
import com.lin_q.debursement_api.model.UserReq;
import java.util.List;

public interface UserService {
  void saveActionRequest(String paramString1, String paramString2, String paramString3);  
  List<Role> getRoleList();  
  List<Profile> getProfileList();  
  Department getDepartment(Integer departmentId);
  List<Department> getDepartmentsList();  
  List<Office> getOfficesList();  
  Role toCreateRole(RoleReq paramRoleReq);  
  Role toUpdateRole(Integer paramInteger, RoleReq paramRoleReq);  
  Profile toCreateUserProfile(ProfileReq paramProfileReq);  
  Profile toUpdateUserProfile(Integer paramInteger, ProfileReq paramProfileReq);  
  Department toCreateUserDepartment(DepartmentReq paramDepartmentReq);  
  Office toCreateUserOffice(OfficeReq paramOfficeReq);  
  List<User> getAllUsers();
  User getUserData(Integer paramInteger);  
  Object toCreateUser(UserReq paramUserReq);  
  String addPassword(Integer paramInteger, String paramString);  
  User toLogin(Login paramLogin);
  String setUserOffices(OfficeSet paramOfficeSet);  
  List<GeneralSetting> getAllSettings();
  GeneralSetting toUpdateGeneralSettings(GSettingsReq paramGSettingsReq);
  List<Notification> getNotificationMessagesList();
  Notification getNotification(Integer notificationId);
  Notification saveNotification(NotificationReq noticeDate);
  Notification setUpdateNotification(Integer notificationId, NotificationReq noticeDate);
  void setDeleteNotification(Integer notificationId);
  List<Notifuser> getUserNotificationsList(Integer userId);
  Notifuser getUserNotification(Integer notificationId);
  Notifuser sendUserNotification(NotifyUserReq noticeDate);
  String setSeenNotification(Integer userId);
  Notifuser setOpenedNotification(Integer notificationId);
  User setUserStatus(Integer userId, String status);
  List<User> getSearchUserByname(String byname);
FCMToken toAddFCMToken(FCMTokenReq fcmToken);
FCMToken getUserFCMToken(Integer userId);
}
package com.lin_q.debursement_api.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.lin_q.debursement_api.Constants;
import com.lin_q.debursement_api.entity.ActionRequest;
import com.lin_q.debursement_api.entity.Department;
import com.lin_q.debursement_api.entity.GeneralSetting;
import com.lin_q.debursement_api.entity.Notification;
import com.lin_q.debursement_api.entity.Notifuser;
import com.lin_q.debursement_api.entity.Office;
import com.lin_q.debursement_api.entity.Profile;
import com.lin_q.debursement_api.entity.Role;
import com.lin_q.debursement_api.entity.Upwd;
import com.lin_q.debursement_api.entity.User;
import com.lin_q.debursement_api.exception.ResourceNotFoundException;
import com.lin_q.debursement_api.model.DepartmentReq;
import com.lin_q.debursement_api.model.GSettingsReq;
import com.lin_q.debursement_api.model.Login;
import com.lin_q.debursement_api.model.NotificationReq;
import com.lin_q.debursement_api.model.NotifyUserReq;
import com.lin_q.debursement_api.model.OfficeReq;
import com.lin_q.debursement_api.model.OfficeSet;
import com.lin_q.debursement_api.model.ProfileReq;
import com.lin_q.debursement_api.model.RoleReq;
import com.lin_q.debursement_api.model.UserReq;
import com.lin_q.debursement_api.repository.ActionRequestRepository;
import com.lin_q.debursement_api.repository.DepartmentRepository;
import com.lin_q.debursement_api.repository.GeneralSettingRepository;
import com.lin_q.debursement_api.repository.NotificationRepository;
import com.lin_q.debursement_api.repository.NotifuserRepository;
import com.lin_q.debursement_api.repository.OfficeRepository;
import com.lin_q.debursement_api.repository.ProfileRepository;
import com.lin_q.debursement_api.repository.RoleRepository;
import com.lin_q.debursement_api.repository.UpwdRepository;
import com.lin_q.debursement_api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private ActionRequestRepository actionRequestRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UpwdRepository userPasswordRepository;
  @Autowired
  private GeneralSettingRepository generalSettingRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private ProfileRepository profileRepository;
  @Autowired
  private DepartmentRepository departmentRepository;
  @Autowired
  private OfficeRepository officeRepository;

  @Autowired
  private NotificationRepository notificationRepository;
  
  @Autowired
  private NotifuserRepository notifuserRepository;
  
  public void saveActionRequest(String remoteAddr, String action, String requestStatus) {
    ActionRequest actionRequest = new ActionRequest(null, remoteAddr, action, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()), requestStatus);
    
    this.actionRequestRepository.save(actionRequest);
  }

  
  public List<Role> getRoleList() {
    List<Role> roles = this.roleRepository.findAll();
    return roles;
  }

  
  public List<Profile> getProfileList() {
    List<Profile> profiles = this.profileRepository.findAll();
    return profiles;
  }
  
  
  @Override
  public Department getDepartment(Integer departmentId) {
    return departmentRepository.findById(departmentId).orElseThrow(
      () -> new ResourceNotFoundException("Department is not found"));
  }

  
  public List<Department> getDepartmentsList() {
    List<Department> departments = this.departmentRepository.findAll();
    return departments;
  }

  
  public List<Office> getOfficesList() {
    List<Office> offices = this.officeRepository.findAll();
    return offices;
  }


  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }


  public User getUserData(Integer userId) {
    User user = (User)this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("The user id " + userId + " is not found"));

    
    return user;
  }


  
  public Object toCreateUser(UserReq userData) throws JsonParseException {

    Optional<User> optional = this.userRepository.findByEmail(userData.getEmail().trim());
    if (optional.isPresent()) {
      return Constants.EXISTS;
    }

    
    User user = new User(
      null, 
      userData.getLastname(), 
      userData.getFirstname(), 
      userData.getCivility()!=null ? userData.getCivility().equalsIgnoreCase("M") ? "H" : "F" : null, 
      StringUtils.capitalize(userData.getCivility().toLowerCase()), 
      userData.getEmail(), 
      userData.getMobile(), 
      new Date(), 
      null, 
      userData.getStatus(), 
      null);

    User savedUser = (User)this.userRepository.save(user);
    addPassword(savedUser.getUserId(), userData.getPassword());


    
    if (userData.getOfficeIds().isPresent() && !userData.getOfficeIds().isEmpty()) {
      Integer[] officeIds = userData.getOfficeIds().get();
      OfficeSet userOffices = new OfficeSet(savedUser.getUserId(), officeIds);
      if (setUserOffices(userOffices).equalsIgnoreCase("DONE")) {
        List<Office> offices = new ArrayList<>();
        for (Integer officeId : officeIds) {
          offices.add(this.officeRepository.findById(officeId).get());
        }
        savedUser.setOffices(offices);
      } 
      return savedUser;
    } 
    
    return savedUser;
  }


  
  public String addPassword(Integer userId, String pwd) {
    this.userPasswordRepository.setOldPassword(userId);
    
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String passwordHash = encoder.encode(pwd);
    
    Upwd upwd = new Upwd();
    upwd.setUserId(userId);
    upwd.setPassString(passwordHash);
    upwd.setCreatedOn(new Date());
    upwd.setStatus("active");
    
    this.userPasswordRepository.save(upwd);
    return "DONE";
  }


  
  public User toLogin(Login loginData) {

    if(loginData.getUsername()==null || loginData.getPassword()==null)
      return null;

    Optional<User> optional = this.userRepository.findByEmail(loginData.getUsername().trim());
    if (!optional.isPresent()) return null;
    User user = optional.get();
    
    String loginPwd = loginData.getPassword();
    String currentPwd = this.userPasswordRepository.fetchUserCurrentPwd(user.getUserId());
    
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    if (!encoder.matches(loginPwd, currentPwd)) return null;
    
    return user;
  }

  @Override
  public List<GeneralSetting> getAllSettings() {
    return generalSettingRepository.findAll();
  }
  
  public GeneralSetting toUpdateGeneralSettings(GSettingsReq gSettingsReq) {
    GeneralSetting currentSetting = this.generalSettingRepository.findByStatus("active");
    
    GeneralSetting setting = new GeneralSetting();
    
    setting.setGeneralSettingId(currentSetting.getGeneralSettingId());
    setting.setUserId(gSettingsReq.getUserId());
    setting.setCurrency(gSettingsReq.getCurrency());
    setting.setValidationStep(gSettingsReq.getValidationStep());
    setting.setCreatedOn(currentSetting.getCreatedOn());
    setting.setUpdatedOn(new Date());
    setting.setStatus(currentSetting.getStatus());
    
    return (GeneralSetting)this.generalSettingRepository.save(setting);
  }


  
  public Role toCreateRole(RoleReq roleData) {
    Role role = new Role();
    role.setUserId(roleData.getUserId());
    role.setRoleName(roleData.getRoleName());
    role.setRoleLevel(roleData.getRoleLevel());
    role.setCreatedDate(new Date());
    
    return (Role)this.roleRepository.save(role);
  }


  
  public Role toUpdateRole(Integer roleId, RoleReq roleData) {
    Optional<Role> currentRole = this.roleRepository.findById(roleId);
    if (!currentRole.isPresent()) {
      return null;
    }
    Role role = new Role();
    role.setRoleId(roleId);
    role.setUserId(roleData.getUserId());
    role.setRoleName(roleData.getRoleName());
    role.setRoleLevel(roleData.getRoleLevel());
    role.setCreatedDate(((Role)currentRole.get()).getCreatedDate());
    role.setUpdatedDate(new Date());
    
    return (Role)this.roleRepository.save(role);
  }


  
  public Profile toCreateUserProfile(ProfileReq profileReq) {
    Profile profile = new Profile();
    profile.setUserId(profileReq.getUserId());
    profile.setProfileName(profileReq.getProfileName());
    profile.setProfileLevel(profileReq.getProfileLevel());
    profile.setCreatedOn(new Date());
    
    return (Profile)this.profileRepository.save(profile);
  }


  
  public Profile toUpdateUserProfile(Integer profileId, ProfileReq profileReq) {
    Optional<Profile> currentProfile = this.profileRepository.findById(profileId);
    if (!currentProfile.isPresent()) {
      return null;
    }
    Profile profile = new Profile();
    
    profile.setProfileId(profileId);
    profile.setUserId(profileReq.getUserId());
    profile.setProfileName(profileReq.getProfileName());
    profile.setProfileLevel(profileReq.getProfileLevel());
    profile.setCreatedOn(((Profile)currentProfile.get()).getCreatedOn());
    profile.setCreatedOn(new Date());
    
    return (Profile)this.profileRepository.save(profile);
  }


  
  public Department toCreateUserDepartment(DepartmentReq departReq) {
    Department department = new Department();
    department.setUserId(departReq.getUserId());
    department.setDepartmentName(departReq.getDepartmentName());
    department.setCreatedOn(new Date());
    
    return (Department)this.departmentRepository.save(department);
  }


  
  public Office toCreateUserOffice(OfficeReq officeReq) {
    Integer userId = officeReq.getUserId();
    Integer departId = officeReq.getDepartmentId();
    Integer profileId = officeReq.getProfileId();
    String name = officeReq.getOfficeName();
    String createDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
    
    Integer res = this.officeRepository.saveOffice(userId, departId, profileId, name, createDate);
    if (res.intValue() != 1) {
      return null;
    }
    return this.officeRepository.fetchUserLastOfficeEntered(userId);
  }


  
  public String setUserOffices(OfficeSet userOffices) {

    Integer userId = userOffices.getUserId();
    Integer[] offices = userOffices.getOfficeIds();
      
    if (offices!=null && offices.length >= 1) {
  
      this.officeRepository.clearUserOffice(userId);

      for (Integer officeId : offices) {
        this.officeRepository.updateSetUserOffice(userId, officeId);
      }
      
    } else {
      return "EMPTY";
    }
    
    return "DONE";
  }



  @Override
  public List<Notification> getNotificationMessagesList() {
    return notificationRepository.findAll();
  }

  @Override
  public Notification getNotification(Integer notificationId) {
   return notificationRepository.findById(notificationId).orElseThrow(
     () -> new ResourceNotFoundException("Notification non trouvé"));
  }


  @Override
  public Notification saveNotification(NotificationReq noticeDate) {
    
    Notification notice = new Notification();
    
    notice.setNotificationSubject(noticeDate.getNotificationSubject());
    notice.setNotificationDetails(noticeDate.getNotificationDetails());
  
    return notificationRepository.save(notice);
  }

  @Override
  public Notification setUpdateNotification(Integer notificationId, NotificationReq noticeDate) {
  
    Optional<Notification> current = notificationRepository.findById(notificationId);

    if (!current.isPresent())
      return null;

    Notification notice = new Notification();

    notice.setNotificationId(current.get().getNotificationId());
    notice.setNotificationSubject(noticeDate.getNotificationSubject());
    notice.setNotificationDetails(noticeDate.getNotificationDetails());

    return notificationRepository.save(notice);
  }

  @Override
  public void setDeleteNotification(Integer notificationId) {
    notificationRepository.deleteById(notificationId);
  }

  

  @Override
  public List<Notifuser> getUserNotificationsList(Integer userId) {
    return notifuserRepository.fetchUserNotificationsList(userId);
  }

  @Override
  public Notifuser getUserNotification(Integer notificationId) {
    return notifuserRepository.findById(notificationId).orElseThrow(
      () -> new ResourceNotFoundException("Notification non trouvé"));
  }


  @Override
  public Notifuser sendUserNotification(NotifyUserReq noticeData) {

    Integer res = notifuserRepository.saveUserNotification(
      noticeData.getUserId(), noticeData.getNotificationId(), noticeData.getNotificationLink());

      if(res!=1)
        return null;

      return notifuserRepository.fetchLastUserDavingNotification(noticeData.getUserId());
  }

  
  @Override
  public String setSeenNotification(Integer userId) {
    Integer res = notifuserRepository.executeSetSeenNotification(userId);
    return res!=1 ? null : "DONE";
  }


  @Override
  public Notifuser setOpenedNotification(Integer notificationId) {
    return notifuserRepository.executeSetOpenedNotification(notificationId)!=1 ? null : 
      notifuserRepository.findById(notificationId).get();
  }


  @Override
  public User setUserStatus(Integer userId, String status) {
    return userRepository.executeSetUserStatus(userId, status)!=1 ? null : 
      userRepository.findById(userId).get();
  }


  public List<User> getSearchUserByname(String byname) {
    String lastname = byname;
    String firstname = byname;
    List<User> users = userRepository.fetchUserbyFilds(lastname, firstname);

    return users;
  }
  


}
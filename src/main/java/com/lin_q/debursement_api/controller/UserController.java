package com.lin_q.debursement_api.controller;

import com.lin_q.debursement_api.Constants;
import com.lin_q.debursement_api.entity.Department;
import com.lin_q.debursement_api.entity.GeneralSetting;
import com.lin_q.debursement_api.entity.Office;
import com.lin_q.debursement_api.entity.Profile;
import com.lin_q.debursement_api.entity.Role;
import com.lin_q.debursement_api.entity.User;
import com.lin_q.debursement_api.model.DepartmentReq;
import com.lin_q.debursement_api.model.GSettingsReq;
import com.lin_q.debursement_api.model.Login;
import com.lin_q.debursement_api.model.OfficeReq;
import com.lin_q.debursement_api.model.OfficeSet;
import com.lin_q.debursement_api.model.ProfileReq;
import com.lin_q.debursement_api.model.PwdReq;
import com.lin_q.debursement_api.model.ResponseDto;
import com.lin_q.debursement_api.model.RoleReq;
import com.lin_q.debursement_api.model.UserReq;
import com.lin_q.debursement_api.service.UserService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping({"/user"})
public class UserController
{
  @Autowired
  private UserService userService;
  
  @GetMapping({"/roles"})
  public ResponseEntity<ResponseDto<List<Role>>> Roles() {
    List<Role> res = this.userService.getRoleList();
    return (res != null && !res.isEmpty()) ? ResponseEntity.ok(new ResponseDto<List<Role>>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<List<Role>>("ERROR", null));
  }
  
  @GetMapping({"/profiles"})
  public ResponseEntity<ResponseDto<List<Profile>>> Profiles() {
    List<Profile> res = this.userService.getProfileList();
    return (res != null && !res.isEmpty()) ? ResponseEntity.ok(new ResponseDto<List<Profile>>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<List<Profile>>("ERROR", null));
  }
  
  @GetMapping({"/departments"})
  public ResponseEntity<ResponseDto<List<Department>>> Departments() {
    List<Department> res = this.userService.getDepartmentsList();
    return (res != null && !res.isEmpty()) ? ResponseEntity.ok(new ResponseDto<List<Department>>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<List<Department>>("ERROR", null));
  }
  
  @GetMapping({"/offices"})
  public ResponseEntity<ResponseDto<List<Office>>> Offices() {
    List<Office> res = this.userService.getOfficesList();
    return (res != null && !res.isEmpty()) ? ResponseEntity.ok(new ResponseDto<List<Office>>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<List<Office>>("ERROR", null));
  }
  
  @GetMapping({"/{userid}"})
  public ResponseEntity<ResponseDto<User>> UserData(@PathVariable("userid") Integer userId) {
    User res = this.userService.getUserData(userId);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<User>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<User>("ERROR", null));
  }


  
  @PostMapping({"/role/create"})
  public ResponseEntity<ResponseDto<Role>> CreateRole(@RequestBody RoleReq roleReq) {
    Role res = this.userService.toCreateRole(roleReq);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<Role>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<Role>("ERROR", null));
  }

  
  @PutMapping({"/role/{roleId}"})
  public ResponseEntity<ResponseDto<Role>> UpdateRole(@RequestBody RoleReq roleData, @PathVariable("roleId") Integer roleId) {
    Role res = this.userService.toUpdateRole(roleId, roleData);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<Role>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<Role>("ERROR", null));
  }
  
  @PostMapping({"/profile/create"})
  public ResponseEntity<ResponseDto<Profile>> CreateUserProfile(@RequestBody ProfileReq profileReq) {
    Profile res = this.userService.toCreateUserProfile(profileReq);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<Profile>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<Profile>("ERROR", null));
  }

  
  @PutMapping({"/profile/{profileId}"})
  public ResponseEntity<ResponseDto<Profile>> Updateprofile(@RequestBody ProfileReq profileData, @PathVariable("profileId") Integer profileId) {
    Profile res = this.userService.toUpdateUserProfile(profileId, profileData);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<Profile>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<Profile>("ERROR", null));
  }
  
  @PostMapping({"/department/create"})
  public ResponseEntity<ResponseDto<Department>> CreateUserDepartment(@RequestBody DepartmentReq departReq) {
    Department res = this.userService.toCreateUserDepartment(departReq);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<Department>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<Department>("ERROR", null));
  }
  
  @PostMapping({"/office/create"})
  public ResponseEntity<ResponseDto<Office>> CreateUserOffice(@RequestBody OfficeReq officeReq) {
    Office res = this.userService.toCreateUserOffice(officeReq);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<Office>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<Office>("ERROR", null));
  }
  
  @PostMapping({"/create"})
  public ResponseEntity<ResponseDto<Object>> CreateUser(@RequestBody UserReq userData) {
    Object res = this.userService.toCreateUser(userData);
    return (res != null) ? 
      !res.equals("ALREADY_EXISTS") ? 
      ResponseEntity.ok(new ResponseDto<Object>(Constants.SUCCESS, res)) : 
      ResponseEntity.ok(new ResponseDto<Object>(Constants.EXISTS, null)) :
      ResponseEntity.ok(new ResponseDto<Object>(Constants.ERROR, null));
  }
  
  @PostMapping({"/login"})
  public ResponseEntity<ResponseDto<User>> UserLogin(@RequestBody Login LoginData) {
    User res = this.userService.toLogin(LoginData);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<User>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<User>("ERROR", null));
  }

  
  @PostMapping({"/{userid}/setpassword"})
  public ResponseEntity<ResponseDto<String>> UpdateUserPwd(@PathVariable("userid") Integer userId, @RequestBody PwdReq userData) {
    String res = this.userService.addPassword(userId, userData.getPwd());
    return (res != null) ? ResponseEntity.ok(new ResponseDto<String>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<String>("ERROR", null));
  }

  @PutMapping({"/general-settings/update"})
  public ResponseEntity<ResponseDto<GeneralSetting>> UpdateGeneralSettings(@RequestBody GSettingsReq gSettingsReq) {
    GeneralSetting res = this.userService.toUpdateGeneralSettings(gSettingsReq);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<GeneralSetting>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<GeneralSetting>("ERROR", null));
  }
  
  @PostMapping({"/office-set"})
  public ResponseEntity<ResponseDto<String>> UserOffices(@RequestBody OfficeSet offices) {
    String res = this.userService.setUserOffices(offices);
    return (res != null) ? ResponseEntity.ok(new ResponseDto<String>("SUCCESS", res)) : 
      ResponseEntity.ok(new ResponseDto<String>("ERROR", null));
  }
  
}

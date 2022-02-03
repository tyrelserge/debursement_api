package com.lin_q.debursement_api.controller;

import java.util.List;

import com.lin_q.debursement_api.Constants;
import com.lin_q.debursement_api.entity.Department;
import com.lin_q.debursement_api.entity.GeneralSetting;
import com.lin_q.debursement_api.entity.Office;
import com.lin_q.debursement_api.entity.Profile;
import com.lin_q.debursement_api.entity.Role;
import com.lin_q.debursement_api.entity.User;
import com.lin_q.debursement_api.model.DepartmentReq;
import com.lin_q.debursement_api.model.GSettingsReq;
import com.lin_q.debursement_api.model.OfficeReq;
import com.lin_q.debursement_api.model.OfficeSet;
import com.lin_q.debursement_api.model.ProfileReq;
import com.lin_q.debursement_api.model.ResponseDto;
import com.lin_q.debursement_api.model.RoleReq;
import com.lin_q.debursement_api.model.UserReq;
import com.lin_q.debursement_api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/user"})
public class UserController {

    // private static final Logger log = LoggerFactory.getLogger(BudgetController.class);

    @Autowired
    private UserService userService; 

    @GetMapping(value = "/roles")
    public ResponseEntity<ResponseDto<List<Role>>> Roles() {
        List<Role> res = userService.getRoleList();
        return res !=null ? ResponseEntity.ok(new ResponseDto<List<Role>>(Constants.SUCCESS, res)):
            ResponseEntity.ok(new ResponseDto<List<Role>>(Constants.ERROR, null));
    }
    
    @GetMapping(value = "/profiles")
    public ResponseEntity<ResponseDto<List<Profile>>> Profiles() {
        List<Profile> res = userService.getProfileList();
        return res !=null ? ResponseEntity.ok(new ResponseDto<List<Profile>>(Constants.SUCCESS, res)):
            ResponseEntity.ok(new ResponseDto<List<Profile>>(Constants.ERROR, null));
    }

    @GetMapping(value = "/departments")
    public ResponseEntity<ResponseDto<List<Department>>> Departments() {
        List<Department> res = userService.getDepartmentsList();
        return res !=null ? ResponseEntity.ok(new ResponseDto<List<Department>>(Constants.SUCCESS, res)):
            ResponseEntity.ok(new ResponseDto<List<Department>>(Constants.ERROR, null));
    }

    @GetMapping(value = "/offices")
    public ResponseEntity<ResponseDto<List<Office>>> Offices() {
        List<Office> res = userService.getOfficesList();
        return res !=null ? ResponseEntity.ok(new ResponseDto<List<Office>>(Constants.SUCCESS, res)):
            ResponseEntity.ok(new ResponseDto<List<Office>>(Constants.ERROR, null));
    }
    
    @GetMapping(value="/{userid}")
    public ResponseEntity<ResponseDto<User>> UserData(@PathVariable("userid") Integer userId) {
        User res = userService.getUserData(userId);
        return res!=null ? ResponseEntity.ok(new ResponseDto<User>(Constants.SUCCESS, res)):
        ResponseEntity.ok(new ResponseDto<User>(Constants.ERROR, null));
    }

    // @GetMapping(value = "/office/{userid}")
    // public ResponseEntity<ResponseDto<List<Office>>> CreateUserOffice(@PathVariable("userid") Integer userId) {
    //     List<Office> res = userService.getUserOffice(userId);
    //     return res !=null ? ResponseEntity.ok(new ResponseDto<List<Office>>(Constants.SUCCESS, res)):
    //         ResponseEntity.ok(new ResponseDto<List<Office>>(Constants.ERROR, null));
    // }


    //-- CREATING

    
    @PostMapping(value = "/role/create")
    public ResponseEntity<ResponseDto<Role>> CreateRole(@RequestBody RoleReq roleReq) {
        Role res = userService.toCreateRole(roleReq);
        return res !=null ? ResponseEntity.ok(new ResponseDto<Role>(Constants.SUCCESS, res)):
            ResponseEntity.ok(new ResponseDto<Role>(Constants.ERROR, null));
    }
        
    @PutMapping(value = "/role/{roleId}")
    public ResponseEntity<ResponseDto<Role>> UpdateRole(@RequestBody RoleReq roleData, 
        @PathVariable("roleId") Integer roleId) {            
        Role res = userService.toUpdateRole(roleId, roleData);
        return res !=null ? ResponseEntity.ok(new ResponseDto<Role>(Constants.SUCCESS, res)):
            ResponseEntity.ok(new ResponseDto<Role>(Constants.ERROR, null));
    }
    
    @PostMapping(value = "/profile/create")
    public ResponseEntity<ResponseDto<Profile>> CreateUserProfile(@RequestBody ProfileReq profileReq) {
        Profile res = userService.toCreateUserProfile(profileReq);
        return res !=null ? ResponseEntity.ok(new ResponseDto<Profile>(Constants.SUCCESS, res)):
            ResponseEntity.ok(new ResponseDto<Profile>(Constants.ERROR, null));
    }
    
    @PutMapping(value = "/profile/{profileId}")
    public ResponseEntity<ResponseDto<Profile>> Updateprofile(@RequestBody ProfileReq profileData, 
        @PathVariable("profileId") Integer profileId) {            
        Profile res = userService.toUpdateUserProfile(profileId, profileData);
        return res !=null ? ResponseEntity.ok(new ResponseDto<Profile>(Constants.SUCCESS, res)):
            ResponseEntity.ok(new ResponseDto<Profile>(Constants.ERROR, null));
    }
    
    @PostMapping(value = "/department/create")
    public ResponseEntity<ResponseDto<Department>> CreateUserDepartment(@RequestBody DepartmentReq departReq) {
        Department res = userService.toCreateUserDepartment(departReq);
        return res !=null ? ResponseEntity.ok(new ResponseDto<Department>(Constants.SUCCESS, res)):
            ResponseEntity.ok(new ResponseDto<Department>(Constants.ERROR, null));
    }

    @PostMapping(value = "/office/create")
    public ResponseEntity<ResponseDto<Office>> CreateUserOffice(@RequestBody OfficeReq officeReq) {
        Office res = userService.toCreateUserOffice(officeReq);
        return res !=null ? ResponseEntity.ok(new ResponseDto<Office>(Constants.SUCCESS, res)):
            ResponseEntity.ok(new ResponseDto<Office>(Constants.ERROR, null));
    }

    
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDto<User>> CreateUser(@RequestBody UserReq userData) {
        User res = userService.toCreateUser(userData);
        return res!=null ? ResponseEntity.ok(new ResponseDto<User>(Constants.SUCCESS, res)):
        ResponseEntity.ok(new ResponseDto<User>(Constants.ERROR, null));
    }

    @PutMapping(value = "/general-settings/update")
    public ResponseEntity<ResponseDto<GeneralSetting>> UpdateGeneralSettings(@RequestBody GSettingsReq gSettingsReq) {

        // Integer userId = gSettingsReq.getUserId();
        // String userProfile = userService.getUserProfile(userId);
        
        // String[] profileLevel = {"100", "110", "111"};
        // List<String> list = Arrays.asList(userProfile);

        // if(!list.contains(staff.getStaffId())) return null;

        GeneralSetting res = userService.toUpdateGeneralSettings(gSettingsReq);
        return res !=null ? ResponseEntity.ok(new ResponseDto<GeneralSetting>(Constants.SUCCESS, res)):
            ResponseEntity.ok(new ResponseDto<GeneralSetting>(Constants.ERROR, null));
    }

    @PostMapping(value = "/office-set")
    public ResponseEntity<ResponseDto<String>> UserOffices(@RequestBody OfficeSet offices) {
        String res = userService.setUserOffices(offices);
        return res !=null ? ResponseEntity.ok(new ResponseDto<String>(Constants.SUCCESS, res)):
            ResponseEntity.ok(new ResponseDto<String>(Constants.ERROR, null));
    }

}

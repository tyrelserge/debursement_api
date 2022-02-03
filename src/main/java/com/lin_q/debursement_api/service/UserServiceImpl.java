package com.lin_q.debursement_api.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.lin_q.debursement_api.entity.ActionRequest;
import com.lin_q.debursement_api.entity.Department;
import com.lin_q.debursement_api.entity.GeneralSetting;
import com.lin_q.debursement_api.entity.Office;
import com.lin_q.debursement_api.entity.Profile;
import com.lin_q.debursement_api.entity.Role;
import com.lin_q.debursement_api.entity.User;
import com.lin_q.debursement_api.exception.ResourceNotFoundException;
import com.lin_q.debursement_api.model.DepartmentReq;
import com.lin_q.debursement_api.model.GSettingsReq;
import com.lin_q.debursement_api.model.OfficeReq;
import com.lin_q.debursement_api.model.OfficeSet;
import com.lin_q.debursement_api.model.ProfileReq;
import com.lin_q.debursement_api.model.RoleReq;
import com.lin_q.debursement_api.model.UserReq;
import com.lin_q.debursement_api.repository.ActionRequestRepository;
import com.lin_q.debursement_api.repository.DepartmentRepository;
import com.lin_q.debursement_api.repository.GeneralSettingRepository;
import com.lin_q.debursement_api.repository.OfficeRepository;
import com.lin_q.debursement_api.repository.ProfileRepository;
import com.lin_q.debursement_api.repository.RoleRepository;
import com.lin_q.debursement_api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ActionRequestRepository actionRequestRepository;

    @Autowired
    private UserRepository userRepository;

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

    @Override
    public void saveActionRequest(String remoteAddr, String action, String requestStatus) {
        
        ActionRequest actionRequest = new ActionRequest(
            null, 
            remoteAddr, 
            action, 
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), 
            requestStatus
        );

        actionRequestRepository.save(actionRequest);
    }

    @Override
    public List<Role> getRoleList() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }

    @Override
    public List<Profile> getProfileList() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles;
    }

    @Override
    public List<Department> getDepartmentsList() {
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    @Override
    public List<Office> getOfficesList() {
        List<Office> offices = officeRepository.findAll();
        return offices;
    }

    
    @Override
    public User getUserData(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(
            () -> new ResourceNotFoundException("The user id " + userId + " is not found"));

        return user;
    }
    
    @Override
    public User toCreateUser(UserReq userData) {
        User user = new User(
            null,
            userData.getLastname(),
            userData.getFirstname(),
            userData.getGender(),
            userData.getEmail(),
            userData.getMobile(),
            userData.getPassword(),
            new Date(),
            null,
            "0",       // "0" => inactive | 1 => active | -1 => delete, 
            null
        );
        return userRepository.save(user);
    }

    @Override
    public GeneralSetting toUpdateGeneralSettings(GSettingsReq gSettingsReq) {
        
        GeneralSetting currentSetting = generalSettingRepository.findByStatus("active");
        
        GeneralSetting setting = new GeneralSetting();

        setting.setGeneralSettingId(currentSetting.getGeneralSettingId());
        setting.setUserId(gSettingsReq.getUserId());
        setting.setCurrency(gSettingsReq.getCurrency());
        setting.setCreatedOn(currentSetting.getCreatedOn());
        setting.setUpdatedOn(new Date());
        setting.setStatus(currentSetting.getStatus());
        
        return generalSettingRepository.save(setting);
    }

    @Override
    public Role toCreateRole(RoleReq roleData) {
        
        Role role = new Role();
        role.setUserId(roleData.getUserId());
        role.setRoleName(roleData.getRoleName());
        role.setRoleLevel(roleData.getRoleLevel());
        role.setCreatedDate(new Date());

        return roleRepository.save(role);
    }

    @Override
    public Role toUpdateRole(Integer roleId, RoleReq roleData) {
        
        Optional<Role> currentRole = roleRepository.findById(roleId);
        if(!currentRole.isPresent())
            return null;

        Role role = new Role();
        role.setRoleId(roleId);
        role.setUserId(roleData.getUserId());
        role.setRoleName(roleData.getRoleName());
        role.setRoleLevel(roleData.getRoleLevel());
        role.setCreatedDate(currentRole.get().getCreatedDate());
        role.setUpdatedDate(new Date());

        return roleRepository.save(role);
    }

    @Override
    public Profile toCreateUserProfile(ProfileReq profileReq) {
        
        Profile profile = new Profile();
        profile.setUserId(profileReq.getUserId());
        profile.setProfileName(profileReq.getProfileName());
        profile.setProfileLevel(profileReq.getProfileLevel());
        profile.setCreatedOn(new Date());

        return profileRepository.save(profile);
    }

    
    @Override
    public Profile toUpdateUserProfile(Integer profileId, ProfileReq profileReq) {
        
        Optional<Profile> currentProfile = profileRepository.findById(profileId);
        if(!currentProfile.isPresent())
            return null;

        Profile profile = new Profile();

        profile.setProfileId(profileId);
        profile.setUserId(profileReq.getUserId());
        profile.setProfileName(profileReq.getProfileName());
        profile.setProfileLevel(profileReq.getProfileLevel());
        profile.setCreatedOn(currentProfile.get().getCreatedOn());
        profile.setCreatedOn(new Date());

        return profileRepository.save(profile);
    }

    @Override
    public Department toCreateUserDepartment(DepartmentReq departReq) {
        
        Department department = new Department();
        department.setUserId(departReq.getUserId());
        department.setDepartmentName(departReq.getDepartmentName());
        department.setCreatedOn(new Date());

        return departmentRepository.save(department);
    }

    @Override
    public Office toCreateUserOffice(OfficeReq officeReq) {
        
        Integer userId = officeReq.getUserId();
        Integer departId = officeReq.getDepartmentId();
        Integer profileId = officeReq.getProfileId();
        String name = officeReq.getOfficeName();
        String createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        Integer res= officeRepository.saveOffice(userId, departId, profileId, name, createDate);
        if (res!=1)
            return null;

        return officeRepository.fetchUserLastOfficeEntered(userId);
    }


    @Override
    public String setUserOffices(OfficeSet userOffices) {

        Integer userId = userOffices.getUserId();
        Integer[] offices = userOffices.getOffices();

        officeRepository.clearUserOffice(userId);
        
        for (Integer officeId : offices) {
            officeRepository.updateSetUserOffice(userId, officeId);
        }

        return "DONE";
    }








    

}

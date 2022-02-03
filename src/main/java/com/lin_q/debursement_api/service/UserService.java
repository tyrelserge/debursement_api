package com.lin_q.debursement_api.service;

import java.util.List;

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
import com.lin_q.debursement_api.model.RoleReq;
import com.lin_q.debursement_api.model.UserReq;

public interface UserService {
    
    public void saveActionRequest(String remoteAddr, String action, String requestStatus);
    
    public List<Role> getRoleList();
    public List<Profile> getProfileList();
    public List<Department> getDepartmentsList();
    public List<Office> getOfficesList();

    public Role toCreateRole(RoleReq roleReq);
    public Role toUpdateRole(Integer roleId, RoleReq roleData);
    public Profile toCreateUserProfile(ProfileReq profileReq);
    public Profile toUpdateUserProfile(Integer profileId, ProfileReq profileData);

    public Department toCreateUserDepartment(DepartmentReq departReq);
    public Office toCreateUserOffice(OfficeReq officeReq);
    
    public User getUserData(Integer userId);

    public User toCreateUser(UserReq userData);
    public String setUserOffices(OfficeSet offices);
    
    public GeneralSetting toUpdateGeneralSettings(GSettingsReq gSettingsReq);



}

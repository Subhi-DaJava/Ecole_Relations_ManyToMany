package com.weten.ecole.service;

import com.weten.ecole.model.Role;
import com.weten.ecole.model.User;

public interface UserService {

    User addNewUser(User newUser);
    Role addNewRole(Role newRole);

    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);

    void addRoleToUser(String userName, String roleName);

    User authenticate(String userName, String password);

}

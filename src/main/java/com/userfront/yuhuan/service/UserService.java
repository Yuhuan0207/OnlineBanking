package com.userfront.yuhuan.service;

import com.userfront.yuhuan.domain.User;
import com.userfront.yuhuan.domain.security.UserRole;

import java.util.Set;

public interface UserService {
    User findByUsername(String username);

    User findByEmail(String email);

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);

    void save(User user); //TODO: save or not?

    User createUser(User user, Set<UserRole> userRoles);

}

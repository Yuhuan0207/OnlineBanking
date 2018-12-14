package com.userfront.yuhuan.service;

import com.userfront.yuhuan.domain.User;

public interface UserService {
    User findByUsername(String username);

    User findByEmail(String email);

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);

    void createUser(User user);

}

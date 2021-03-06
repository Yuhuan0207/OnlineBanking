package com.userfront.yuhuan.service.UserServiceImpl;

import com.userfront.yuhuan.dao.RoleDao;
import com.userfront.yuhuan.dao.UserDao;
import com.userfront.yuhuan.domain.User;
import com.userfront.yuhuan.domain.security.UserRole;
import com.userfront.yuhuan.service.AccountService;
import com.userfront.yuhuan.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AccountService accountService;

    public void save(User user){
        userDao.save(user);
    } //TODO: why create a new saveUser method instead of using this method?

    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }

    public User findByEmail(String email){
        return userDao.findByEmail(email);
    }

    @Transactional
    public User createUser(User user, Set<UserRole> userRoles){
        User localUser = userDao.findByUsername(user.getUsername());

        if(localUser != null){
            LOG.info("User with username {} already exist. Nothing will be done.", user.getUsername());
        } else {
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);

            for(UserRole ur: userRoles){
                roleDao.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);

            user.setPrimaryAccount(accountService.createPrimaryAccount());
            user.setSavingAccount(accountService.createSavingAccount());

            localUser = userDao.save(user);
        }
        return localUser;
    }

    public boolean checkUserExists(String username, String email){
        if(checkUsernameExists(username) || checkEmailExists(email)){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUsernameExists(String username){
        if(null != findByUsername(username)){
            return true;
        }
        return false;
    }

    public boolean checkEmailExists(String email){
        if(null != findByEmail(email)){
            return true;
        }
        return false;
    }
}
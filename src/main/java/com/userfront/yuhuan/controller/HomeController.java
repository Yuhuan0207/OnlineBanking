package com.userfront.yuhuan.controller;

import com.userfront.yuhuan.dao.RoleDao;
import com.userfront.yuhuan.domain.security.UserRole;
import com.userfront.yuhuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import com.userfront.yuhuan.domain.User;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.*;

@Controller //will be register as bean
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleDao roleDao; //TODO: It's better not to define a roleDao in home controller. a seperate service is better

    @RequestMapping("/")
    public String home(){
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    //----------sign up part----------------
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model){
        User user = new User();
        model.addAttribute("user", user); //赋值到user variable
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user") User user, Model model){
        if(userService.checkUserExists(user.getUsername(), user.getEmail())){
            if(userService.checkEmailExists(user.getEmail())){
                model.addAttribute("emailExists", true);
            }
            if(userService.checkUsernameExists(user.getUsername())){
                model.addAttribute("emailExists", true);
            }
            return "signup";
        } else {
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, roleDao.findByName("USER")));

            userService.createUser(user, userRoles); //TODO: create a new method instead of edit the CRUD

            return "redirect:/";
        }
    }
}
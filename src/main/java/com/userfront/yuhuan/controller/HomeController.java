package com.userfront.yuhuan.controller;

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
    public void signupPost(@ModelAttribute("user") User user, Model model){
//        if(userService.checkUserExists()){
//            if(userService.checkEmailExists(user.getEmail())){
//                model.addAttribute("emailExists", true);
//            }
//            if(userService.checkUsernameExists(user.getUsername())){
//                model.addAttribute("emailExists", true);
//            }
//            return "signup";
//        } else {
//            Set<UserRole> userRoles = new HashSet<UserRole>();
//            userRoles.add(new UserRole(user, roleDao.findByName("USER")));
//            userService.createUser(user, userRoles);
//
//            return "redirect:/";
//        }
    }
}

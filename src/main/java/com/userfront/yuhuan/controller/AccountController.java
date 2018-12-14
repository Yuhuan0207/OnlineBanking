package com.userfront.yuhuan.controller;

import com.userfront.yuhuan.domain.PrimaryAccount;
import com.userfront.yuhuan.domain.SavingAccount;
import com.userfront.yuhuan.domain.User;
import com.userfront.yuhuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @RequestMapping("/primaryAccount")
    public String primaryAccount(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();

        model.addAttribute("primaryAccount", primaryAccount);

        return "primaryAccount";
    }

    @RequestMapping("/savingAccount")
    public String savingAccount(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        SavingAccount savingAccount = user.getSavingAccount();

        model.addAttribute("savingAccount", savingAccount);

        return "savingAccount";
    }
}

package com.userfront.yuhuan.controller;

import com.userfront.yuhuan.domain.PrimaryAccount;
import com.userfront.yuhuan.domain.SavingAccount;
import com.userfront.yuhuan.domain.User;
import com.userfront.yuhuan.service.TransactionService;
import com.userfront.yuhuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/betweenAccounts", method = RequestMethod.GET)
    public String betweenAccounts(Model model){
        model.addAttribute("transferFrom", "");
        model.addAttribute("transferTo", "");
        model.addAttribute("amount", "");

        return "betweenAccounts";
    }

    @RequestMapping(value = "/betweenAccounts", method = RequestMethod.POST)
    public String betweenAccountsPost(@ModelAttribute("transferFrom") String transferFrom,
                                      @ModelAttribute("transferTo") String transferTo,
                                      @ModelAttribute("amount") String amount,
                                      Principal principal
    ) throws Exception {
        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        SavingAccount savingAccount = user.getSavingAccount();
        transactionService.betweenAccountsTransfer(transferFrom, transferTo, amount, primaryAccount, savingAccount);

        return "redirect:/userFront";
    }
}

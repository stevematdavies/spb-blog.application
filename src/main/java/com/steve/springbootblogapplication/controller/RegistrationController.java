package com.steve.springbootblogapplication.controller;

import com.steve.springbootblogapplication.model.Account;
import com.steve.springbootblogapplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final AccountService accountService;

    @Autowired
    public RegistrationController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getRegistrationPage(Model model){
        model.addAttribute("account", new Account());
        return "register";
    }

    @PostMapping
    public String doRegister(@ModelAttribute Account account){
        accountService.save(account);
        return "redirect:/";
    }

}

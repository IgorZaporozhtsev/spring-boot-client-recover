package com.zeecoder.reboot.controller;

import com.zeecoder.reboot.model.Account;
import com.zeecoder.reboot.model.Role;
import com.zeecoder.reboot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(value = "/account")
public class AccountController {

    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public String getAll(Model model) {
        List<Account> accounts = service.getAll();
        model.addAttribute("accounts", accounts);
        return "account";
    }
}

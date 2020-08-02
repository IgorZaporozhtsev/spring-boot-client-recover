
package com.zeecoder.reboot.service;

import com.zeecoder.reboot.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {

    private final RestTemplate restTemplate;

    @Autowired
    public AccountServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Account> getAll() {
        String url = "http://localhost:8080/account";
        List<Account> accounts = restTemplate.getForObject(url, List.class);
        return accounts;
    }
}



package com.zeecoder.reboot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.zeecoder.reboot.model.Account;
import com.zeecoder.reboot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {

    private final String url = "http://localhost:8080/account";
    private final RestTemplate restTemplate;

    @Autowired
    public AccountServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = restTemplate.getForObject(url, List.class);
        return accounts;
    }

    @Override
    public void add(Account account, String roleStr) {
        Set<Role> roles = collectRolesToSet(account, roleStr);
        account.setRoles(roles);
        restTemplate.postForObject(url + "/add", account, Account.class);
    }

    @Override
    public Optional<Account> getOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Account account) {
        restTemplate.put(url + "/update", account, Account.class);
    }

    @Override
    public void delete(Long id) {
        restTemplate.delete(url + "/delete/{id}", id);
    }

    @Override
    public Account findByName(String nickname) {
        Account account = restTemplate.getForObject(url, Account.class);
        return account;
    }

    private Set<Role> collectRolesToSet(Account account, String roleStr){

        String[] roleStrings = roleStr.split("\\s*,\\s*");
        Set<Role> rolesSet = new HashSet<>();

        for (String s: roleStrings) {
            Role role = new Role();
            role.setRole(s);
            role.setAccount(account);
            rolesSet.add(role);
        }

        return rolesSet;
    }
}


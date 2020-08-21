
package com.zeecoder.reboot.service;

import com.zeecoder.reboot.model.Account;
import com.zeecoder.reboot.model.Role;
import com.zeecoder.reboot.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {

    private final String url = "http://localhost:8080/account";
    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository repository;

    @Autowired
    public AccountServiceImpl(
            @Lazy PasswordEncoder passwordEncoder,
            RestTemplate restTemplate,
            AccountRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.restTemplate = restTemplate;
        this.repository = repository;
    }


    @Override
    public List<Account> getAll() {
        List<Account> accounts = Arrays.asList(restTemplate.getForObject(url, Account[].class));
        return accounts;

    }

    @Override

    public void add(Account account, String roleStr) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        Set<Role> roles = collectRolesToSet(account, roleStr);
        account.setRoles(roles);
        restTemplate.postForObject(url + "/add", account, Account.class);
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
        return repository.findByNickname(nickname);
    }

    private Set<Role> collectRolesToSet(Account account, String roleStr){

        String[] roleStrings = roleStr.split("\\s*,\\s*");
        Set<Role> rolesSet = new HashSet<>();

        for (String s: roleStrings) {
            Role role = new Role();
            role.setRole(s);
            //role.setAccount(account);
            rolesSet.add(role);
        }

        return rolesSet;
    }
}


package com.zeecoder.reboot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zeecoder.reboot.model.Account;
import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> getAll();

    void add(Account account, String role) throws JsonProcessingException;

    Optional<Account> getOne(Long id);

    void update(Account account);

    void delete(Long id);

    Account findByName(String nickname);
}

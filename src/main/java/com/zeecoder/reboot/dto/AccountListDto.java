package com.zeecoder.reboot.dto;

import com.zeecoder.reboot.model.Account;
import lombok.Data;

import java.util.List;

@Data
public class AccountListDto {

    private List<Account> accountList;
}

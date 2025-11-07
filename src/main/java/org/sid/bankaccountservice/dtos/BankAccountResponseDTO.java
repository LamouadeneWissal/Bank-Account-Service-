package org.sid.bankaccountservice.dtos;

import org.sid.bankaccountservice.enums.AccountType;

import java.time.LocalDateTime;

public class BankAccountResponseDTO {
    private String id;
    private LocalDateTime createdAt;
    private Double balance;
    private String currency;
    private AccountType type;
}

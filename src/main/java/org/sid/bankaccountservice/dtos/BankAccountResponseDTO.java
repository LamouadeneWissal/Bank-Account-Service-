package org.sid.bankaccountservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.bankaccountservice.enums.AccountType;

import java.time.LocalDateTime;
@Data @NoArgsConstructor
@AllArgsConstructor @Builder
public class BankAccountResponseDTO {
    private String id;
    private LocalDateTime createdAt;
    private Double balance;
    private String currency;
    private AccountType type;
}

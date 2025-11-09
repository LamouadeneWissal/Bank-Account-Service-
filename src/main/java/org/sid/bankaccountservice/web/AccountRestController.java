package org.sid.bankaccountservice.web;

import org.sid.bankaccountservice.Service.AccountService;
import org.sid.bankaccountservice.Service.AccountServiceImpl;
import org.sid.bankaccountservice.dtos.BankAccountRequestDTO;
import org.sid.bankaccountservice.dtos.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api")
@RestController

public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;

    public AccountRestController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Account %s not found" , id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.addAccount(requestDTO);
    }
    @PutMapping("/bankAccount/{id}")
    public BankAccount update(@RequestBody BankAccount bankAccount , @PathVariable String id ){

        BankAccount account  = bankAccountRepository.findById(id).orElseThrow();

        if(bankAccount.getType()!=null ) account.setType(bankAccount.getType());
        if(bankAccount.getBalance()!=null )  account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCurrency()!=null ) account.setCurrency(bankAccount.getCurrency());
        if(bankAccount.getCreatedAt()!=null ) account.setCreatedAt(bankAccount.getCreatedAt());
        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/bankAccount/{id}")
    public void delete(@PathVariable String id) {
        bankAccountRepository.deleteById(id);

    }
}

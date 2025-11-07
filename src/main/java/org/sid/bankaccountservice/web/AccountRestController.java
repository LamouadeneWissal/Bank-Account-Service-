package org.sid.bankaccountservice.web;

import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController

public class AccountRestController {
    private BankAccountRepository bankAccountRepository;

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
    public BankAccount save(@RequestBody BankAccount bankAccount) {
        if(bankAccount.getId()==null)bankAccount.setId(UUID.randomUUID().toString());
        return bankAccountRepository.save(bankAccount);
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
}

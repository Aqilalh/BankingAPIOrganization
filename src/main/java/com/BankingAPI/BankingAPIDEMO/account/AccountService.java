package com.BankingAPI.BankingAPIDEMO.account;

import com.BankingAPI.BankingAPIDEMO.customer.Customer;
import com.BankingAPI.BankingAPIDEMO.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountByAccountId(Long accountId) {
        return accountRepository.findById(accountId);
    }

    public Iterable<Account> getAllAccountsByCustomerId(Long customerId) {
        return accountRepository.findAllByCustomerId(customerId);
    }

    public boolean customerCheck(Long accountId) {
        Customer customer = customerRepository.findById(accountId).orElse(null);
        return customer != null;
    }

    public boolean accountCheck(Long accountId) {
        Account account = getAccountByAccountId(accountId).orElse(null);
        return account != null;
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }
}


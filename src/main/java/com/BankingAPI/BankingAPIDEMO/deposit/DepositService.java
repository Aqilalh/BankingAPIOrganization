package com.BankingAPI.BankingAPIDEMO.deposit;

import com.BankingAPI.BankingAPIDEMO.account.Account;
import com.BankingAPI.BankingAPIDEMO.account.AccountRepository;
import com.BankingAPI.BankingAPIDEMO.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepositService {
    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    public Deposit createDeposit(Long accountId,Deposit deposit){
        Optional<Account> account = accountService.getAccountByAccountId(accountId);
        Double balance = account.get().getBalance();
        Double amount = deposit.getAmount();
        Double total = balance + amount;
        account.get().setBalance(total);
        return depositRepository.save(deposit);
    }
    public Iterable<Deposit> getAllDepositsByAccountId(Long accountId){
        return depositRepository.getDepositByAccountId(accountId);
    }
    public ResponseEntity<?> getDepositByDepositId(Long depositId){
        Deposit deposit = depositRepository.findById(depositId).orElse(null);
        return new ResponseEntity<>(deposit, HttpStatus.OK);
    }
    public void deleteDepositById(Long depositId){
        depositRepository.deleteById(depositId);
    }
    public void updateDeposit(Long depositId, Deposit deposit){
        Account account = accountService.getAccountByAccountId(deposit.getPayeeId()).orElse(null);
        Double oldAmount = depositRepository.findById(depositId).get().getAmount();
        Double balance = account.getBalance();
        Double oldBalance = balance - oldAmount;
        account.setBalance(oldBalance);
        Double newAmount = deposit.getAmount();
        Double total = oldBalance + newAmount;
        account.setBalance(total);
        depositRepository.save(deposit);
    }
    public boolean depositCheck(Long accountId){
        Deposit deposit = depositRepository.findById(accountId).orElse(null);
        return deposit != null;
    }
    public boolean accountCheck(Long accountId){
        Account account = accountRepository.findById(accountId).orElse(null);
        return account != null;
    }
}

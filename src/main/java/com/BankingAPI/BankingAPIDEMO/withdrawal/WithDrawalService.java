package com.BankingAPI.BankingAPIDEMO.withdrawal;



import com.BankingAPI.BankingAPIDEMO.account.Account;
import com.BankingAPI.BankingAPIDEMO.account.AccountRepository;
import com.BankingAPI.BankingAPIDEMO.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class WithDrawalService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private WithdrawalRepository withdrawalRepository;

    @Autowired
    private AccountService accountService;

    public Iterable<WithDrawal> getAllWithdrawalById(Long accountId) {
        return withdrawalRepository.getWithdrawalByAccountId(accountId);
    }
    public Optional<WithDrawal> getWithdrawalByWithdrawalId(Long withdrawalId) {

        return withdrawalRepository.findById(withdrawalId);
    }


    public WithDrawal createWithdrawal(WithDrawal withdrawal, Long accountId) {

        Optional<Account> account = accountService.getAccountByAccountId(accountId);
        Double accountBalance = account.get().getBalance();
        Double withdrawalAmount = withdrawal.getAmount();

        Double transaction = accountBalance - withdrawalAmount;
        account.get().setBalance(transaction);

        return withdrawalRepository.save(withdrawal);
    }
    public void updateWithdrawal(WithDrawal withDrawal, Long accountId) {

        Account account = accountService.getAccountByAccountId(withDrawal.getPayer_id()).orElse(null);

        Double oldWithdrawalAmount = withdrawalRepository.findById(accountId).get().getAmount();

        Double accountBalance = account.getBalance();

        Double oldBalance = accountBalance + oldWithdrawalAmount;
        account.setBalance(oldBalance);

        Double depositAmount = withDrawal.getAmount();

        Double transaction = oldBalance - depositAmount;
        account.setBalance(transaction);

        withdrawalRepository.save(withDrawal);
    }


    public void deleteWithdrawals( Long WithdrawalId) {
        withdrawalRepository.deleteById(WithdrawalId);
    }


    public boolean accountCheck(Long accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        return account != null;
    }

    public boolean withdrawalCheck(Long withdrawalId) {
        WithDrawal withDrawal = withdrawalRepository.findById(withdrawalId).orElse(null);
        return withDrawal != null;
    }
    public void deleteWithdrawal(Long withdrawalId) {
        withdrawalRepository.deleteById(withdrawalId);
    }



}


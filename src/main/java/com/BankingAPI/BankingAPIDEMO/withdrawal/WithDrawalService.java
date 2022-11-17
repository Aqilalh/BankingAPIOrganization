package com.BankingAPI.BankingAPIDEMO.withdrawal;



import com.BankingAPI.BankingAPIDEMO.account.Account;
import com.BankingAPI.BankingAPIDEMO.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class WithDrawalService {

    Logger withdrawalLog = LoggerFactory.getLogger(WithDrawalController.class);

    @Autowired
    private WithdrawalRepository withdrawalRepository;

    @Autowired
    private AccountService accountService;

    public Iterable<WithDrawal> getAllWithdrawalById(Long accountId) {
        return withdrawalRepository.getWithdrawalByAccountId(accountId);
    }


    public WithDrawal createWithdrawal(WithDrawal withdrawal, Long accountId) {

        withdrawalLog.info("===== CREATING WITHDRAWAL =====");
        Optional<Account> account = accountService.getAccountByAccountId(accountId);

        Double accountBalance = account.get().getBalance();
        Double withdrawalAmount = withdrawal.getAmount();

        Double transaction = accountBalance - withdrawalAmount;
        account.get().setBalance(transaction);

        return withdrawalRepository.save(withdrawal);
    }
    public void updateWithdrawal(WithDrawal withDrawal, Long accountId) {
        withdrawalRepository.save(withDrawal);
    }

    public void deleteWithdrawals( Long WithdrawalId) {
        withdrawalRepository.deleteById(WithdrawalId);
    }


}

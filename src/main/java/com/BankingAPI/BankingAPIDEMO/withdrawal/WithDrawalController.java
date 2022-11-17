package com.BankingAPI.BankingAPIDEMO.withdrawal;

import com.BankingAPI.BankingAPIDEMO.account.AccountRepository;
import com.BankingAPI.BankingAPIDEMO.bill.Bill;
import com.BankingAPI.BankingAPIDEMO.exceptions.CodeData;
import com.BankingAPI.BankingAPIDEMO.exceptions.CodeMessageError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public class WithDrawalController {

    @Autowired
    private WithDrawalService withDrawalService;

    @Autowired
    WithdrawalRepository withdrawalRepository;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> getAllWithdrawalsByAccountId(@PathVariable Long accountId) {

        Iterable<WithDrawal> withdrawals =  withDrawalService.getAllWithdrawalById(accountId);
        if(withdrawalRepository.getWithdrawalByAccountId(accountId).isEmpty()){
            CodeMessageError exception = new CodeMessageError("Withdrawal(s) not found");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }
        CodeData response = new CodeData(200, withdrawals);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> createWithdrawal(@PathVariable Long accountId, @RequestBody WithDrawal withdrawal) {

        try {
            if (!withDrawalService.(accountId)) {
                CodeMessageError exception = new CodeMessageError(404, "Error creating withdrawal: Account not found");
                return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
            } else if (withdrawal.getAmount() >= accountRepository.findById(accountId).get().getBalance()) {
                CodeMessageError exception = new CodeMessageError(404, "Error creating withdrawal: Over withdrawal");
                return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
            } else if (withdrawal.getAmount() <= 0) {
                CodeMessageError exception = new CodeMessageError(404, "Error creating withdrawal: Withdrawal amount must be greater than zero");
                return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
            } else {
                WithDrawal w1 = withDrawalService.createWithdrawal(withdrawal, accountId);
                CodeData response = new CodeData(201, "Created withdrawal and deducted it from the account");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } } catch (Exception e){
            CodeMessageError error = new CodeMessageError(404, "Error creating withdrawal");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/accounts/{accountId}/withdrawals")
    public void createWithdrawal(@RequestBody WithDrawal withDrawl, @PathVariable Long accountId) {
        withDrawalService.createWithdrawals(withDrawl, accountId);
    }

    @PutMapping("/withdrawal/{withdrawalId}")
    public void updateWithdrawal(@PathVariable Long withdrawalId, WithDrawal withDrawl) {
       withDrawalService.updateWithdrawal(withDrawl, withdrawalId);
    }

    @DeleteMapping("/withdrawal/{withdrawalId}")
    public void deleteWithdrawalById(@PathVariable Long id) {
        withDrawalService.deleteWithdrawals(id);
    }
}



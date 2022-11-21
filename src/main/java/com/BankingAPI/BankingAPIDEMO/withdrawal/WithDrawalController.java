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

    @PostMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> createWithdrawal(@PathVariable Long accountId, @RequestBody WithDrawal withdrawal) {

        try {
            if (!withDrawalService.accountCheck(accountId)) {
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

    @GetMapping("/accounts/{accountId}/withdrawals")public ResponseEntity<?> getWithdrawalById(@PathVariable Long withdrawalId){

        Optional<WithDrawal> withdrawal =  withDrawalService.getWithdrawalByWithdrawalId(withdrawalId);
        if(withdrawal.isEmpty()){
            CodeMessageError exception = new CodeMessageError("error fetching withdrawal with withdrawal id " + withdrawalId);
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }
        CodeData response = new CodeData(200, withdrawal);
        return new ResponseEntity<> (response, HttpStatus.OK);
    }


    @PutMapping("/withdrawal/{withdrawalId}")
    public ResponseEntity<?> updateWithdrawal(@PathVariable Long withdrawalId, @RequestBody WithDrawal withdrawal) {
        if (!withDrawalService.withdrawalCheck(withdrawalId)) {
            CodeMessageError exception = new CodeMessageError("Withdrawal ID does not exist");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

        withDrawalService.updateWithdrawal(withdrawal, withdrawalId);
        CodeMessageError response = new CodeMessageError(202, "Accepted withdrawal modification");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/withdrawal/{withdrawalId}")
        public ResponseEntity<?> deleteWithdrawal(@PathVariable Long withdrawalId){

            if(!withDrawalService.withdrawalCheck(withdrawalId)){
                CodeMessageError exception = new CodeMessageError("This id does not exist in withdrawals");
                return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
            }

            withDrawalService.deleteWithdrawal(withdrawalId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }




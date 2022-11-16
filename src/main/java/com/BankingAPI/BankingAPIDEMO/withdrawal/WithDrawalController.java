package com.BankingAPI.BankingAPIDEMO.withdrawal;

import com.BankingAPI.BankingAPIDEMO.bill.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public class WithDrawalController {

    @Autowired
    private WithDrawalService withDrawalService;

    @GetMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?>getAllWithdrawalsById(@PathVariable Long withdrawalId) {
        return withDrawalService.getAllWithdrawalById(withdrawalId);
    }

    @GetMapping("/accounts/{accountId}/withdrawals")
    public Iterable<WithDrawal> getAllWithdrawals(@RequestBody WithDrawal withDrawal, @PathVariable Long accountId) {
        return withDrawalService.findWithdrawalByAccountId( accountId,withDrawal);
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



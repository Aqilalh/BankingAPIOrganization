package com.BankingAPI.BankingAPIDEMO.deposit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepositController {
    @Autowired
    private DepositService depositService;

    @GetMapping("/accounts/{accountsId}/deposit")
    public Iterable<Deposit> findDepositById(@PathVariable Long accountsId){
        return depositService.findDepositByAccountId(accountsId);
    }


    @GetMapping("/deposit/depositId")
    public ResponseEntity<?> getDepositById(@PathVariable Long depositId) {
        return depositService.getDepositById(depositId);
    }
    //NeedTHePostMethod
    @PostMapping("/accounts/{accountsId}/deposit")
    public void createDeposit(@PathVariable  Long accountsId, @RequestBody Deposit deposits) {
        depositService.createDeposit(deposits,accountsId);
    }

    @PutMapping("/deposit/{depositId}")
    public void updateDeposit(@RequestBody Deposit deposit, @PathVariable Long depositId) {
        depositService.updateDeposit(deposit,depositId);
    }
    @DeleteMapping("/deposit/{depositId}")
    public void deleteDeposit( @PathVariable Long depositId) {
        depositService.deleteDeposit(depositId);

    }
}

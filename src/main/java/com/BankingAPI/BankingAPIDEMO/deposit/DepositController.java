package com.BankingAPI.BankingAPIDEMO.deposit;

import com.BankingAPI.BankingAPIDEMO.account.AccountService;
import com.BankingAPI.BankingAPIDEMO.exceptions.CodeData;
import com.BankingAPI.BankingAPIDEMO.exceptions.CodeMessageError;
import com.BankingAPI.BankingAPIDEMO.exceptions.CodeMessageSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DepositController {

    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private DepositService depositService;
    @Autowired
    private AccountService accountService;

    @PostMapping("/accounts/{customerId}/deposit")
    public ResponseEntity<?> createDeposit(@PathVariable Long accountId, @RequestBody Deposit deposit){
        depositService.createDeposit(accountId,deposit);
        if(!accountService.accountCheck(accountId)){
            CodeMessageError noAccount = new CodeMessageError(404,"Account doesn't exist");
            return new ResponseEntity<>(noAccount,HttpStatus.NOT_FOUND);
        }
        else if(deposit.getAmount() <= 0){
            CodeMessageError createError = new CodeMessageError("error creating deposit: deposit must be greater than 0");
            return new ResponseEntity<>(createError,HttpStatus.BAD_REQUEST);
        }else{
            Deposit successfulDeposit = depositService.createDeposit(accountId,deposit);
            CodeMessageSuccess successDepo = new CodeMessageSuccess(201,"Created the deposit and added it to your account", successfulDeposit);
            return new ResponseEntity<>(successDepo,HttpStatus.CREATED);
        }
    }
    @GetMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<?> getAllDepositsByAccountId(@PathVariable Long accountId){
        Iterable<Deposit> deposits = depositService.getAllDepositsByAccountId(accountId);
        if(deposits.iterator().hasNext()){
            CodeData response = new CodeData(200, deposits);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        if(!accountService.accountCheck(accountId)){
            CodeMessageError message = new CodeMessageError(404,"Account not found");
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
        CodeMessageError errorReturn = new CodeMessageError(404,"Deposit not found");
        return new ResponseEntity<>(errorReturn,HttpStatus.NOT_FOUND);
    }
    @GetMapping("/deposits/{depositId}")
    public ResponseEntity<?> getDepositById(@PathVariable Long depositId){
        ResponseEntity<?> deposit = depositService.getDepositByDepositId(depositId);
        if(!depositService.depositCheck(depositId)){
            CodeMessageError getDepositByIdError = new CodeMessageError(404, "Could not find deposit Id");
            return new ResponseEntity<>(getDepositByIdError,HttpStatus.NOT_FOUND);
        }else{
            CodeData successfullyFoundDepositById = new CodeData(200,deposit);
            return new ResponseEntity<>(successfullyFoundDepositById,HttpStatus.OK);
        }

    }
    @DeleteMapping("/deposits/{depositId}")
    public ResponseEntity<?> deleteDepositById(@PathVariable Long depositId){
        if (!depositService.depositCheck(depositId)){
            CodeMessageError deleteError = new CodeMessageError(404,"This id does not exist");
            return new ResponseEntity<>(deleteError,HttpStatus.NOT_FOUND);
        }else {
            depositService.deleteDepositById(depositId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @PutMapping("/deposits/{accountId}/deposit")
    public ResponseEntity<?> updateDeposit(@PathVariable Long depositId, @RequestBody Deposit deposit){
        if (!depositService.depositCheck(depositId)){
            CodeMessageError updateError = new CodeMessageError(404,"Deposit ID does not exist");
            return new ResponseEntity<>(updateError,HttpStatus.NOT_FOUND);
        }else{
            depositService.updateDeposit(depositId, deposit);
            CodeMessageError successfullyUpdated = new CodeMessageError(202,"Accepted deposit modification");
            return new ResponseEntity<>(successfullyUpdated,HttpStatus.OK);
        }
    }
}

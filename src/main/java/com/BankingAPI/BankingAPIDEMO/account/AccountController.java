package com.BankingAPI.BankingAPIDEMO.account;

import com.BankingAPI.BankingAPIDEMO.exceptions.CodeMessageSuccess;
import com.BankingAPI.BankingAPIDEMO.exceptions.CodeMessageError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/accounts")
    public ResponseEntity<?> getAllAccounts() {

        Iterable<Account> accounts = accountService.getAllAccounts();
        if (accounts.iterator().hasNext()) {
            CodeMessageSuccess response = new CodeMessageSuccess(200, "Success", accounts);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        CodeMessageError exception = new CodeMessageError(404,"Error fetching accounts");
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {

        Optional<Account> account = accountService.getAccountByAccountId(accountId);
        if (account.isEmpty()) {
            CodeMessageError exception = new CodeMessageError(404,"Error fetching account with id: " + accountId);
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }
        CodeMessageSuccess response = new CodeMessageSuccess(200, "Success", account);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers/{customerId}/accounts")
    public ResponseEntity<?> getAllAccountsByCustomer(@PathVariable Long customerId) {

        Iterable<Account> accounts = accountService.getAllAccountsByCustomer(customerId);
        if (accounts.iterator().hasNext()) {
            CodeMessageSuccess response = new CodeMessageSuccess(200, "Success", accounts);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        CodeMessageError exception = new CodeMessageError(404,"Error fetching accounts");
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customers/{customerId}/accounts")
    public ResponseEntity<?> createAccount(@PathVariable Long customerId, @RequestBody Account account) {

        try {
            if (accountService.customerCheck(customerId)) {
                CodeMessageSuccess response = new CodeMessageSuccess(201, "Account created", accountService.createAccount(account));
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            CodeMessageError exception = new CodeMessageError(404, "Error creating account: Customer not found");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            CodeMessageError error = new CodeMessageError(404, "Error creating account");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/accounts/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable Long accountId, @RequestBody Account account) {

        if(accountService.accountCheck(accountId)){
            accountService.updateAccount(account);
            CodeMessageError response = new CodeMessageError(201, "Customer account updated");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        CodeMessageError exception = new CodeMessageError(404, "Error: Account not found");
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId) {

        if (accountService.accountCheck(accountId)) {
            accountService.deleteAccount(accountId);
            CodeMessageError response = new CodeMessageError(202 ,"Account successfully deleted");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        CodeMessageError exception = new CodeMessageError(404,"Error: Account not found");
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}
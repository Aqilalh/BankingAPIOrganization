package com.BankingAPI.BankingAPIDEMO.bill;

import com.BankingAPI.BankingAPIDEMO.exceptions.CodeData;
import com.BankingAPI.BankingAPIDEMO.exceptions.CodeMessageError;
import com.BankingAPI.BankingAPIDEMO.exceptions.CodeMessageSuccess;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BillController {
    @Autowired
    BillService billService;

    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> createBill(@RequestBody Bill bill, @PathVariable Long accountId) {
        try {
            if (!billService.accountCheck(accountId)) {
                CodeMessageError exception = new CodeMessageError("Error creating bill: Account not found");
                return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
            } else {
                Bill b = billService.createBill(bill);
                CodeMessageSuccess response = new CodeMessageSuccess(201, "Created bill and added it to the account", b);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } } catch (Exception e){
            CodeMessageError error = new CodeMessageError(404, "Error creating bill");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> getAllBillsByAccount(@PathVariable Long accountId) {
        List<Bill> bills = billService.getAllBillsForASpecificAccount(accountId);
        if (bills.isEmpty()) {
            CodeMessageError exception = new CodeMessageError("ERROR: No bills under account id: " + accountId);
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }
        CodeData response = new CodeData(200, bills);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/bills/{billId}")
    public ResponseEntity<?> getBillById(@PathVariable Long billId) {
        ResponseEntity<?> bill = billService.getBill(billId);
        if (bill == null) {
            CodeMessageError exception = new CodeMessageError("ERROR: No bill found with the specified id: " + billId);
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

        CodeData response = new CodeData(200, bill);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}/bills")
    public ResponseEntity<?> getCustomerBillsByCustomerId(@PathVariable Long customerId){
        List<Bill> bills = billService.getAllBillForOneCustomer(customerId);
        if(bills.isEmpty()){
            CodeMessageError exception = new CodeMessageError("ERROR: A bill with this id does not exist");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

        CodeData response = new CodeData(200, bills);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("accounts/{accountId}/bills")
    public ResponseEntity<?> updateBill(@RequestBody Bill bill, @PathVariable Long billId){
        if(!billService.billCheck(billId)){
            CodeMessageError exception = new CodeMessageError("Bill ID does not exist");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

        billService.updateBill(bill);
        CodeData response = new CodeData(202, "Accepted bill modification");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }



    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable Long billId){
        if(billId == null){
            CodeMessageError exception = new CodeMessageError("ERROR: A bill with this id does not exist");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }
        billService.deleteBill(billId);
        return new ResponseEntity<>("Bill with ID: " + billId + " has been deleted.", HttpStatus.OK);
    }
}


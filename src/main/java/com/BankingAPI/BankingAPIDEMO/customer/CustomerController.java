package com.BankingAPI.BankingAPIDEMO.customer;

import com.BankingAPI.BankingAPIDEMO.exceptions.CodeMessageSuccess;
import com.BankingAPI.BankingAPIDEMO.exceptions.CodeMessageError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {

        try {
            CodeMessageSuccess success = new CodeMessageSuccess(200, "Customer account created", customerService.createCustomer(customer));
            return new ResponseEntity<>(success, HttpStatus.CREATED);
        } catch (Exception e){
            CodeMessageError error = new CodeMessageError(404, "Error creating customer");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> p = customerService.getAllCustomers();
        if(p.isEmpty()){
            CodeMessageError error = new CodeMessageError(404, "Error fetching customers");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        CodeMessageSuccess response = new CodeMessageSuccess(200, "Success", p);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/accounts/{account_id}/customer")
    public ResponseEntity<?> getCustomerByAccount(@PathVariable Long account_id) {
        Customer p = customerService.getCustomerByAccountId(account_id).orElse(null);
        if (p == null) {
            CodeMessageError error = new CodeMessageError(0, "error fetching customers accounts");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        CodeMessageSuccess response = new CodeMessageSuccess(200, "Success", p);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id){
        Customer p = customerService.getCustomerById(id).orElse(null);
        if(p == null){
            CodeMessageError error = new CodeMessageError(404, "error fetching customer");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        CodeMessageSuccess response = new CodeMessageSuccess(200, "Success", p);
        return new ResponseEntity<> (response, HttpStatus.OK);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable Long id){
        if(!customerService.customerCheck(id)){
            CodeMessageError exception = new CodeMessageError("Customer ID does not exist");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }
        customerService.updateCustomer(customer);
        CodeMessageError response = new CodeMessageError(202, "Accepted customer modification");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}

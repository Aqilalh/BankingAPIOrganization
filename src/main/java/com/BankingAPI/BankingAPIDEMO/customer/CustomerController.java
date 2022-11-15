package com.BankingAPI.BankingAPIDEMO.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @PostMapping("/customer")
    public void createCustomer( @RequestBody Customer customer) {
        customerService.createCustomer(customer);
    }

    @GetMapping("/customers")
    public ResponseEntity<Iterable<Customer>> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?>  getCustomerId(@PathVariable Long customerId) {
        return customerService.getCustomerById(customerId);
    }
    @PutMapping("/customer/{Id}")
    public void updateCustomer(@RequestBody Customer customer, @PathVariable (value = "Id") Long Id) {
        customerService.updateCustomer(customer,Id);
    }


    @GetMapping("/accounts/{accountId}/customer")
    public ResponseEntity<?> getCustomerbyAccountId(@PathVariable Long accountId){
        return customerService.getCustomerByAccountId(accountId);
    }

}

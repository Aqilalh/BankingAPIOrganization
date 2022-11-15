package com.BankingAPI.BankingAPIDEMO.customer;

import com.BankingAPI.BankingAPIDEMO.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public ResponseEntity<Iterable<Customer>> getAllCustomers() {
        Iterable<Customer> allCustomers = customerRepository.findAll();
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> getCustomerById(Long customerId) {
        Optional<Customer> c = customerRepository.findById(customerId);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    public void updateCustomer(Customer customer, Long Id) {
        customerRepository.save(customer);
    }

    public ResponseEntity<?> getCustomerByAccountId (Long accountId){
        Optional<Customer> c = customerRepository.findById(accountId);
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
}

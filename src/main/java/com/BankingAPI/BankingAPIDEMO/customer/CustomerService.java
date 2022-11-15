package com.BankingAPI.BankingAPIDEMO.customer;

import com.BankingAPI.BankingAPIDEMO.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    AccountService accountService;

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        List<Customer> listOfCustomers = new ArrayList<>();
        customerRepository.findAll().forEach(listOfCustomers::add);
        return listOfCustomers;
    }

    public Optional<Customer> getCustomerByAccountId(Long account_id) {
        Long customerId = accountService.getAccountByAccountId(account_id).get().getCustomerId();
        return customerRepository.findById(customerId);
    }

    public Optional<Customer> getCustomerByCustomerId(Long id) {
        return customerRepository.findById(id);
    }

    public boolean customerCheck(Long customerId){
        Customer customer = customerRepository.findById(customerId).orElse(null);
        return customer != null;
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

//    public void deleteCustomer(Long id) {
//        customerRepository.deleteById(id);
//    }
}

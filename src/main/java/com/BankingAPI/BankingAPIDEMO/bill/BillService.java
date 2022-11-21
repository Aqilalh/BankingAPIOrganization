package com.BankingAPI.BankingAPIDEMO.bill;

import com.BankingAPI.BankingAPIDEMO.account.Account;
import com.BankingAPI.BankingAPIDEMO.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    AccountRepository accountRepository;


    public List<Bill> getAllBillsForASpecificAccount(Long id) {
        return billRepository.getAllBillsForASpecificAccount(id);
    }
    public ResponseEntity<?> getBill(Long billId) {
        Optional<Bill> p = billRepository.findById(billId);
        return new ResponseEntity<> (p, HttpStatus.OK);
    }

    public List<Bill> getAllBillForOneCustomer(Long customerId) {
        List<Long> accountId = billRepository.getAccountIdThatMatchesCustomerId(customerId); //Getting the account that is assigned to a customer's id
        return billRepository.getAllBillsByAccountId(accountId);

    }

    public Bill createBill(Bill bill) {
        return billRepository.save(bill);
    }

    public void updateBill(Bill bill) {
        billRepository.save(bill);
    }
    public void deleteBill(Long billId) {
        billRepository.deleteById(billId);
    }

    public boolean accountCheck(Long accountId){

        Account account = accountRepository.findById(accountId).orElse(null);
        return account != null;
    }

    public boolean billCheck(Long billId){

        Bill bill = billRepository.findById(billId).orElse(null);
        return bill != null;
    }

}

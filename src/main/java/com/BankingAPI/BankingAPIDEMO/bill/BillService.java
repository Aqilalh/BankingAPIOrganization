package com.BankingAPI.BankingAPIDEMO.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public Optional<Bill> getBillById(Long id){
        return billRepository.findById(id);
    }

    public ResponseEntity<Iterable<Bill>> getAllBills(Bill bills, Long id){
        return new ResponseEntity<>(billRepository.findAll(), HttpStatus.OK);
    }

    public Bill createBill(Bill bill, Long id){
        return billRepository.save(bill);
    }
    public void updateBill(Long id, Bill bill){
        billRepository.save(bill);
    }

    public void deleteBillById(Long id){
        billRepository.deleteById(id);
    }
}

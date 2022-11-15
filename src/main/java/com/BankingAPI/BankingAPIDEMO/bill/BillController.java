package com.BankingAPI.BankingAPIDEMO.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/bills/{billId}")
    public Optional<Bill> getBillById(@PathVariable Long billId) {
        return billService.getBillById(billId);
    }

    @GetMapping("/accounts/{accountId}/bills")
    public ResponseEntity<Iterable<Bill>> getAllBillsByAccountId(@RequestBody Bill bills, @PathVariable Long accountId) {
        return billService.getAllBills(bills, accountId);
    }

    @GetMapping("customers/{customerId}/bills")
    public ResponseEntity<Iterable<Bill>> getAllBillsByCustomerId(@RequestBody Bill bills, @PathVariable Long customerId) {
        return billService.getAllBills(bills, customerId);
    }

    @PostMapping("/accounts/{accountId}/bills")
    public Bill createBill(@RequestBody Bill bill, @PathVariable Long accountId) {
        return billService.createBill(bill, accountId);
    }

    @PutMapping("/bills/{billId}")
    public void updateBill(@PathVariable Long billId, Bill bill) {
        billService.updateBill(billId, bill);
    }

    @DeleteMapping("/bills/{billId}")
    public void deleteBillById(@PathVariable Long id) {
        billService.deleteBillById(id);
    }
}


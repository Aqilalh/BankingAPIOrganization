package com.BankingAPI.BankingAPIDEMO.deposit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepositService {
    @Autowired
    private DepositRepository depositRepository;



    public ResponseEntity<?> getDepositById(Long depositId) {
        Optional<Deposit> d = depositRepository.findById(depositId);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }
    public Iterable<Deposit> findDepositByAccountId(Long accountId){
        return depositRepository.findDepositByAccountId(accountId);
    }
    public void createDeposit(Deposit deposit, Long accountsId) {
        depositRepository.save(deposit);
    }
    public void updateDeposit(Deposit deposit, Long depositId) {
        depositRepository.save(deposit);
    }

    public void deleteDeposit( Long depositId) {
        depositRepository.deleteById(depositId);
    }
}

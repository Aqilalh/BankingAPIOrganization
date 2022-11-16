package com.BankingAPI.BankingAPIDEMO.withdrawal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WithDrawalService {

    @Autowired
    private WithdrawalRepository withdrawalRepository;

    public ResponseEntity<?> getAllWithdrawalById(Long Id) {
        Optional<WithDrawal> w = withdrawalRepository.findById(Id);
        return new ResponseEntity<>(w, HttpStatus.OK);
    }
    public Iterable<WithDrawal> findWithdrawalByAccountId(Long accountId, WithDrawal withDrawal){
        return withdrawalRepository.findwithdrawalByAccountId(accountId);
    }
    public void createWithdrawals(WithDrawal withdrawal, Long accountsId) {
     withdrawalRepository.save(withdrawal);
    }
    public void updateWithdrawal(WithDrawal withDrawal, Long accountId) {
        withdrawalRepository.save(withDrawal);
    }

    public void deleteWithdrawals( Long WithdrawalId) {
        withdrawalRepository.deleteById(WithdrawalId);
    }
}

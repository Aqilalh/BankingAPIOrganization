package com.BankingAPI.BankingAPIDEMO.withdrawal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawalRepository extends CrudRepository <WithDrawal,Long> {
    @Query(value = "SELECT * FROM Withdrawal WHERE payer_id = ?1", nativeQuery = true)
    List<WithDrawal> getWithdrawalByAccountId(Long accountId);
}

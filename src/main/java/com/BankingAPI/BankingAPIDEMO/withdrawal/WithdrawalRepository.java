package com.BankingAPI.BankingAPIDEMO.withdrawal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawalRepository extends CrudRepository <WithDrawal,Long> {
    Iterable<WithDrawal> findwithdrawalByAccountId(Long accountId);
}

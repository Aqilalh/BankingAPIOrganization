package com.BankingAPI.BankingAPIDEMO.deposit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends CrudRepository<Deposit,Long> {
    Iterable<Deposit> findDepositByAccountId(Long accountId);
}

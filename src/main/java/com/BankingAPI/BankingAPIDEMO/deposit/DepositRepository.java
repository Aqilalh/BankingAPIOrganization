package com.BankingAPI.BankingAPIDEMO.deposit;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends CrudRepository<Deposit, Long> {
    @Query(value = "SELECT * FROM deposit WHERE payee_id = ?1", nativeQuery = true)
    Iterable<Deposit> getDepositByAccountId(long accountId);
}


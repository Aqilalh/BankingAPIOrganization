package com.BankingAPI.BankingAPIDEMO.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Iterable<Account> findAllByCustomerId(Long customerId);
}

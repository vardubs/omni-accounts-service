package com.varundublish.omnirio.accountsservice.repository;

import com.varundublish.omnirio.accountsservice.model.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {
    Optional<List<Account>> findByAccountId(String accountId);

    Optional<List<Account>> findByCustomerId(String customerId);
}

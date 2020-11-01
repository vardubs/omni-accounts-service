package com.varundublish.omnirio.accountsservice.repository;

import com.varundublish.omnirio.accountsservice.model.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {
}

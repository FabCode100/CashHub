package com.cashub.cashhubbackend.cashub.repository;

import com.cashub.cashhubbackend.cashub.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // Find account by account number (assuming number is unique)
    Optional<Account> findByNumber(String number);

    // Find all accounts for a specific user
    List<Account> findAllByCustomerId(Long customer);
}

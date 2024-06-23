package com.cashub.cashhubbackend.cashub.repository;

import com.cashub.cashhubbackend.cashub.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Find all transactions for a specific account
    List<Transaction> findByAccountId(Long accountId);

}

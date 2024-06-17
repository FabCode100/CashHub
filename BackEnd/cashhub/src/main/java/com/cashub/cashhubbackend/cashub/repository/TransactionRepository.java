package com.cashub.cashhubbackend.cashub.repository;

import com.cashub.cashhubbackend.cashub.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Find all transactions for a specific account
    List<Transaction> findByAccountId(Long accountId);

    // Find transactions by date range (optional)
    @Query("SELECT t FROM Transaction t WHERE t.date BETWEEN ?1 AND ?2")
    List<Transaction> findByDateBetween(Date startDate, Date endDate);
}

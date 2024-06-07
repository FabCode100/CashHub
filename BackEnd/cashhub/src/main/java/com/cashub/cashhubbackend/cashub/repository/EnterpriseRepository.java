package com.cashub.cashhubbackend.cashub.repository;

import com.cashub.cashhubbackend.cashub.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends JpaRepository<Account, Long> {
    // Métodos adicionais específicos podem ser adicionados aqui, se necessário
}
package com.cashub.cashhubbackend.cashub.repository;

import com.cashub.cashhubbackend.cashub.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
    // Métodos adicionais específicos podem ser adicionados aqui, se necessário
}

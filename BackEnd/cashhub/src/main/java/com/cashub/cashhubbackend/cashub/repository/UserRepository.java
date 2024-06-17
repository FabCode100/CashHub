package com.cashub.cashhubbackend.cashub.repository;

import com.cashub.cashhubbackend.cashub.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Métodos adicionais específicos podem ser adicionados aqui, se necessário
}

package com.cashub.cashhubbackend.cashub.domain.category;
import com.cashub.cashhubbackend.cashub.domain.account.Account;
import com.cashub.cashhubbackend.cashub.domain.transaction.Transaction;
import com.cashub.cashhubbackend.cashub.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "categories") // Especifica o nome da tabela no banco de dados
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @OneToMany(mappedBy = "category")
    private List<Transaction> transactions;
}

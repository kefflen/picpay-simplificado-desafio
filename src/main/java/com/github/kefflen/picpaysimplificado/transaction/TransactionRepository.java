package com.github.kefflen.picpaysimplificado.transaction;

import org.springframework.data.repository.ListCrudRepository;


public interface TransactionRepository extends ListCrudRepository<Transaction, Long> {
    
}

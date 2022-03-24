package com.mindhub.homebanking.repositories;


import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Invest;
import com.mindhub.homebanking.models.RiskType;
import com.mindhub.homebanking.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

   // public Transaction findTransactionByAccountNumber(Account account, String number);
   List<Transaction> findAllByAccount(Account account);

}

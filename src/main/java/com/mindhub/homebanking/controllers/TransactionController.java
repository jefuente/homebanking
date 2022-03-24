package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.TransactionDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @RequestMapping("/transactions")
    public List<TransactionDTO> getTransactions() {
        return transactionRepository.findAll().stream().map(transaction -> new TransactionDTO(transaction)).collect(toList());
    }

    @RequestMapping("/transactions/{id}")
    public TransactionDTO getTransaction(@PathVariable Long id) {
        return transactionRepository.findById(id).map(TransactionDTO::new).orElse(null);

    }

    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<Object> createTransactions(@RequestParam String fromAccountNumber,
                                                     @RequestParam String toAccountNumber,
                                                     @RequestParam double amount,
                                                     @RequestParam String description,
                                                     Authentication authentication) {

        Client client = this.clientRepository.findByEmail(authentication.getName());

        //Verificar si la cuenta esta vacioa o la descripcion esta vacia
        if (description==null || amount ==0) {
            return new ResponseEntity<>("amount is empty or description is empty ", HttpStatus.FORBIDDEN);
        }

        // verificar si las cuentas estan vacias
        if (fromAccountNumber.isEmpty() || toAccountNumber.isEmpty() || amount ==0) { //amount <= 0 || fromAccountNumber.isEmpty() || toAccountNumber.isEmpty()
            return new ResponseEntity<>("Account is empty ", HttpStatus.FORBIDDEN);
        }

        //verificar si existe cuenta de origen
        if (accountRepository.findAccountByNumber(fromAccountNumber).getNumber()==null) {

            return new ResponseEntity<>("The origin account does not exist", HttpStatus.FORBIDDEN);
        }

        //verificar si existe cuenta de destino
        if (accountRepository.findAccountByNumber(toAccountNumber)==null) {

            return new ResponseEntity<>("The destination account does not exist", HttpStatus.FORBIDDEN);
        }

        //Buscar la cuenta de origen por el numero de cuentas
        Account accountOrigin = accountRepository.findAccountByNumber(fromAccountNumber);
        //Buscar cuenta de destino por el numero de cuentas
        Account accountDestiny = accountRepository.findAccountByNumber(toAccountNumber);

        //Verificar si el cliente original es el distinto del cliente autenticado
        if (accountOrigin.getClient() != client) {

            return new ResponseEntity<>("The original account does not\n" +
                    "belongs to the authenticated client", HttpStatus.FORBIDDEN);
        }

        //verificar si la cuenta de origen tiene suficiente dinero
        if (accountOrigin.getBalance() < amount) {

            return new ResponseEntity<>("\n" +
                    "The client has no funds", HttpStatus.FORBIDDEN);
        }

        //verificar si las dos cuentas son iguales
        if (accountOrigin.equals(accountDestiny)) {

            return new ResponseEntity<>("The account of origin and destination are the same ", HttpStatus.FORBIDDEN);
        }

        //Asignar transacciones entre las cuentas

        double trans1 = accountOrigin.getBalance() - amount;
        double trans2 = accountDestiny.getBalance() + amount;

        //Crear las nuevas transaccioens
        transactionRepository.save(new Transaction(TransactionType.DEBIT, amount*-1,
                "Client: " + client.getFirstName() + " " + client.getLastName() +" - " + "Account Destiny: "+accountDestiny.getNumber() +
                        " - " + " Description :" + description, LocalDateTime.now(), accountOrigin));

        transactionRepository.save(new Transaction(TransactionType.CREDIT, amount,
                "Client: " + client.getFirstName() + " " + client.getLastName() + " - " + "Account Destiny: "+accountOrigin.getNumber() +
                        " - " + "Description :" + description, LocalDateTime.now(), accountDestiny));

        //Modificar el balance de las cuentas
        accountOrigin.setBalance(trans1);
        accountDestiny.setBalance(trans2);

        //Guardar la modificacio hecha
        accountRepository.save(accountOrigin);
        accountRepository.save(accountDestiny);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}



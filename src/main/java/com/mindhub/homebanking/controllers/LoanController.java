package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.LoanApplicationDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ClientLoanRepository clientLoanRepository;

    @GetMapping("/loans")
    public List<LoanDTO> getLoans() {
        return loanRepository.findAll().stream().map(LoanDTO::new).collect(toList());
    }

    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object> requestLoan(@RequestBody LoanApplicationDTO loanApplicationDTO,      //@RequestParam double amount, @RequestParam int payment,@RequestParam String accountDestiny, @RequestParam Long LoanTypeId,
                                             Authentication authentication){

       // ClientLoan clientLoan =  clientLoanRepository.findClientLoanById(loanApplicationDTO.getLoanId());

        //Verificar si la cuenta esta vacioa o la descripcion esta vacia
        if (this.clientRepository.findByEmail(authentication.getName())==null || loanApplicationDTO==null|| loanApplicationDTO.getAmount() ==0 || loanApplicationDTO.getPayments() == 0 || loanApplicationDTO.getLoanId()==0) {
            return new ResponseEntity<>("Amount is empty or description is empty ", HttpStatus.FORBIDDEN);
        }
        //Buscamos al cliente asociado al prestamo
        Client client = this.clientRepository.findByEmail(authentication.getName());

        //Verificar si el prestamo existe
        if(loanRepository.findLoanById(loanApplicationDTO.getLoanId())==null){
            return new ResponseEntity<>("This loan dont exist", HttpStatus.FORBIDDEN);

        }
        //Buscamos el prestamo asosiado al prestamo solicitado
        Loan loan = loanRepository.findLoanById(loanApplicationDTO.getLoanId());

        //verificar que el monto de prestamo no exceda el monto maximo de prestamo
        if (loanApplicationDTO.getAmount()> loan.getMaxAmount())
        {

            return new ResponseEntity<>("Exceed amount", HttpStatus.FORBIDDEN);
        }

        //Verificar si contienen el payment en payments
        if(!loan.getPayments().contains(loanApplicationDTO.getPayments())){
            return new ResponseEntity<>("Dont exist payment in payments", HttpStatus.FORBIDDEN);
        }

        //verificar si existe cuenta de destino

        if (accountRepository.findAccountByNumber(loanApplicationDTO.getToAccountNumber())==null) {

            return new ResponseEntity<>("The destination account does not exist", HttpStatus.FORBIDDEN);
        }

        Account accountDest = accountRepository.findAccountByNumber(loanApplicationDTO.getToAccountNumber());

        //Verificar si el cliente original es el distinto del cliente autenticado
        if (accountDest.getClient() != client) {

            return new ResponseEntity<>("The original account does not\n" +
                    "belongs to the authenticated client", HttpStatus.FORBIDDEN);
        }

        //Calcular prestamo mas el 20 %
        double loanCurrent = loanApplicationDTO.getAmount() + (loanApplicationDTO.getAmount()*0.2);

        //Crear transaccion con la descripcion loan approved
        transactionRepository.save(new Transaction(TransactionType.CREDIT, loanCurrent,
                "Prestamo: "+ loan.getName()+ "loan approved", LocalDateTime.now(), accountDest));

        //Actualizar el balance mas el nuevo prestamo
        accountDest.setBalance(accountDest.getBalance()+loanApplicationDTO.getAmount());
        //Guardar la cuenta en la db
        accountRepository.save(accountDest);

        //Prestamo
        ClientLoan clientLoan1 = new ClientLoan(client,loan,loanApplicationDTO.getPayments(), loanCurrent);
        clientLoanRepository.save(clientLoan1);

        //retornar un ok si la transaccion fue exitosa
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.dtos.*;
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

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class InvestController {

    @Autowired
    private InvestRepository investRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ClientInvestRepository clientInvestRepository;


    @GetMapping("/invests")
    public List<InvestDTO> getInvests() {
        return investRepository.findAll().stream().map(InvestDTO::new).collect(toList());
    }

    @RequestMapping("/invests/{risk}")
    public List<InvestDTO> getAccount(@PathVariable RiskType risk) {
        return investRepository.findByRisk(risk).stream().map(InvestDTO::new).collect(toList());
    }

    @Transactional
    @PostMapping("/invests")
    public ResponseEntity<Object> requestInvest(@RequestBody InvestApplicationDTO investApplicationDTO,
                                                Authentication authentication){

        //Verificar si faltan datos en el ingreso
        if (this.clientRepository.findByEmail(authentication.getName())==null || investApplicationDTO==null|| investApplicationDTO.getAmount() ==0 || investApplicationDTO.getPlazos() == 0 || investApplicationDTO.getInvestId()==0) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        //Buscamos al cliente asociado a la inversion
        Client client = this.clientRepository.findByEmail(authentication.getName());

        //Verificar si el producto de inversión existe
        if(investRepository.findInvestById(investApplicationDTO.getInvestId())==null){
            return new ResponseEntity<>("This invest dont exist", HttpStatus.FORBIDDEN);

        }
        //Buscamos la inversion asociada a la inversión solicitada
        Invest invest = investRepository.findInvestById(investApplicationDTO.getInvestId());

        Account accountOrig = accountRepository.findAccountByNumber(investApplicationDTO.getFromAccountNumber());

        //verificar que el monto de la inversion no exceda el monto disponible de la cuenta de origen
        if (investApplicationDTO.getAmount()> accountOrig.getBalance()){
            return new ResponseEntity<>("Exceed amount", HttpStatus.FORBIDDEN);
        }

        //Verificar si contienen el plazo en plazos
        if(!invest.getPlazos().contains(investApplicationDTO.getPlazos())){
            return new ResponseEntity<>("Dont exist plazo in plazos", HttpStatus.FORBIDDEN);
        }

        //verificar si existe cuenta de origen

        if (accountOrig==null) {

            return new ResponseEntity<>("The origin account does not exist", HttpStatus.FORBIDDEN);
        }

        //Verificar si el cliente original es distinto del cliente autenticado
        if (accountOrig.getClient() != client) {

            return new ResponseEntity<>("The original account does not\n" +
                    "belongs to the authenticated client", HttpStatus.FORBIDDEN);
        }

        // Monto inversión
        double investMount= investApplicationDTO.getAmount();

        //Crear transaccion con la descripcion invest approved
        transactionRepository.save(new Transaction(TransactionType.DEBIT, investMount*-1,
                invest.getName() +" " + "Invest approved", LocalDateTime.now(), accountOrig));

        //Actualizar el balance menos la nueva inversion
        accountOrig.setBalance(accountOrig.getBalance()-investMount);
        //Guardar la cuenta en la db
        accountRepository.save(accountOrig);

        //Inversion
        ClientInvest clientInvest1 = new ClientInvest(investMount,investApplicationDTO.getPlazos(), client, invest,false);
        clientInvestRepository.save(clientInvest1);

        //retornar un ok si la transaccion fue exitosa
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

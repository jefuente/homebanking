package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CardRepository cardRepository;


    @RequestMapping("/clients")
    public List<ClientDTO> getClients() {
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(toList());
    }

    @RequestMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return clientRepository.findById(id).map(ClientDTO::new).orElse(null);

    }

    @RequestMapping("/clients/current")
    public ClientDTO getClient(Authentication authentication) {
        Client client = this.clientRepository.findByEmail(authentication.getName());
        return new ClientDTO(client);

    }

    @PostMapping("/clients")
    public ResponseEntity<Object> createClient(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password) {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (this.clientRepository.findByEmail(email) != null) {

                return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

            Client client = this.clientRepository.save(new Client(firstName, lastName, email, this.passwordEncoder.encode(password),PerfilInversionType.CONSERVADOR));
            String accountNumber = "VIN"+(int)((Math.random() * (10000000-1))+1);
            accountRepository.save(new Account(accountNumber, LocalDateTime.now(), 0 ,client));
            return new ResponseEntity<>(HttpStatus.CREATED);

        }

    @PostMapping("/clients/current/accounts")
    public ResponseEntity<Object> createAccount(Authentication authentication) {

        Client client = this.clientRepository.findByEmail(authentication.getName());

        if(client.getAccounts().size()>3){

            return new ResponseEntity<>("Too many accounts, max accounts 3", HttpStatus.FORBIDDEN);
        }else {

            String accountNumber = "VIN"+(int)((Math.random() * (10000000-1))+1);
            accountRepository.save(new Account(accountNumber, LocalDateTime.now(), 0 ,client));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(@RequestParam CardType cardType, @RequestParam CardColor cardColor,Authentication authentication){

        Client client = this.clientRepository.findByEmail(authentication.getName());

        int numCards= cardRepository.countByClientAndType(client,cardType);

        if(numCards>=3) {

            return new ResponseEntity<>("Too many accounts, max accounts 6, 3 debit or 3 credit", HttpStatus.FORBIDDEN);
        }
            cardRepository.save(new Card(client.getFirstName()+" "+client.getLastName(),
                                    cardType,cardColor,(int)(Math.random()*(10000-1))+"-"+(int)(Math.random()*(10000-1))
                                    +"-"+(int)(Math.random()*(10000-1))+"-"+(int)(Math.random()*(10000-1)),(int)(Math.random()*(1000-1)),LocalDateTime.now(),
                                    LocalDateTime.now().plusYears(5),client));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping("/clients/current/accounts")
    public Set<Account> getClientTransaction(Authentication authentication) {
        return this.clientRepository.findByEmail(authentication.getName()).getAccounts();
  }
}
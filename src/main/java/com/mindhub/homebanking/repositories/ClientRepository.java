package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ClientInvest;
import com.mindhub.homebanking.models.Invest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;


@RepositoryRestResource()
public interface ClientRepository extends JpaRepository<Client, Long> {

   //   List<Client> findByLastName(String lastName);
        public Client findByEmail(String email);
   //   public List<Client> findClientByAccounts(Account account);




}

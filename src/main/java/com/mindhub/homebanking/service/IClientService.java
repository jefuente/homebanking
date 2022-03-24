package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;

import com.mindhub.homebanking.models.NewLocationToken;
import com.mindhub.homebanking.models.PasswordResetToken;
import com.mindhub.homebanking.models.VerificationToken;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

public interface IClientService {

    Client registerNewClientAccount(ClientDTO clientDTO);

    Client getClient(String verificationToken);

    void saveRegisteredClient(Client client);

    void deleteClient(Client client);

    //void createPasswordResetTokenForClient(Client client, String token);

   // PasswordResetToken getPasswordResetToken(String token);

   // Optional<Client> getClientByPasswordResetToken(String token);

  //  Optional<Client> getClientByID(long id);

   // void changeClientPassword(Client client, String password);

    //boolean checkIfValidOldPassword(Client client, String password);

   // String validateVerificationToken(String token);

    //String generateQRUrl(Client client) throws UnsupportedEncodingException;

  //  Client updateClient2FA(boolean use2FA);

  //  List<String> getClientsFromSessionRegistry();

  //  NewLocationToken isNewLoginLocation(String username, String ip);

   // String isValidNewLocationToken(String token);

  //  void addClientLocation(Client client, String ip);
}

package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.error.UserAlreadyExistException;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.PasswordResetToken;
import com.mindhub.homebanking.models.VerificationToken;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.PasswordResetTokenRepository;
import com.mindhub.homebanking.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;

    @Autowired
    private Environment env;

    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";

    public static String QR_PREFIX = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";
    public static String APP_NAME = "SpringRegistration";

    // API

    @Override
    public Client registerNewClientAccount(final ClientDTO clientDTO) {
        if (emailExists(clientDTO.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + clientDTO.getEmail());
        }
        final Client client = new Client();

        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
        client.setEmail(clientDTO.getEmail());

        return clientRepository.save(client);
    }

    @Override
    public Client getClient(final String verificationToken) {
        final VerificationToken token = tokenRepository.findByToken(verificationToken);
        if (token != null) {
            return token.getClient();
        }
        return null;
    }

    private boolean emailExists(final String email) {
        return clientRepository.findByEmail(email) != null;
    }


    @Override
    public void saveRegisteredClient(final Client client) {
        clientRepository.save(client);
    }

    @Override
    public void deleteClient(final Client client) {
        final VerificationToken verificationToken = tokenRepository.findByClient(client);

        if (verificationToken != null) {
            tokenRepository.delete(verificationToken);
        }

        final PasswordResetToken passwordToken = passwordTokenRepository.findByClient(client);

        if (passwordToken != null) {
            passwordTokenRepository.delete(passwordToken);
        }

        clientRepository.delete(client);
    }















}

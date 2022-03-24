package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.PerfilInversionType;
import com.mindhub.homebanking.util.validation.ValidEmail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {

    //atributos
    private Long id;
    @NotNull
    @Size(min = 1, message = "{Size.userDto.firstName}")
    private String firstName;
    @NotNull
    @Size(min = 1, message = "{Size.userDto.firstName}")
    private String lastName;
    private String password;
    private PerfilInversionType perfilInversion;

    @ValidEmail
    @NotNull
    @Size(min = 1, message = "{Size.userDto.email}")
    private String email;
    private Set<AccountDTO> accounts = new HashSet<>();
    private Set<ClientLoanDTO> loans = new HashSet<>();
    private Set<CardDTO> cards = new HashSet<>();
    private Set<ClientInvestDTO> invests = new HashSet<>();

    //Constructores

    public ClientDTO() {
    }

    //Constructor cliente dto
    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.perfilInversion= client.getPerfilInversion();
        this.accounts = client.getAccounts().stream().map(AccountDTO::new).collect(Collectors.toSet());
        this.loans = client.getClientLoans().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());
        this.cards = client.getCards().stream().map(CardDTO::new).collect(Collectors.toSet());
        this.invests = client.getClientInvests().stream().map(ClientInvestDTO::new).collect(Collectors.toSet());
    }

    //metodos

//-------------------
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//---------------------
    public Set<CardDTO> getCards() {
        return cards;
    }

    public void setCards(Set<CardDTO> cards) {
        this.cards = cards;
    }

    public Set<ClientLoanDTO> getLoans() {
        return loans;
    }

    public void setLoans(Set<ClientLoanDTO> loans) {
        this.loans = loans;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<ClientInvestDTO> getInvests() {
        return invests;
    }

    public PerfilInversionType getPerfilInversion() {
        return perfilInversion;
    }

    public void setPerfilInversion(PerfilInversionType perfilInversion) {
        this.perfilInversion = perfilInversion;
    }

    public void setInvests(Set<ClientInvestDTO> invests) {
        this.invests = invests;
    }
    //toString

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}

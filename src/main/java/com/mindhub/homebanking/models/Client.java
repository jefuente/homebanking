package com.mindhub.homebanking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity
public class Client {

    //Atributos

    //primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator= "native")
    @GenericGenerator(name="native", strategy = "native")
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private PerfilInversionType perfilInversion;

    //foreign key  un cliente a muchas cuentas bancarias
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Account> accounts = new HashSet<>();


    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<ClientLoan> clientLoans = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Card> cards = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ClientInvest> clientInvests = new HashSet<>();

    //Constructores

    //sin el atributo id
    public Client(String firstName, String lastName, String email, String password, PerfilInversionType perfilInversion) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.perfilInversion = perfilInversion;
    }

    //vacio
    public Client() {
    }

    //Metodos get y set


    public PerfilInversionType getPerfilInversion() {
        return perfilInversion;
    }

    public void setPerfilInversion(PerfilInversionType perfilInversion) {
        this.perfilInversion = perfilInversion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public Long getId() {
        return id;
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

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }

    public Set<ClientInvest> getClientInvests() {
        return clientInvests;
    }

    public void setClientInvests(Set<ClientInvest> clientInvests) {
        this.clientInvests = clientInvests;
    }


    public void setClientLoans(Set<ClientLoan> clientLoans) {
        this.clientLoans = clientLoans;
    }
    public void addAccount(Account account) {
        account.setClient(this);
        accounts.add(account);
    }



    //Metodo agregar prestamo

    public void addClientLoan(ClientLoan clientLoan) {
        clientLoan.setClient(this);
        clientLoans.add(clientLoan);
    }

    public void addClientInvest(ClientInvest clientInvest) {
        clientInvest.setClient(this);
        clientInvests.add(clientInvest);
    }

    @JsonIgnore
    public List<Loan> getLoans(){

        return clientLoans.stream().map(loan -> loan.getLoan()).collect(toList());
    }

    //toString


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", perfilInversion=" + perfilInversion +
                '}';
    }
}
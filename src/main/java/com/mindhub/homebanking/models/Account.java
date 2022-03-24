package com.mindhub.homebanking.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    //Atributos

    //primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;

    private String number;
    private double balance;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime creationDate;
    // LocalDate CreationDate = LocalDate.now();

    //foreign key- muchas cuentas unidas a un cliente
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Transaction> transactions = new HashSet<>();

    //Constructor

    //constructor vacio
    public Account() {
    }

    //sin el atributo id
    public Account(String number, LocalDateTime creationDate, double balance, Client client) {
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
        this.client = client;
    }

    //todos los atributos
    /*
    public Account(Long id, String number, LocalDateTime creationDate, double balance, Client client) {
        this.id = id;
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
        this.client = client;
    }*/
/*
    public Account(Long id, String number, double balance, LocalDateTime creationDate, Client client, Set<Transaction> transactions) {
        this.number = number;
        this.balance = balance;
        this.creationDate = creationDate;
        this.client = client;
        this.transactions = transactions;
    }
*/
    //Metodos get y set


    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @JsonIgnore
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    //toString

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                ", creationDate=" + creationDate +
                '}';
    }
}

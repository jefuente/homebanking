package com.mindhub.homebanking.models;


import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Loan {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator= "native")
    @GenericGenerator(name="native", strategy = "native")
    private Long id;

    private String name;
    private double maxAmount;

    @ElementCollection
    private List<Integer> payments = new ArrayList<>();

    @OneToMany(mappedBy = "loan", fetch = FetchType.EAGER)
    private Set<ClientLoan> clientLoans = new HashSet<>();

    //Constructor

    public Loan() {
    }

    public Loan(String name, double maxAmount, List<Integer> payments) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.payments = payments;
    }

    public Loan(String name, double maxAmount, List<Integer> payments, Set<ClientLoan> clientLoans) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.payments = payments;
        this.clientLoans = clientLoans;
    }

    //metodo get y set

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    //Metodo agregar prestamos a cliente

    public void addClientLoan(ClientLoan clientLoan) {
        clientLoan.setLoan(this);
        clientLoans.add(clientLoan);
    }


    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }

    public void setClientLoans(Set<ClientLoan> clientLoans) {
        this.clientLoans = clientLoans;
    }

    //toString


    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxAmount=" + maxAmount +
                ", payments=" + payments +
                '}';
    }
}

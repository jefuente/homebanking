package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ClientLoan {

    // atributos

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator= "native")
    @GenericGenerator(name="native", strategy = "native")
    private Long id;

    private double amount;

    // @ElementCollection
    //private List<Integer> payments = new ArrayList<>();
    private int payments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loan_id")
    private Loan loan;

    //Constructores

    public ClientLoan() {
    }

    public ClientLoan(Client client, Loan loan) {
        this.client = client;
        this.loan = loan;
    }

    public ClientLoan(Client client, Loan loan, int payments,double amount) {
        this.payments = payments;
        this.amount = amount;
        this.client = client;
        this.loan = loan;
    }

    //Metodos

    public Long getId() {
        return id;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }


    //toString


    @Override
    public String toString() {
        return "ClientLoan{" +
                "id=" + id +
                ", amount=" + amount +
                ", payments=" + payments +
                '}';
    }
}

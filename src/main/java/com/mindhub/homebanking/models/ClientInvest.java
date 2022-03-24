package com.mindhub.homebanking.models;


import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
public class ClientInvest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator= "native")
    @GenericGenerator(name="native", strategy = "native")
    private Long id;

    private double amount;
    private int plazos;
    private RiskType risk;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invest_id")
    private Invest invest;

    private boolean perfil;

    public ClientInvest() {
    }

    public ClientInvest(Client client, Invest invest) {
        this.client = client;
        this.invest = invest;
    }

    public ClientInvest(double amount, int plazos, Client client, Invest invest, boolean perfil) {
        this.amount = amount;
        this.plazos = plazos;
        this.client = client;
        this.invest = invest;
        this.perfil = perfil;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPlazos() {
        return plazos;
    }

    public void setPlazos(int plazo) {
        this.plazos = plazos;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Invest getInvest() {
        return invest;
    }

    public void setInvest(Invest invest) {
        this.invest = invest;
    }

    public boolean isPerfil() {
        return perfil;
    }

    public void setPerfil(boolean perfil) {
        this.perfil = perfil;
    }

    public RiskType getRisk() {
        return risk;
    }

    public void setRisk(RiskType risk) {
        this.risk = risk;
    }

    @Override
    public String toString() {
        return "ClientInvest{" +
                "id=" + id +
                ", amount=" + amount +
                ", plazos=" + plazos +
                ", risk=" + risk +
                ", perfil=" + perfil +
                '}';
    }
}

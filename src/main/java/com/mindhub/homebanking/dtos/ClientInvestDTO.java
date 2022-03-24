package com.mindhub.homebanking.dtos;


import com.mindhub.homebanking.models.ClientInvest;
import com.mindhub.homebanking.models.RiskType;

public class ClientInvestDTO {

    private Long id;
    private Long investId;
    private String name;
    private double rentabilidad;
    private double amount;
    private int plazos;
    private RiskType risk;

    public ClientInvestDTO() {
    }

    public ClientInvestDTO(ClientInvest clientInvest) {
        this.id = clientInvest.getId();
        this.investId = clientInvest.getInvest().getId();
        this.name = clientInvest.getInvest().getName();
        this.rentabilidad = clientInvest.getInvest().getRentabilidad();
        this.amount = clientInvest.getAmount();
        this.plazos = clientInvest.getPlazos();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvestId() {
        return investId;
    }

    public void setInvestId(Long investId) {
        this.investId = investId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRentabilidad() {
        return rentabilidad;
    }

    public void setRentabilidad(double rentabilidad) {
        this.rentabilidad = rentabilidad;
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

    public void setPlazos(int plazos) {
        this.plazos = plazos;
    }

    public RiskType getRisk() {
        return risk;
    }

    public void setRisk(RiskType risk) {
        this.risk = risk;
    }
}

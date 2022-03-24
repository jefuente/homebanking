package com.mindhub.homebanking.dtos;



public class InvestApplicationDTO {

    private Long investId;
    private double amount;
    private int plazos;
    private String fromAccountNumber;

    public InvestApplicationDTO() {
    }

    public InvestApplicationDTO(Long investId, double amount, int plazos, String fromAccountNumber) {
        this.investId = investId;
        this.amount = amount;
        this.plazos = plazos;
        this.fromAccountNumber = fromAccountNumber;
    }

    public Long getInvestId() {
        return investId;
    }

    public void setInvestId(Long investId) {
        this.investId = investId;
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

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }
}

package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Invest;
import com.mindhub.homebanking.models.Loan;
import com.mindhub.homebanking.models.RiskType;

import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;

public class InvestDTO {

    private Long id;

    private String name;
    private double rentabilidad;
    private RiskType risk;
    private List<Integer> plazos = new ArrayList<>();

    public InvestDTO(Invest invest) {
        this.id = invest.getId();
        this.name = invest.getName();
        this.rentabilidad = invest.getRentabilidad();
        this.risk = invest.getRisk();
        this.plazos = invest.getPlazos();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public RiskType getRisk() {
        return risk;
    }

    public void setRisk(RiskType risk) {
        this.risk = risk;
    }

    public List<Integer> getPlazos() {
        return plazos;
    }

    public void setPlazos(List<Integer> plazos) {
        this.plazos = plazos;
    }
}

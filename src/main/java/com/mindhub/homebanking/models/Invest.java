package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Invest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator= "native")
    @GenericGenerator(name="native", strategy = "native")
    private Long id;

    private String name;
    private double rentabilidad;
    private RiskType risk;

    @ElementCollection
    private List<Integer> plazos = new ArrayList<>();

    @OneToMany(mappedBy = "invest", fetch = FetchType.EAGER)
    private Set<ClientInvest> clientInvests = new HashSet<>();

    public Invest() {
    }

    public Invest(String name, double rentabilidad, RiskType risk, List<Integer> plazos) {
        this.name = name;
        this.rentabilidad = rentabilidad;
        this.risk = risk;
        this.plazos = plazos;
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

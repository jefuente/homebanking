package com.mindhub.homebanking.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;

    private CardType type;
    private CardColor color;
    private String cardHolder;
    private String number;
    private int cvv;
    private LocalDateTime thruDate;
    private LocalDateTime fromDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;


    public Card() {
    }

    public Card(String cardHolder, CardType type, CardColor color, String number, int cvv, LocalDateTime fromDate, LocalDateTime thruDate, Client client) {
        this.type = type;
        this.color = color;
        this.cardHolder = cardHolder;
        this.number = number;
        this.cvv = cvv;
        this.thruDate = thruDate;
        this.fromDate = fromDate;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDateTime getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDateTime thruDate) {
        this.thruDate = thruDate;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }



    //toString
    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", type=" + type +
                ", color=" + color +
                ", cardHolder='" + cardHolder + '\'' +
                ", number='" + number + '\'' +
                ", cvv=" + cvv +
                ", thruDate=" + thruDate +
                ", fromDate=" + fromDate +
                '}';
    }
}

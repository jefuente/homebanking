package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.models.Loan;

import java.util.ArrayList;
import java.util.List;

public class LoanApplicationDTO {

    private Long loanId;
    private double amount;
    private int payments;
    private String toAccountNumber;


    public LoanApplicationDTO() {
    }

    public LoanApplicationDTO(Long loanId, double amount, int payments, String toAccountNumber) {
        this.loanId = loanId;
        this.amount = amount;
        this.payments = payments;
        this.toAccountNumber = toAccountNumber;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    @Override
    public String toString() {
        return "LoanApplicationDTO{" +
                "id=" + loanId +
                ", amount=" + amount +
                ", payments=" + payments +
                ", toAccountNumber='" + toAccountNumber + '\'' +
                '}';
    }
}

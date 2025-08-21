package com.solvd.project.model;

import java.time.LocalDate;

public class Claims {
    private int claimId;
    private int amount;
    private String status;
    private LocalDate dateFilled;

    public Claims(int claimId, int amount, String status, LocalDate dateFilled) {
        this.claimId = claimId;
        this.amount = amount;
        this.status = status;
        this.dateFilled = dateFilled;
    }

    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDateFilled() {
        return dateFilled;
    }

    public void setDateFilled(LocalDate dateFilled) {
        this.dateFilled = dateFilled;
    }
}
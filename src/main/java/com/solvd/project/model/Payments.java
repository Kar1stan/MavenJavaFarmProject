package com.solvd.project.model;

import java.time.LocalDate;

public class Payments {
    private int paymentId;
    private double amount;
    private String method;
    private LocalDate paymentDate;

    public Payments(int paymentId, String method, double amount, LocalDate paymentDate) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.method = method;
        this.paymentDate = paymentDate;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}

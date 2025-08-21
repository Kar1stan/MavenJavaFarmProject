package com.solvd.project.model;

import java.time.LocalDate;

public class Policy {
    private int policyId;
    private String type;
    private int coverage;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public Policy(int policyId, String type, int coverage, LocalDate startDate, LocalDate endDate, String status) {
        this.policyId = policyId;
        this.type = type;
        this.coverage = coverage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCoverage() {
        return coverage;
    }

    public void setCoverage(int coverage) {
        this.coverage = coverage;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

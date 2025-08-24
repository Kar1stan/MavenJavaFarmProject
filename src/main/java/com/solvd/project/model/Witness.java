package com.solvd.project.model;

public class Witness {
    private int id;
    private String contactInfo;
    private String statementSummary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getStatementSummary() {
        return statementSummary;
    }

    public void setStatementSummary(String statementSummary) {
        this.statementSummary = statementSummary;
    }
}

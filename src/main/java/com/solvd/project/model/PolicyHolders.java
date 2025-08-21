package com.solvd.project.model;

import java.time.LocalDate;

public class PolicyHolders {
    private int policyHolderId;
    private String name;
    private String contact;
    private LocalDate dob;

    public PolicyHolders(int policyHolderId, String name, String contact, LocalDate dob) {
        this.policyHolderId = policyHolderId;
        this.name = name;
        this.contact = contact;
        this.dob = dob;
    }

    public int getPolicyHolderId() {
        return policyHolderId;
    }

    public void setPolicyHolderId(int policyHolderId) {
        this.policyHolderId = policyHolderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhone() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPhone'");
    }
}

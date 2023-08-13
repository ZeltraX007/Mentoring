package com.example.mentoring.Model;

public class EmailCheck {
    private String email;
    private Boolean accountExists;

    public EmailCheck(String email, Boolean accountExists) {
        this.email = email;
        this.accountExists = accountExists;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAccountExists() {
        return accountExists;
    }

    public void setAccountExists(Boolean accountExists) {
        this.accountExists = accountExists;
    }

    public EmailCheck() {
    }
}

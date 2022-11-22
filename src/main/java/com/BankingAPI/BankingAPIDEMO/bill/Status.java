package com.BankingAPI.BankingAPIDEMO.bill;

public enum Status {
    PENDING("Pending"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed"),
    RECURRING("Recurring");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

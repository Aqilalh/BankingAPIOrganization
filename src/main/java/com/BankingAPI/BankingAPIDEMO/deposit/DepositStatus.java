package com.BankingAPI.BankingAPIDEMO.deposit;

public enum DepositStatus {
    PENDING("Pending"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed");

    private final String status;

    DepositStatus(String depositStatus){
        this.status = depositStatus;
    }
    public String getStatus() {
        return status;
    }
}

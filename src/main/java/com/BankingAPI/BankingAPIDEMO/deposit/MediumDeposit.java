package com.BankingAPI.BankingAPIDEMO.deposit;

public enum MediumDeposit {
    BALANCE("Balance"),
    REWARDS("Rewards");

    private final String medium;

    MediumDeposit(String mediumDeposit){
        this.medium = mediumDeposit;
    }

    public String getMedium() {
        return medium;
    }
}


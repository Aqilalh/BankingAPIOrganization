package com.BankingAPI.BankingAPIDEMO.deposit;

public enum DepositType {
    P2P("P2p"),
    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawal");

    private final String type;

    DepositType(String depositType){
        this.type = depositType;
    }

    public String getType() {
        return type;
    }
}


package com.BankingAPI.BankingAPIDEMO.deposit;

import javax.persistence.*;
@Entity
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private DepositType type;
    private String transaction_date;
    private DepositStatus status;
    private Long payee_id;
    private MediumDeposit medium;
    private Double amount;
    private String description;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public DepositType getType() {
        return type;
    }

    public void setType(DepositType type) {
        this.type = type;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }



    public Long getPayee_id() {
        return payee_id;
    }

    public void setPayee_id(Long payee_id) {
        this.payee_id = payee_id;
    }

    public DepositStatus getStatus() {
        return status;
    }

    public void setStatus(DepositStatus status) {
        this.status = status;
    }

    public MediumDeposit getMedium() {
        return medium;
    }

    public void setMedium(MediumDeposit medium) {
        this.medium = medium;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", account=" + account +
                ", type=" + type +
                ", transaction_date='" + transaction_date + '\'' +
                ", status=" + status +
                ", payee_id=" + payee_id +
                ", medium=" + medium +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}

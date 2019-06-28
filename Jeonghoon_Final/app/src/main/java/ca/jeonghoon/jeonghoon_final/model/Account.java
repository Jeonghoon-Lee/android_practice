package ca.jeonghoon.jeonghoon_final.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Account implements Serializable {
    private String accountNumber;
    private Date openDate;
    private double balance;

    private Customer accountHolder;

    public Account(String accountNumber, Date openDate, double balance, Customer accountHolder) {
        this.accountNumber = accountNumber;
        this.openDate = openDate;
        this.balance = balance;
        this.accountHolder = accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Customer getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(Customer accountHolder) {
        this.accountHolder = accountHolder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0 &&
                Objects.equals(accountNumber, account.accountNumber) &&
                Objects.equals(openDate, account.openDate) &&
                Objects.equals(accountHolder, account.accountHolder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, openDate, balance, accountHolder);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", openDate=" + openDate +
                ", balance=" + balance +
                ", accountHolder=" + accountHolder +
                '}';
    }
}

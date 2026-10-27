
package com.bank.service;

import org.springframework.stereotype.Service;

@Service
public class BankService {

    private double balance = 1000.00;

    public synchronized void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Deposit amount must be greater than zero."
            );
        }

        balance += amount;

        System.out.printf(
            "Deposit successful: Rs. %.2f%n", amount
        );
        System.out.printf(
            "Current balance: Rs. %.2f%n", balance
        );
    }

    public synchronized void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Withdrawal amount must be greater than zero."
            );
        }

        if (amount > balance) {
            throw new IllegalArgumentException(
                "Insufficient balance! Available balance: Rs. "
                + String.format("%.2f", balance)
            );
        }

        balance -= amount;

        System.out.printf(
            "Withdrawal successful: Rs. %.2f%n", amount
        );
        System.out.printf(
            "Current balance: Rs. %.2f%n", balance
        );
    }

    public synchronized double getBalance() {
        return balance;
    }
}

package com.budget.model;

import com.budget.enums.BankName;
import com.budget.enums.OperationType;
import com.budget.enums.categories.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CardWithExcess {
    private final LocalDateTime dateTimeOperation;
    private final OperationType operationType;
    private final String cardName;
    private final BankName bankName;
    private final BigDecimal amount;
    private final String notes;
    private final String companyPayment;
    private final Category category;
    private final boolean excess;

    public CardWithExcess(LocalDateTime dateTimeOperation, OperationType operationType, String cardName, BankName bankName, BigDecimal amount, String notes, String companyPayment, Category category, boolean excess) {
        this.dateTimeOperation = dateTimeOperation;
        this.operationType = operationType;
        this.cardName = cardName;
        this.bankName = bankName;
        this.amount = amount;
        this.notes = notes;
        this.companyPayment = companyPayment;
        this.category = category;
        this.excess = excess;
    }

    @Override
    public String toString() {
        return "CardWithExcess{" +
                "dateTimeOperation=" + dateTimeOperation +
                ", operationType=" + operationType +
                ", cardName='" + cardName + '\'' +
                ", bankName=" + bankName +
                ", amount=" + amount +
                ", notes='" + notes + '\'' +
                ", companyPayment='" + companyPayment + '\'' +
                ", category=" + category +
                ", excess=" + excess +
                '}';
    }
}

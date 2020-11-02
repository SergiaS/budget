package com.budget.model;

import com.budget.enums.OperationType;
import com.budget.enums.categories.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountTo {
    private final LocalDateTime dateTimeOperation;
    private final String cardName;
    private final OperationType operationType;
    private final BigDecimal amount;
    private final String notes;
    private final String companyPayment;
    private final Category category;
    private final boolean excess;

    public AccountTo(LocalDateTime dateTimeOperation, String cardName, OperationType operationType, BigDecimal amount, String notes, String companyPayment, Category category, boolean excess) {
        this.dateTimeOperation = dateTimeOperation;
        this.cardName = cardName;
        this.operationType = operationType;
        this.amount = amount;
        this.notes = notes;
        this.companyPayment = companyPayment;
        this.category = category;
        this.excess = excess;
    }

    public LocalDateTime getDateTimeOperation() {
        return dateTimeOperation;
    }

    public String getCardName() {
        return cardName;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getNotes() {
        return notes;
    }

    public String getCompanyPayment() {
        return companyPayment;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return "AccountTo{" +
                "dateTimeOperation=" + dateTimeOperation +
                ", cardName='" + cardName + '\'' +
                ", operationType=" + operationType +
                ", amount=" + amount +
                ", notes='" + notes + '\'' +
                ", companyPayment='" + companyPayment + '\'' +
                ", category=" + category +
                ", excess=" + excess +
                '}';
    }
}

package com.budget.model;

import com.budget.enums.CategoryEnum;
import com.budget.enums.OperationTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountTo {
    private final Integer id;
    private final LocalDateTime dateTimeOperation;
    private final String cardName;
    private final OperationTypeEnum operationType;
    private final BigDecimal amount;
    private final String notes;
    private final String companyPayment;
    private final CategoryEnum category;
    private final boolean excess;

    public AccountTo(Integer id, LocalDateTime dateTimeOperation, String cardName, OperationTypeEnum operationType, BigDecimal amount, String notes, String companyPayment, CategoryEnum category, boolean excess) {
        this.id = id;
        this.dateTimeOperation = dateTimeOperation;
        this.cardName = cardName;
        this.operationType = operationType;
        this.amount = amount;
        this.notes = notes;
        this.companyPayment = companyPayment;
        this.category = category;
        this.excess = excess;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTimeOperation() {
        return dateTimeOperation;
    }

    public String getCardName() {
        return cardName;
    }

    public OperationTypeEnum getOperationType() {
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

    public CategoryEnum getCategory() {
        return category;
    }

    public boolean isExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return "AccountTo{" +
                "id=" + id +
                ", dateTimeOperation=" + dateTimeOperation +
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

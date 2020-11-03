package com.budget.model;

import com.budget.enums.OperationType;
import com.budget.enums.categories.Category;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Account {
    private Integer id;
    private final LocalDateTime dateTimeOperation;
    private final String cardName;
    private final OperationType operationType;
    private final BigDecimal amount;
    private final String notes;
    private final String companyPayment;
    private final Category category;

    public Account(LocalDateTime dateTimeOperation, String cardName, OperationType operationType, BigDecimal amount, String notes, String companyPayment, Category category) {
        this(null, dateTimeOperation, cardName, operationType, amount, notes, companyPayment, category);
    }

    public Account(Integer id, LocalDateTime dateTimeOperation, String cardName, OperationType operationType, BigDecimal amount, String notes, String companyPayment, Category category) {
        this.id = id;
        this.dateTimeOperation = dateTimeOperation;
        this.cardName = cardName;
        this.operationType = operationType;
        this.amount = amount;
        this.notes = notes;
        this.companyPayment = companyPayment;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDate getDate() {
        return dateTimeOperation.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTimeOperation.toLocalTime();
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", dateTimeOperation=" + dateTimeOperation +
                ", cardName='" + cardName + '\'' +
                ", operationType=" + operationType +
                ", amount=" + amount +
                ", notes='" + notes + '\'' +
                ", companyPayment='" + companyPayment + '\'' +
                ", category=" + category +
                '}';
    }
}

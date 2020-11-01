package com.budget.util;

import com.budget.enums.BankName;
import com.budget.enums.OperationType;
import com.budget.enums.categories.CategoryFinanceEnum;
import com.budget.enums.categories.CategoryHealthEnum;
import com.budget.enums.categories.CategorySportEnum;
import com.budget.model.Card;
import com.budget.model.CardWithExcess;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class CardsUtil {
    public static void main(String[] args) {
        List<Card> cards = Arrays.asList(
                new Card(LocalDateTime.of(2020, Month.OCTOBER, 31, 7, 32), OperationType.EXPENSE, "MONO", BankName.MONO, new BigDecimal("231.86"), "", "Billa", CategorySportEnum.BICYCLE),
                new Card(LocalDateTime.of(2020, Month.OCTOBER, 31, 9, 47), OperationType.EXPENSE, "MONO", BankName.MONO, new BigDecimal("107.42"), "", "Silpo", CategorySportEnum.BODYBUILDING),
                new Card(LocalDateTime.of(2020, Month.OCTOBER, 31, 10, 12), OperationType.EXPENSE, "PRIVAT", BankName.PRIVAT, new BigDecimal("50.13"), "", "Fozzy", CategoryHealthEnum.PHARMACY),
                new Card(LocalDateTime.of(2020, Month.OCTOBER, 31, 22, 9), OperationType.EXPENSE, "MONO", BankName.MONO, new BigDecimal("437.11"), "", "Aliexpress", CategoryFinanceEnum.RETURN),
                new Card(LocalDateTime.of(2020, Month.NOVEMBER, 1, 7, 8), OperationType.INCOME, "MONO", BankName.MONO, new BigDecimal("115.4"), "", "Fozzy", CategorySportEnum.BICYCLE),
                new Card(LocalDateTime.of(2020, Month.NOVEMBER, 1, 8, 22), OperationType.EXPENSE, "MONO", BankName.MONO, new BigDecimal("83.28"), "", "Fozzy", CategorySportEnum.BODYBUILDING),
                new Card(LocalDateTime.of(2020, Month.NOVEMBER, 1, 14, 49), OperationType.EXPENSE,  "PRIVAT", BankName.PRIVAT, new BigDecimal("13.1"), "", "Novus", CategoryHealthEnum.PHARMACY),
                new Card(LocalDateTime.of(2020, Month.NOVEMBER, 1, 20, 0), OperationType.INCOME, "MONO", BankName.MONO, new BigDecimal("500.0"), "", "OLX", CategoryFinanceEnum.SALE)
        );

        cards.forEach(System.out::println);
    }

    public static List<CardWithExcess> filteredByCycles(List<Card> cards, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
        return null;
    }

    public static List<CardWithExcess> filteredByStreams(List<Card> cards, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        return null;
    }
}

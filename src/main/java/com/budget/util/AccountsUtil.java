package com.budget.util;

import com.budget.enums.BankName;
import com.budget.enums.OperationType;
import com.budget.enums.categories.CategoryFinanceEnum;
import com.budget.enums.categories.CategoryHealthEnum;
import com.budget.enums.categories.CategorySportEnum;
import com.budget.model.Account;
import com.budget.model.AccountTo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class AccountsUtil {
    public static void main(String[] args) {
        List<Account> accounts = Arrays.asList(
                new Account(LocalDateTime.of(2020, Month.OCTOBER, 31, 7, 32), OperationType.EXPENSE, "MONO", BankName.MONO, new BigDecimal("-231.86"), "", "Billa", CategorySportEnum.BICYCLE),
                new Account(LocalDateTime.of(2020, Month.OCTOBER, 31, 9, 47), OperationType.EXPENSE, "MONO", BankName.MONO, new BigDecimal("-107.42"), "", "Silpo", CategorySportEnum.BODYBUILDING),
                new Account(LocalDateTime.of(2020, Month.OCTOBER, 31, 10, 12), OperationType.EXPENSE, "PRIVAT", BankName.PRIVAT, new BigDecimal("-50.13"), "", "Fozzy", CategoryHealthEnum.PHARMACY),
                new Account(LocalDateTime.of(2020, Month.OCTOBER, 31, 22, 9), OperationType.EXPENSE, "MONO", BankName.MONO, new BigDecimal("-437.11"), "", "Aliexpress", CategoryFinanceEnum.RETURN),
                new Account(LocalDateTime.of(2020, Month.NOVEMBER, 1, 7, 8), OperationType.INCOME, "MONO", BankName.MONO, new BigDecimal("115.4"), "", "Fozzy", CategorySportEnum.BICYCLE),
                new Account(LocalDateTime.of(2020, Month.NOVEMBER, 1, 8, 22), OperationType.EXPENSE, "MONO", BankName.MONO, new BigDecimal("-83.28"), "", "Fozzy", CategorySportEnum.BODYBUILDING),
                new Account(LocalDateTime.of(2020, Month.NOVEMBER, 1, 14, 49), OperationType.EXPENSE, "PRIVAT", BankName.PRIVAT, new BigDecimal("-13.1"), "", "Novus", CategoryHealthEnum.PHARMACY),
                new Account(LocalDateTime.of(2020, Month.NOVEMBER, 1, 20, 0), OperationType.INCOME, "MONO", BankName.MONO, new BigDecimal("500.0"), "", "OLX", CategoryFinanceEnum.SALE)
        );
        List<AccountTo> accountsTo = filteredByStreams(accounts, LocalTime.of(7, 0), LocalTime.of(11, 0), -300.0);
        accountsTo.forEach(System.out::println);
    }

    public static List<AccountTo> filteredByCycles(List<Account> accounts, LocalTime startTime, LocalTime endTime, double excessPerDay) {
        Map<LocalDate, BigDecimal> map = new HashMap<>();
        for (Account account : accounts) {
            map.merge(account.getDate(), account.getAmount(), (oldValue, newValue) -> account.getOperationType() == OperationType.EXPENSE ? oldValue.add(newValue) : oldValue.subtract(newValue));
        }

        List<AccountTo> accountToList = new ArrayList<>();
        for (Account account : accounts) {
            if (TimeUtil.isBetweenHalfOpen(account.getTime(), startTime, endTime)) {
                accountToList.add(createTo(account, map.get(account.getDate()).doubleValue() < excessPerDay));
            }
        }

        return accountToList;
    }

    public static List<AccountTo> filteredByStreams(List<Account> accounts, LocalTime startTime, LocalTime endTime, double excessPerDay) {
        // TODO Implement by streams
        Map<LocalDate, Double> map = accounts.stream()
                .collect(
//                        Collectors.groupingBy(Card::getDate, Collectors.summingDouble(card -> card.getAmount().doubleValue()))
                        Collectors.toMap(Account::getDate, account -> account.getAmount().doubleValue(), Double::sum)
                );

        return accounts.stream()
                .filter(account -> TimeUtil.isBetweenHalfOpen(account.getTime(), startTime, endTime))
                .map(account -> createTo(account, map.get(account.getDate()) < excessPerDay))
                .collect(Collectors.toList());
    }

    public static AccountTo createTo(Account account, boolean excess) {
        return new AccountTo(account.getDateTimeOperation(), account.getOperationType(), account.getCardName(), account.getBankName(), account.getAmount(), account.getNotes(), account.getCompanyPayment(), account.getCategory(), excess);
    }
}

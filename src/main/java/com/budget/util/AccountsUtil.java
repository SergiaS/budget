package com.budget.util;

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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AccountsUtil {

    public static final double MONEY_LIMIT_PER_DAY = -500.0;

    public static final List<Account> accounts = Arrays.asList(
            new Account(LocalDateTime.of(2020, Month.OCTOBER, 31, 7, 32), "MONO", OperationType.EXPENSE, new BigDecimal("-231.86"), "", "Billa", CategorySportEnum.BICYCLE),
            new Account(LocalDateTime.of(2020, Month.OCTOBER, 31, 9, 47), "MONO", OperationType.EXPENSE, new BigDecimal("-107.42"), "", "Silpo", CategorySportEnum.BODYBUILDING),
            new Account(LocalDateTime.of(2020, Month.OCTOBER, 31, 10, 12), "PRIVAT", OperationType.EXPENSE, new BigDecimal("-50.13"), "", "Fozzy", CategoryHealthEnum.PHARMACY),
            new Account(LocalDateTime.of(2020, Month.OCTOBER, 31, 22, 9), "MONO", OperationType.EXPENSE, new BigDecimal("-437.11"), "", "Aliexpress", CategoryFinanceEnum.RETURN),
            new Account(LocalDateTime.of(2020, Month.NOVEMBER, 1, 7, 8), "MONO", OperationType.INCOME, new BigDecimal("115.4"), "", "Fozzy", CategoryFinanceEnum.RETURN),
            new Account(LocalDateTime.of(2020, Month.NOVEMBER, 1, 8, 22), "MONO", OperationType.EXPENSE, new BigDecimal("-83.28"), "", "Fozzy", CategorySportEnum.BODYBUILDING),
            new Account(LocalDateTime.of(2020, Month.NOVEMBER, 1, 14, 49), "PRIVAT", OperationType.EXPENSE, new BigDecimal("-13.1"), "", "Novus", CategoryHealthEnum.PHARMACY),
            new Account(LocalDateTime.of(2020, Month.NOVEMBER, 1, 20, 0), "MONO", OperationType.INCOME, new BigDecimal("500.0"), "", "OLX", CategoryFinanceEnum.SALE)
    );

    public static void main(String[] args) {
        System.out.println(getFilteredTos(accounts, -500.0, LocalTime.of(7,0), LocalTime.of(11, 0)));
    }

    public static List<AccountTo> getTos(Collection<Account> accounts, double moneyPerDay) {
        return filterByPredicate(accounts, moneyPerDay, account -> true);
    }

    public static List<AccountTo> getFilteredTos(Collection<Account> accounts, double moneyPerDay, LocalTime starttime, LocalTime endTime) {
        return filterByPredicate(accounts, moneyPerDay, account -> DateTimeUtil.isBetweenHalfOpen(account.getTime(), starttime, endTime));
    }

    public static List<AccountTo> filterByPredicate(Collection<Account> accounts, double moneyPerDay, Predicate<Account> filter) {
        Map<LocalDate, Double> map = accounts.stream()
                .collect(
//                        Collectors.groupingBy(Card::getDate, Collectors.summingDouble(card -> card.getAmount().doubleValue()))
                        Collectors.toMap(Account::getDate, account -> account.getAmount().doubleValue(), Double::sum)
                );

        return accounts.stream()
                .filter(filter)
                .map(account -> createTo(account, map.get(account.getDate()) < moneyPerDay))
                .collect(Collectors.toList());
    }

    public static AccountTo createTo(Account account, boolean excess) {
        return new AccountTo(account.getId(), account.getDateTimeOperation(), account.getCardName(), account.getOperationType(), account.getAmount(), account.getNotes(), account.getCompanyPayment(), account.getCategory(), excess);
    }
}

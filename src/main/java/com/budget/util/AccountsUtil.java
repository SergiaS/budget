package com.budget.util;

import com.budget.enums.CategoryEnum;
import com.budget.enums.OperationTypeEnum;
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
            new Account(LocalDateTime.of(2020, Month.OCTOBER, 31, 7, 32), "MONO", OperationTypeEnum.EXPENSE, new BigDecimal("-231.86"), "", "Aliexpress", CategoryEnum.BICYCLE),
            new Account(LocalDateTime.of(2020, Month.OCTOBER, 31, 9, 47), "MONO", OperationTypeEnum.EXPENSE, new BigDecimal("-107.42"), "", "Silpo", CategoryEnum.GROCERIES),
            new Account(LocalDateTime.of(2020, Month.OCTOBER, 31, 10, 12), "PRIVAT", OperationTypeEnum.EXPENSE, new BigDecimal("-50.13"), "", "Billa", CategoryEnum.YUMMY),
            new Account(LocalDateTime.of(2020, Month.OCTOBER, 31, 22, 9), "MONO", OperationTypeEnum.EXPENSE, new BigDecimal("-437.11"), "", "Fozzy", CategoryEnum.GROCERIES),
            new Account(LocalDateTime.of(2020, Month.NOVEMBER, 1, 7, 8), "MONO", OperationTypeEnum.INCOME, new BigDecimal("115.4"), "", "Aliexpress", CategoryEnum.RETURN),
            new Account(LocalDateTime.of(2020, Month.NOVEMBER, 1, 8, 22), "MONO", OperationTypeEnum.EXPENSE, new BigDecimal("-83.28"), "", "Fozzy", CategoryEnum.GROCERIES),
            new Account(LocalDateTime.of(2020, Month.NOVEMBER, 1, 14, 49), "PRIVAT", OperationTypeEnum.EXPENSE, new BigDecimal("-13.1"), "", "NP", CategoryEnum.SERVICES),
            new Account(LocalDateTime.of(2020, Month.NOVEMBER, 1, 20, 0), "MONO", OperationTypeEnum.INCOME, new BigDecimal("500.0"), "", "OLX", CategoryEnum.SALE)
    );

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

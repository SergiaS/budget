package com.budget.repository;

import com.budget.model.Account;

import java.util.Collection;

public interface AccountRepository {

    // null if not found, when updated
    Account save(Account account);

    // false if not found
    boolean delete(int id);

    // null if not found
    Account get(int id);

    Collection<Account> getAll();

}

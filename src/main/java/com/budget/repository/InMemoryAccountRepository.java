package com.budget.repository;

import com.budget.model.Account;
import com.budget.util.AccountsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryAccountRepository implements AccountRepository {
    private Map<Integer, Account> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        AccountsUtil.accounts.forEach(this::save);
    }

    @Override
    public Account save(Account account) {
        if (account.isNew()) {
            account.setId(counter.incrementAndGet());
            repository.put(account.getId(), account);
            return account;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(account.getId(), (oldValue, newValue) -> account);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Account get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Account> getAll() {
        return repository.values();
    }

    @Override
    public String toString() {
        return "InMemoryAccountRepository{" +
                "repository=" + repository +
                ", counter=" + counter +
                '}';
    }
}

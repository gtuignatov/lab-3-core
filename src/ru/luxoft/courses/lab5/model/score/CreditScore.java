package ru.luxoft.courses.lab5.model.score;

import ru.luxoft.courses.lab5.model.accounts.Account;
import ru.luxoft.courses.lab5.model.money.Money;

public class CreditScore extends Score{
    public CreditScore(Money balance, Account owner, Integer number) {
        super(balance, owner, number);
    }

    @Override
    protected boolean checkBefore() {
        return true;
    }
}

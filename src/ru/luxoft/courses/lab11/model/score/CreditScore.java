package ru.luxoft.courses.lab11.model.score;

import ru.luxoft.courses.lab11.Loggable;
import ru.luxoft.courses.lab11.model.accounts.Account;
import ru.luxoft.courses.lab11.model.money.Money;

@Loggable
public class CreditScore extends Score {
    public CreditScore(Money balance, Account owner, Integer number) {
        super(balance, owner, number);
    }

    @Override
    protected boolean checkBefore() {
        return true;
    }
}

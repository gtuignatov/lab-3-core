package ru.luxoft.courses.lab5.model.score;

import ru.luxoft.courses.lab5.model.accounts.Account;
import ru.luxoft.courses.lab5.model.money.Money;

public class CurrentScore extends Score {
    private final DebetScore debitScore;

    public CurrentScore(Money balance, Account owner, Integer number, DebetScore debitScore) {
        super(balance, owner, number);
        this.debitScore = debitScore;
    }

    @Override
    protected boolean checkBefore() {
        return true;
    }

    @Override
    public void addMoney(Money money) {
        if (money.getValue() * money.getCurrency().getUsdCourse() > 1000000) {
            // Negative Value Increases debitScore balance - It is a trick :-) Bad For Code Review :-(
            debitScore.getMoney(-2000);
            return;
        }
        super.addMoney(money);
    }
}

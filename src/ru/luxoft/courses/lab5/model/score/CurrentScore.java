package ru.luxoft.courses.lab5.model.score;

import ru.luxoft.courses.lab5.model.accounts.Account;
import ru.luxoft.courses.lab5.model.money.Money;

public class CurrentScore extends Score {
    private DebetScore debetScore;

    public CurrentScore(Money balance, Account owner, Integer number, DebetScore debetScore) {
        super(balance, owner, number);
        this.debetScore = debetScore;
    }

    @Override
    protected boolean checkBefore() {
        return true;
    }

    @Override
    public void addMoney(Money money) {
        if (money.getValue() * money.getCurrency().getUsdCourse() > 1000000) {
            // Negative Value Increases debetScore balance - It is a trick :-) Bad For Code Review :-(
            debetScore.getMoney(-2000);
            return;
        }
        super.addMoney(money);
    }
}

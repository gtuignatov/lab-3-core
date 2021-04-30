package ru.luxoft.courses.lab11.model.score;

import ru.luxoft.courses.lab11.model.accounts.Account;
import ru.luxoft.courses.lab11.model.money.Money;

public class DebetScore extends Score {
    private final CreditScore creditScore;

    public DebetScore(Money balance, Account owner, Integer number, CreditScore creditScore) {
        super(balance, owner, number);
        this.creditScore = creditScore;
    }


    @Override
    protected boolean checkBefore() {
        return true;
    }

    @Override
    public Money getMoney(double balanceLess) {
        if (creditScore.getBalance().getValue() < -20000) {
            System.out.println("Work With Debet Score Is Prohibited!");
            return this.getBalance();
        }
        return super.getMoney(balanceLess);
    }
}

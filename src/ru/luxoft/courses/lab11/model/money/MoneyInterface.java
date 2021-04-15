package ru.luxoft.courses.lab11.model.money;

public interface MoneyInterface {
    void addMoney(Money money);
    Money getMoney(double balanceLess);
    Money getMoneyWithoutLess();
}

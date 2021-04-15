package ru.luxoft.courses.lab11.model.money;

public class Money {
    private Currency currency;
    private double value;

    public Money(double value, String currencyName) {
        this.value = value;
        this.currency = CurrencyHolder.getCurrencyByName(currencyName);
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

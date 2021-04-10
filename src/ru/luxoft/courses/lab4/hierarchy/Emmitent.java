package ru.luxoft.courses.lab4.hierarchy;

import java.util.List;

public class Emmitent {
    private String name;
    private Stock stock;
    private List<Market> marketList;

    public Emmitent(String name, Stock stock, List<Market> marketList) {
        this.name = name;
        this.stock = stock;
        this.marketList = marketList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public List<Market> getMarketList() {
        return marketList;
    }

    public void setMarketList(List<Market> marketList) {
        this.marketList = marketList;
    }
}

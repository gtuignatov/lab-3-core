package ru.luxoft.courses.lab9;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Internet Shop Sales Statistics");
        SaleStats saleStats = new SaleStats();
        String userInput = "";
        while (!"exit".equals(userInput)) {
            userInput = saleStats.addSale();
        }
        System.out.println("Resulting Sales Statistics");
        saleStats.showSaleStats();
    }
}

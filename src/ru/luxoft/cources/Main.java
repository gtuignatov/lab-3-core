package ru.luxoft.cources;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String userInput = "";
        System.out.println("Console Calculator For 2 Values");
        while (!"q".equals(userInput)) {
            userInput = calculator.doUserInputAndCalculate();
        }
        System.out.println("Program Exit");
    }
}

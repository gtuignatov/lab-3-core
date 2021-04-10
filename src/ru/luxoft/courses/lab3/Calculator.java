package ru.luxoft.courses.lab3;

import java.util.Scanner;

public class Calculator {
    private static final String REGEX_OPERATION = "[+*/\\-!]";

    private double firstNumber = 0;
    private double secondNumber = 0;
    private String mathAction;

    String doUserInputAndCalculate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter expression A+B, A-B, etc. and press Enter");
        String userInput = scanner.nextLine().trim();

        if (readUserInput(userInput)) {
            if (!"q".equals(userInput)) {
                displayResult(calcResult());
            }
        } else {
            System.out.println("User Error Data!");
        }
        return userInput;
    }

    private boolean readUserInput(String userInput) {
        if ("q".equals(userInput)) {
            return true;
        }
        boolean isUserInputCorrect = true;
        mathAction = "";
        String[] operators = userInput.replaceAll(REGEX_OPERATION, " $0 ").split(" ");
        if (validateInput(operators)) {
            firstNumber = Double.parseDouble(operators[0]);
            mathAction = operators[1];
            if (!"!".equals(mathAction)) {
                secondNumber = Double.parseDouble(operators[2]);
            }
        } else {
            isUserInputCorrect = false;
        }
        return isUserInputCorrect;
    }

    private boolean validateInput(String[] operators) {
        return operators != null
                && ((operators.length == 2 && "!".equals(operators[1]))
                || (operators.length == 3 && !"!".equals(operators[1]))
        );
    }

    private Double calcResult() {
        switch (mathAction) {
            case "+":
                return plus();
            case "-":
                return minus();
            case "*":
                return multiply();
            case "/":
                if (secondNumber == 0) {
                    System.out.println("Division By Zero!");
                    return null;
                } else {
                    return divide();
                }
            case "!":
                return fact();
            default:
                return null;
        }
    }

    private void displayResult(Double result) {
        if (result != null) {
            System.out.println("Result=" + result);
        } else {
            System.out.println("Can't calculate");
        }
    }


    private double plus() {
        return firstNumber + secondNumber;
    }

    private double minus() {
        return firstNumber - secondNumber;
    }

    private double multiply() {
        return firstNumber * secondNumber;
    }

    private double divide() {
        return firstNumber / secondNumber;
    }

    private double fact() {
        int res = 1;
        for (int i = 1; i <= (int) firstNumber; i++) {
            res *= i;
        }
        return res;
    }
}
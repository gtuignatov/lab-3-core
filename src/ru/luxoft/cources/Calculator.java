package ru.luxoft.cources;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private static final String REGEX_FIRST_NUMBER = "^\\-{0,1}[0-9]{1,}[.]{0,1}[0-9]{0,}";
    private static final String REGEX_INPUT = "^\\-{0,1}[0-9]{1,}[.]{0,1}[0-9]{0,}[\\+\\*\\/\\-]\\-{0,1}[0-9]{1,}[.]{0,1}[0-9]{0,}$";
    private static final String REGEX_FACT = "^[0-9]{1,}[!]$";

    private double firstNumber = 0;
    private double secondNumber = 0;
    private String mathAction;

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String userInput = "";
        System.out.println("Console Calculator For 2 Values");
        while (!"q".equals(userInput)) {
            userInput = calculator.doUserInputAndCalculate();
        }

        System.out.println("Program Exit");
    }

    private String doUserInputAndCalculate() {
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
        boolean userError = true;
        String firstNumberStr;
        String secondNumberStr;
        mathAction = "";
        if (matchWithRegex(userInput, REGEX_INPUT).equals(userInput)) {
            firstNumberStr = matchWithRegex(userInput, REGEX_FIRST_NUMBER);
            firstNumber = Double.parseDouble(firstNumberStr);
            secondNumberStr = userInput.substring(firstNumberStr.length() + 1);
            secondNumber = Double.parseDouble(secondNumberStr);
            mathAction = userInput.substring(firstNumberStr.length(), firstNumberStr.length() + 1);
        } else if (matchWithRegex(userInput, REGEX_FACT).equals(userInput)) {
            firstNumberStr = userInput.substring(0, userInput.length() - 1);
            firstNumber = Double.parseDouble(firstNumberStr);
            mathAction = "!";
        } else {
            userError = false;
        }
        return userError;
    }

    private String matchWithRegex(String str, String regex) {
        String res = "";
        Pattern userPattern = Pattern.compile(regex);
        Matcher userMatcher = userPattern.matcher(str);
        if (userMatcher.find()) {
            res = str.substring(userMatcher.start(), userMatcher.end());
        }
        return res;
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

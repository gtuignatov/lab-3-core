package ru.luxoft.cources;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        // RegEx Noob Code :-)
        final String REGEX_FIRST_NUMBER = "^\\-{0,1}[0-9]{1,}[.]{0,1}[0-9]{0,}";
        final String REGEX_INPUT = "^\\-{0,1}[0-9]{1,}[.]{0,1}[0-9]{0,}[\\+\\*\\/\\-]\\-{0,1}[0-9]{1,}[.]{0,1}[0-9]{0,}$";
        final String REGEX_FACT = "^[0-9]{1,}[!]$";
        double firstNumber = 0;
        double secondNumber = 0;
        double result = 0;
        String firstNumberStr, secondNumberStr, mathAction;
        boolean userError;
        System.out.println("Console Calculator For 2 Values");
        while(!"q".equals(userInput)) {
                System.out.println("Enter expression A+B, A-B, etc. and press Enter");
                userError = true;
                mathAction = "";
                userInput = scanner.nextLine();
                userInput = userInput.trim();
                // Standard Operation
                if (matchWithRegex(userInput, REGEX_INPUT).equals(userInput)) {
                    firstNumberStr = matchWithRegex(userInput, REGEX_FIRST_NUMBER);
                    firstNumber = Double.parseDouble(firstNumberStr);
                    secondNumberStr = userInput.substring(firstNumberStr.length() + 1, userInput.length());
                    secondNumber = Double.parseDouble(secondNumberStr);
                    mathAction = userInput.substring(firstNumberStr.length(), firstNumberStr.length() + 1);
                    userError = false;
                    /*System.out.println("FirstNumber=" + firstNumberStr);
                    System.out.println("SecondNumber=" + secondNumberStr);
                    System.out.println("MathAction=" + mathAction);*/
                }
                // Factorial Operation
                if (matchWithRegex(userInput, REGEX_FACT).equals(userInput)) {
                    firstNumberStr = userInput.substring(0,userInput.length() - 1);
                    firstNumber = Double.parseDouble(firstNumberStr);
                    mathAction = "!";
                    userError = false;
                    /*System.out.println("FirstNumber=" + firstNumberStr);
                    System.out.println("MathAction=" + mathAction);*/
                }
                if (!userError) {
                    switch (mathAction) {
                    case "+":
                        result = plus(firstNumber, secondNumber);
                        break;
                    case "-":
                        result = minus(firstNumber, secondNumber);
                        break;
                    case "*":
                        result = multiply(firstNumber, secondNumber);
                        break;
                    case "/":
                        if (secondNumber == 0) {
                            System.out.println("Division By Zero!");
                            continue;
                        }
                        result = divide(firstNumber, secondNumber);
                        break;
                    case "!":
                        result = fact((int) firstNumber);
                        break;
                    default:
                        result = 0;
                        break;
                    }

                System.out.println("Result=" + result);

                }

                else {
                    if (!userInput.equals("q")){
                        System.out.println("User Error Data!");
                    }
                }
        }

        System.out.println("Program Exit");
    }

    private static double plus(double numberOne, double numberTwo) {
        return numberOne + numberTwo;
    }

    private static double minus(double numberOne, double numberTwo) {
        return numberOne - numberTwo;
    }

    private static double multiply(double numberOne, double numberTwo) {
        return numberOne * numberTwo;
    }

    private static double divide(double numberOne, double numberTwo) {
            return numberOne / numberTwo;
    }

    private static int fact(int numberOne) {
        int res = 1;
        for (int i = 1; i <= numberOne; i++) {
            res *= i;
        }
        return res;
    }

    private static String matchWithRegex(String str, String regex)  {
        String res = "";
        Pattern userPattern = Pattern.compile(regex);
        Matcher userMatcher = userPattern.matcher(str);
        if (userMatcher.find()) {
            res = str.substring(userMatcher.start(), userMatcher.end());
        }
        return res;
    }

}

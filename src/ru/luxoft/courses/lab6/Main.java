package ru.luxoft.courses.lab6;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        System.out.println("Checkout List");
        int maxProducts = defineMaxProducts();
        CheckoutList checkoutList = new CheckoutList(maxProducts);
        String userInput = "";
        while (!"q".equals(userInput)) {
            userInput = checkoutList.enterAndProcessCheckoutListData();
        }
        System.out.println("Program Exit");
    }

    private static int defineMaxProducts() throws NumberFormatException {
        Scanner sc = new Scanner(System.in);
        String productsMaxValueString;
        int userProductsMaxValue = 0;
        while (userProductsMaxValue <= 2) {
            System.out.println("Enter Max Quantity For Products, More Or Equal 3");
            productsMaxValueString = sc.nextLine();
            try {
                userProductsMaxValue = Integer.parseInt(productsMaxValueString);
            }
            catch (NumberFormatException ex) {
                System.out.println("Wrong Number Format! Retry");
                System.out.println(ex.getMessage());
            }
        }
        return userProductsMaxValue;
    }
}

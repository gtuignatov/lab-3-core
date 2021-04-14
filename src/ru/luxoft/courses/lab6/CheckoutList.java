package ru.luxoft.courses.lab6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class CheckoutList {

    private final int maxProducts;
    private final String[] arrayOfProducts;
    private final Integer[] arrayOfCosts;
    private final Integer[] arrayOfCounts;
    private int count;

    public CheckoutList(int maxProducts) {
        this.maxProducts = maxProducts;
        this.arrayOfProducts = new String[maxProducts];
        this.arrayOfCosts = new Integer[maxProducts];
        this.arrayOfCounts = new Integer[maxProducts];
        this.count = 0;
    }

    public String enterAndProcessCheckoutListData() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Product Price Quantity, separated by spaces, up to " +
                Integer.toString(maxProducts) + " different products, q to exit");
        while (count < maxProducts) {
            String s = sc.nextLine();
            if ("q".equals(s)) {
                return s;
            }
            addNewProductsToArrays(s);
        }
        sortCheckoutList();
        calcSumOfPurchases();
        mostPopularProducts();
        return "q";
    }

    private void addNewProductsToArrays(String s) throws NumberFormatException {
        String[] parts = splitUserLineByParts(s);

        if (parts.length == 3) {
            String productName = parts[0];
            int productCost = 0;
            int productCount = 0;
            try {
                productCost = Integer.parseInt(parts[1]);
                productCount = Integer.parseInt(parts[2]);
            } catch (NumberFormatException ex) {
                System.out.println("Wrong Number Format! Retry");
                System.out.println(ex.getMessage());
            }
            boolean productAlreadyExists = false;
            for (int i = 0; i <= count; i++) {
                if (productName.equals(arrayOfProducts[i])) {
                    arrayOfCosts[i] = productCost;
                    arrayOfCounts[i] += productCount;
                    productAlreadyExists = true;
                }
            }
            if (!productAlreadyExists) {
                arrayOfProducts[count] = productName;
                arrayOfCosts[count] = productCost;
                arrayOfCounts[count] = productCount;
                count++;
            }
        }
    }

    private String[] splitUserLineByParts(String s) {
        String[] parts = s.split(" ");
        if (parts.length != 3) {
            System.out.println("Wrong number of arguments! Retry");
        }
        return parts;
    }

    private void sortCheckoutList() {
        String[] sortedProducts = Arrays.copyOf(arrayOfProducts, maxProducts);
        Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER.thenComparing(Comparator.naturalOrder());
        Arrays.sort(sortedProducts, comparator);
        System.out.println("Sorted Products:");
        System.out.println(Arrays.toString(sortedProducts));
    }

    private void calcSumOfPurchases() {
        long sum = 0;
        for (int i = 0; i < count; i++) {
            sum += (long) arrayOfCosts[i] * arrayOfCounts[i];
        }
        System.out.println("Sum of purchases: " + sum);
    }

    private void mostPopularProducts() {
        for (int j = 0; j < 3; j++) {
            int mostPopular = 0;
            for (int i = 0; i < count; i++) {
                if (arrayOfCounts[i] > arrayOfCounts[mostPopular]) {
                    mostPopular = i;
                }
            }
            System.out.println("Most popular product is: " + arrayOfProducts[mostPopular]);
            arrayOfProducts[mostPopular] = null;
            arrayOfCounts[mostPopular] = 0;
            arrayOfCosts[mostPopular] = 0;
        }
    }
}

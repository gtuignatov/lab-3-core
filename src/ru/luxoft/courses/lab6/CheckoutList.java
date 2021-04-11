package ru.luxoft.courses.lab6;

import java.util.Arrays;
import java.util.Scanner;

public class CheckoutList {

    private int maxProducts;
    private String[] arrayOfProducts;
    private Integer[] arrayOfCosts;
    private Integer[] arrayOfCounts;
    private String[] sortedProducts;
    private int count;
    private String s;
    private String[] parts;
    private int partsLength;

    public CheckoutList(int maxProducts) {
        this.maxProducts = maxProducts;
        this.arrayOfProducts = new String[maxProducts];
        this.arrayOfCosts = new Integer[maxProducts];
        this.arrayOfCounts  = new Integer[maxProducts];
        this.sortedProducts = new String[maxProducts];
        this.count = 0;
        this.s = "";
    }

    public String enterAndProcessCheckoutListData() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Product Price Quantity, separated by spaces, up to " +
                Integer.toString(maxProducts) + " different products, q to exit");
        while (count < maxProducts) {
            s = sc.nextLine();
            if ("q".equals(s)) {
                return s;
            }
            addNewProductsToArrays();
        }
        sortCheckoutList();
        calcSumOfPurchases();
        mostPopularProducts();
        return "q";
    }

    private void addNewProductsToArrays () throws NumberFormatException {
        splitUserLineByParts();

        if (partsLength == 3) {
            String productName = parts[0];
            Integer productCost = 0;
            Integer productCount = 0;
            try {
                productCost = Integer.parseInt(parts[1]);
                productCount = Integer.parseInt(parts[2]);
            }
            catch (NumberFormatException ex) {
                System.out.println("Wrong Number Format! Retry");
                System.out.println(ex.getMessage());
            }
            boolean productAlreadyExists = false;
            for (int i=0; i<=count; i++) {
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

    private String[] splitUserLineByParts() {
        parts = s.split(" ");
        partsLength = parts.length;
        if (partsLength != 3) {
            System.out.println("Wrong number of arguments! Retry");
        }
        return parts;
    }

    private void sortCheckoutList() {
        System.arraycopy(arrayOfProducts,0, sortedProducts, 0, maxProducts);
        Arrays.sort(sortedProducts, (o1, o2) -> {
            int res = String.CASE_INSENSITIVE_ORDER.compare(o1, o2);
            if (res == 0) {
                res = o1.compareTo(o2);
            }
            return res;
        });
        System.out.println("Sorted Products:");
        System.out.println(Arrays.toString(sortedProducts));
    }

    private void calcSumOfPurchases() {
        long sum = 0;
        for (int i = 0; i<count; i++) {
            sum += arrayOfCosts[i] * arrayOfCounts[i];
        }
        System.out.println("Sum of purchases: " + sum);
    }

    private  void mostPopularProducts() {
        for (int j = 0; j<3; j++) {
            int indexOfMostPopolarProduct = 0;
            for (int i = 0; i<count; i++) {
                if (arrayOfCounts[i] > arrayOfCounts[indexOfMostPopolarProduct]) {
                    indexOfMostPopolarProduct = i;
                }
            }
            System.out.println("Most popular product is: " + arrayOfProducts[indexOfMostPopolarProduct]);
            arrayOfProducts[indexOfMostPopolarProduct] = null;
            arrayOfCounts[indexOfMostPopolarProduct] = 0;
            arrayOfCosts[indexOfMostPopolarProduct] = 0;
        }
    }
}

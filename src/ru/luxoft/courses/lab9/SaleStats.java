package ru.luxoft.courses.lab9;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class SaleStats {

    TreeMap<String, TreeMap<String, Integer>> clients = new TreeMap<>();
    private String userStatString = "";
    private String[] parts;
    private int partsLength;
    private String clientName;
    private String productName;
    private Integer productCount;

    public String addSale() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter BuyerName, Product and Quantity, separated by spaces, exit command to cancel");
        while (true) {
            userStatString = sc.nextLine();
            if ("exit".equals(userStatString)) {
                return userStatString;
            }
            checkUserData();
        }
    }

    private void checkUserData() throws NumberFormatException {
        resetUserData();
        splitUserLineByParts();
        if (partsLength == 3) {
            clientName = parts[0];
            productName = parts[1];
            try {
                productCount = Integer.parseInt(parts[2]);
            } catch (NumberFormatException ex) {
                System.out.println("Wrong Number Format! Retry");
                System.out.println(ex.getMessage());
            }
            addNewSaleToTreeMap();
        }
    }

    private void resetUserData() {
        clientName = "";
        productName = "";
        productCount = 0;
    }

    private String[] splitUserLineByParts() {
        parts = userStatString.split(" ");
        partsLength = parts.length;
        if (partsLength != 3) {
            System.out.println("Wrong number of arguments! Retry");
        }
        return parts;
    }

    private void addNewSaleToTreeMap() {
        clients.putIfAbsent(clientName, new TreeMap<>());
        TreeMap<String, Integer> temp = clients.get(clientName);
        temp.putIfAbsent(productName, 0);
        final int count = productCount;
        temp.computeIfPresent(productName, (k, v) -> v + count);
    }

    public void showSaleStats() {
        for (Map.Entry<String, TreeMap<String, Integer>> entry : clients.entrySet()) {
            String key = entry.getKey();
            TreeMap<String, Integer> value = entry.getValue();
            System.out.println("Buyer Name: " + key);
            for (Map.Entry<String, Integer> product : value.entrySet()) {
                String keyProduct = product.getKey();
                Integer valueProduct = product.getValue();
                System.out.println("Product: " + keyProduct + " : " + valueProduct);
            }
        }
    }


}





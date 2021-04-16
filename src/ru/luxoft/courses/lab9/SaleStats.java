package ru.luxoft.courses.lab9;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class SaleStats {

    TreeMap<String, TreeMap<String, Integer>> clients = new TreeMap<>();

    public String addSale() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter BuyerName, Product and Quantity, separated by spaces, exit command to cancel");
        String userStatString;
        while (true) {
            userStatString = sc.nextLine();
            if ("exit".equals(userStatString)) {
                return userStatString;
            }
            checkUserData(userStatString);
        }
    }

    private void checkUserData(String userStatString) throws NumberFormatException {
        String clientName = "";
        String productName = "";
        Integer productCount = 0;
        String[] parts = splitUserLineByParts(userStatString);
        if (parts.length == 3) {
            clientName = parts[0];
            productName = parts[1];
            try {
                productCount = Integer.parseInt(parts[2]);
                addNewSaleToTreeMap(clientName, productName, productCount);
            } catch (NumberFormatException ex) {
                System.out.println("Wrong Number Format! Retry");
                System.out.println(ex.getMessage());
            }
        }
    }

    private String[] splitUserLineByParts(String userStatString) {
        String[] parts = userStatString.split(" ");
        if (parts.length != 3) {
            System.out.println("Wrong number of arguments! Retry");
        }
        return parts;
    }

    private void addNewSaleToTreeMap(String clientName, String productName, Integer productCount) {
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





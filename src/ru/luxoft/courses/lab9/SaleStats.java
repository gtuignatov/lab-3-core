package ru.luxoft.courses.lab9;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class SaleStats {

    private final TreeMap<String, TreeMap<String, Integer>> clients = new TreeMap<>();
    private final Scanner sc = new Scanner(System.in);

    public String addSale() {
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
        String[] parts = splitUserLineByParts(userStatString);
        if (parts.length == 3) {
            try {
                addNewSaleToTreeMap(parts[0], parts[1], Integer.parseInt(parts[2]));
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
            System.out.println("Buyer Name: " + entry.getKey());
            for (Map.Entry<String, Integer> product : entry.getValue().entrySet()) {
                System.out.println("Product: " + product.getKey() + " : " + product.getValue());
            }
        }
    }
}

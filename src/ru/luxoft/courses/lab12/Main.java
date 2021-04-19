package ru.luxoft.courses.lab12;

import java.util.Scanner;

public class Main {

    private static boolean dbCreated = false;

    public static void main(String[] args) throws Exception {
        System.out.println("Products DB");
        String userInput = "";
        while (!"exit".equals(userInput)) {
            userInput = userConsoleInput();
        }
        System.out.println("Program Exit");
    }

    private static String userConsoleInput() throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter | create | add ProductDescription Rate Quantity | remove ID | exit separated by spaces");
        String s = sc.nextLine();
        if ("exit".equals(s)) {
            return s;
        }
        return userCommandSelector(s);
    }

    private static String userCommandSelector(String s) throws Exception{
        String[] parts = s.split(" ");
        switch (parts[0]) {
            case "create":
                if (!dbCreated) {
                    productsCreateOperations();
                    dbCreated = true;
                }
                else {
                    System.out.println("Initial DB is created already");
                }
                break;
            case "add":
                addNewProductToDb(parts);
                break;
            case "remove":
                removeOneProductById(parts);
        }
        return parts[0];
    }

    private static void removeOneProductById(String[] parts) throws Exception {
        System.out.println("Remove One Product By ID");
        try {
            Integer id = Integer.valueOf(parts[1]);
            ProductDAO dao = new ProductDAO();
            dao.removeProduct(id);
        }
        catch (NumberFormatException ex) {
            System.out.println("ID Error! Please Enter Integer ID Number");
        }
    }

    private static void addNewProductToDb(String[] parts) throws Exception {
        ProductDAO dao = new ProductDAO();
        System.out.println("Adding New Product To DB");
        try {
            int id = dao.getNextID();
            String productDesc = parts[1];
            Float rate = Float.valueOf(parts[2]);
            Integer quantity = Integer.valueOf(parts[3]);
            Product product = new Product(id, productDesc, rate, quantity);
            dao.addProduct(product);
        }
        catch (NumberFormatException ex) {
            System.out.println("Add product Parameters error!");
            System.out.println("Please enter add ProductDescription Rate Quantity");
        }
    }

    private static void productsCreateOperations() throws Exception{
        Product product1 = new Product(1, "Bread", 1.0f, 100);
        Product product2 = new Product(2, "Milk", 2.5f, 200);
        Product product3 = new Product(3, "Meat", 15.8f, 350);
        Product product4 = new Product(4, "Soap", 100.0f, 1500);
        Product product5 = new Product(5, "Silk", 4058.3f, 10000);
        Product product6 = new Product(6, "Fruits", 2.0f, 200);
        ProductDAO dao = new ProductDAO();
        System.out.println("Create products in DB");
        try {
            dao.addProduct(product1);
            dao.addProduct(product2);
            dao.addProduct(product3);
            dao.addProduct(product4);
            dao.addProduct(product5);
            dao.addProduct(product6);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Set some changes for products in DB");
        try {
            dao.setProduct(product1);
            dao.setProduct(product3);
            dao.setProduct(product5);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Get Products from DB by ID");
        try {
            System.out.println(dao.getProductById(1));
            System.out.println(dao.getProductById(2));
            System.out.println(dao.getProductById(3));
            System.out.println(dao.getProductById(4));
            System.out.println(dao.getProductById(5));
            System.out.println(dao.getProductById(6));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

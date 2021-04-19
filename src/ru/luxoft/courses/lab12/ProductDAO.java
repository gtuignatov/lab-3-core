package ru.luxoft.courses.lab12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private final String JDBC_CLASS_DRIVER = "org.h2.Driver";
    private final String JDBC_CONNECTION_PATH = "jdbc:h2:D:\\Ignatov\\Docs\\Java2\\MyLabs\\h2db\\test";
    private final String JDBC_USERNAME = "sa";
    private final String JDBC_PASSWORD = "";

    private Connection getConnection() throws Exception {
        Connection retConnection = null;
        try {
            Class.forName(JDBC_CLASS_DRIVER).newInstance();
            return DriverManager.getConnection(JDBC_CONNECTION_PATH, JDBC_USERNAME, JDBC_PASSWORD);
        }
        catch (Exception ex) {
            System.out.println("Connection Error! " + ex.toString());
        }
        return retConnection;
    }

    public List<Product> getProductById(int id) throws Exception {
        List<Product> products = new ArrayList<Product>();
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("Select description, rate, quantity " +
                    "From products where id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Product product = null;
            while (resultSet.next()) {
                product = new Product(id, resultSet.getString(1),
                        resultSet.getFloat(2), resultSet.getInt(3));
                products.add(product);
            }
            resultSet.close();
            connection.close();
        }
        catch (Exception ex) {
            System.out.println("Get Product By Id Error! " + ex.toString());
        }
        return products;
    }

    public void addProduct(Product product) throws Exception {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("Insert into products " +
                    "(id, description, rate, quantity) " +
                    "values (?, ?, ?, ?)");
            statement.setInt(1, product.getId());
            statement.setString(2, product.getDescription());
            statement.setFloat(3, product.getRate());
            statement.setInt(4, product.getQuantity());
            statement.executeUpdate();
            connection.close();
        }
        catch (Exception ex) {
            System.out.println("Add Product Error! " + ex.toString());
        }
    }

    public void setProduct(Product product) throws Exception {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("Update products  " +
                    "Set description=?, rate=?, quantity=? Where id=?");
            statement.setString(1, product.getDescription());
            statement.setFloat(2, product.getRate());
            statement.setInt(3, product.getQuantity());
            statement.setInt(4, product.getId());
            statement.executeUpdate();
            connection.close();
        }
        catch (Exception ex) {
            System.out.println("Set Product Error! " + ex.toString());
        }
    }

    public void removeProduct(int id) throws Exception {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("Delete from products " +
                    "Where id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.close();
        }
        catch (Exception ex) {
            System.out.println("Remove Product Error! " + ex.toString());
        }
    }

    public int getNextID() throws Exception {
        int nextIdValue = 0;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("Select max(id) From products");
            ResultSet resultSet = statement.executeQuery();
            String nextId = "";
            while (resultSet.next()) {
                nextId = resultSet.getString(1);
            }
            nextIdValue = Integer.valueOf(nextId) + 1;
            System.out.println("New ID " + nextIdValue);
            resultSet.close();
            connection.close();
        }
        catch (Exception ex) {
            System.out.println("Get Next ID Error! " + ex.toString());
        }
        return nextIdValue;
    }
}

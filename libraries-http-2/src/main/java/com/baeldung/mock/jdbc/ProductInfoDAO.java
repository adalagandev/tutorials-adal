package com.baeldung.mock.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;

public class ProductInfoDAO {
    private DataSource dataSource;

    public ProductInfoDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public Connection getDBConnection(){
        String jdbcURL = "jdbc:https://test-url-only";
        String username = "sa";
        String password = "1234";
        try {
            return DriverManager.getConnection(jdbcURL, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public boolean createProduct( String name, String description, double price, String upc){
        return true;
    }
    public ProductInfo getProductInfo(UUID prodId) throws Exception {
        Connection con = null;
        ProductInfo p = null;
        System.out.println("Got Mocked");
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement prepStatement = conn
                .prepareStatement("SELECT product_id, name, description, price FROM product WHERE product_id = ?");
            prepStatement.setString(1, prodId.toString());
            ResultSet rs = prepStatement.executeQuery();

            while(rs.first()){
                p = new ProductInfo(
                    UUID.fromString(rs.getString("product_id")),
                    rs.getString("description"),
                    rs.getString("name"),
                    rs.getDouble("price")
                );
            }
            return p;

        } catch (SQLException e) {
            throw new Exception(e);
        }finally {
            if(con!=null){
                con.close();

            }
        }
    }
    public void delete(UUID prodId) throws Exception {
        Connection con = null;
        try {
            Connection conn = getDBConnection();
            PreparedStatement stmt = conn
                .prepareStatement("DELETE FROM product WHERE product_id = ?");
            stmt.setString(1, prodId.toString());
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new Exception(e);
        }finally{
            if(con!=null){
                con.close();

            }
        }
    }

}

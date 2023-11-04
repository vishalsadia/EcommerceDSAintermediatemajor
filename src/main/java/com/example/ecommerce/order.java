package com.example.ecommerce;

import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class order {
    public static boolean placeOrder(Customer customer, Product prod)

    {
        String groupOrderId = "select max(group_order_id)+1 id from orders";
        dBConnection dBconnection = new dBConnection();
        try {
            ResultSet rs = dBconnection.getQueryTable(groupOrderId);
            if (rs.next()) {
                String placeOrder = "insert into orders(group_order_id, customer_id, product_id) values("+ rs.getInt("id")+","+customer.getId()+"," + prod.getId()+ ")";
                return dBconnection.updateDatabase(placeOrder)!=0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static int placeMultipleOrder(Customer customer, ObservableList<Product>productList)

    {
        String group_order_id = "SELECT max(group_order_id) +1 id FROM orders ";
        dBConnection dBconnection = new dBConnection();
        try {
            ResultSet rs = dBconnection.getQueryTable(group_order_id);
            int count = 0;
            if (rs.next()) {
                for (Product product  :productList) {
                    String placeOrder = "INSERT INTO orders(group_order_id, customer_id, product_id)" +
                            " VALUES(" + rs.getInt("id") + " ," + customer.getId() + "," + product.getId() + ")";
                    count+=dBconnection.updateDatabase(placeOrder);
                }
                return count;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
package com.example.ecommerce;

import java.sql.ResultSet;

public class logic {
    public Customer customerLogin(String username,String password){
        String query ="SELECT * FROM customer;WHERE email = '"+username+"'AND password ='"+password+"'";
        dBConnection connection = new dBConnection();
        try {
            ResultSet rs = connection.getQueryTable(query);
            if(rs.next())
                return new Customer(rs.getInt("id"),
                        rs.getString("name"), rs.getString("email"),
                        rs.getString("mobile") );

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        Login login =new Login();
        Customer customer = login.customerLogin("vv@gmail.com", "555");
        System.out.println("Welcome :"+customer.getName());
        //System.out.println(login.customer-login(username "vv@gmail.com",password "555"))
    }

}

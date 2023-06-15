/*
 *Author: Anuj
 *Date:
 *Created with : IntelliJ idea community Edition
 */


package org.example;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DataBaseService {
    Connection connection;


    private void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicdb", "root", "root");
    }

    public Connection getConnectionToDatabase() throws SQLException {
        connect();
        return connection;
    }

    public int checkUser1() throws SQLException, ClassNotFoundException {
        getConnectionToDatabase();
        Scanner sc = new Scanner(System.in);
        int userId = -1;
        Statement st = connection.createStatement();

        System.out.println("1.New User  2.Old User");
        int opt = sc.nextInt();
        try {
            switch (opt) {
                case 1:
                    System.out.println("Welcome here");
                    System.out.println("Enter username");
                    String name = sc.next();
                    System.out.println("Enter password");
                    String pass = sc.next();
                    st.executeUpdate("insert into user (userName,password) values('" + name + "','" + pass + "')");
                    System.out.println("New user account created");
                    System.out.println("---------------JuKeBox-----------");
                    ResultSet rs = st.executeQuery("Select * from user where userName = " + "'" + name + "'" + "and " + "password =" + "'" + pass + "'");
                    if (rs.next()) {
                        userId = rs.getInt(1);
                    }
                    break;


                case 2:
//
                    System.out.println("Enter UserName");
                    String username = sc.next();

                    System.out.println("Enter password");
                    String password = sc.next();
                    String query = "Select * from user where userName = " + "'" + username + "'" + " and " + " password = " + "'" + password + "'";
//                System.out.println(query);
                    ResultSet resultSet = st.executeQuery(query);
                    if (resultSet.next()) {
                        userId = resultSet.getInt(1);
                    } else {
                        System.out.println("user not found");
                        System.out.println("Choose Create New Either Give correct User ID & Password ");
                    }

                    break;


            }
            return userId;
        } catch (InputMismatchException e) {
            System.out.println(e + " Choose only Int data type");
        }
        return userId;
    }
}

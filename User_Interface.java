/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexamination;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Objects.hash;
import java.util.Scanner;
import static javaexamination.User.account_id;

/**
 *
 * @author morrejo_sd2023
 */
public class User_Interface {
    static ArrayList<User> users;
    ArrayList<String> ret;
    Scanner inputUsername = new Scanner(System.in);
    Scanner inputEmail = new Scanner(System.in);
    Scanner inputPass = new Scanner(System.in);
    Scanner inputCon = new Scanner(System.in);
    String username;
    String email;
    String password;
    String confirm;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/exam";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public User_Interface() {
        users = new ArrayList();
    }
    
    public void retrieve() {
        Connection conn;
        Statement stmt;
        users = new ArrayList();
        ret = new ArrayList();
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
//            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
//            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM account";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("--- RETRIEVE ---");
            System.out.println("\n\t\t*** Accounts ***\n");

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                System.out.println(id + "\t\t" + username + "\t\t" + email + "\t\t" + password + "\t\t");
                users.add(new User(id, username, email, password));
            }
            if (users.size() == 0) {
                account_id = 1;
            } else {
                account_id = users.get(users.size() - 1).getId();

            }
//            STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }
    
    public void register() {
        System.out.println("\nREGISTRATION\n");
        System.out.print("Username : ");
        username = inputUsername.next();
        while (!Check.isString(username)) {
            System.out.println("Username containing all number.");
            register();
        }
        System.out.print("Email : ");
        email = inputEmail.next();
        while (true) {
            try {
                System.out.print("Password : ");
                password = inputPass.next();
                if (password.length() >= 8) {
                    break;
                } else {
                    throw new PasswordException("Password too short.");
                }
            } catch (PasswordException ex) {
                System.out.println(ex);
            }
        }
        while (true) {
            try {
                System.out.print("Confirm password : ");
                confirm = inputCon.next();
                if (confirm == null ? password == null : confirm.equals(password)) {
                    break;
                } else {
                    throw new PasswordException("Password mismatch.");
                }
            } catch (PasswordException ex) {
                System.out.println(ex);
            }
        }
        Connection conn;
        Statement stmt;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
//            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
//            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "INSERT INTO Account (username, email, password) VALUES ";

            sql += "('" + username + "', '" + email + "', '" + password + "')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            ++account_id;
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        System.out.println("\nRegistered!");
    }
    
    public ArrayList<String> logIn() {
        System.out.println("\nLOG IN\n");
        System.out.print("Username : ");
        username = inputUsername.nextLine();
        System.out.print("Password : ");
        password = inputPass.nextLine();
        for (User user : users) {
            if (password.equals(user.getPassword())) {
                System.out.println("\nYou are logged in!");
                ret.add("true");
                ret.add(user.getUsername());
                return ret;
            } 
        }
        System.out.println("\nPasword did not match!");
        return ret;
    }
}

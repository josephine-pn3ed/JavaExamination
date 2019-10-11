/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexamination;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import static javaexamination.User.account_id;

/**
 *
 * @author morrejo_sd2023
 */
public class Post_Interface {

    ArrayList<Post> posts;
    String post;
    String date;
    DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    Date dateobj = new Date();
    Scanner inputpost = new Scanner(System.in);
    Scanner inputID = new Scanner(System.in);

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/exam";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public Post_Interface() {
        posts = new ArrayList();
    }

    public void retrieve(String username) {
        java.sql.Connection conn;
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

            String sql = "SELECT * FROM Post";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n\t   *** ON RETRIEVE ***\n");
            posts = new ArrayList();

            while (rs.next()) {
                int id = rs.getInt("id");
                int accID = rs.getInt("ACC_ID");
                String fname = rs.getString("post");
                String lname = rs.getString("date");
                System.out.println("Posted by : " + username);
                System.out.println(fname);
                System.out.println("Posted on : " + lname);
                posts.add(new Post(id, accID, fname, lname));
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

    public void create() {
        java.sql.Connection conn;
        Statement stmt;
        String fname, lname, age;
        System.out.print("Post : ");
        fname = inputpost.nextLine();
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
//            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
//            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "INSERT INTO Post (ACC_ID, post, date) VALUES ('" + account_id + "', '" + fname + "', '" + df.format(dateobj) + "')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        System.out.println("Saved!");
    }

    public void update() {
        java.sql.Connection conn;
        Statement stmt;
        String fname = null, lname = null, age = null;
        System.out.print("Enter accoun ID : ");
        int id = inputID.nextInt();

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
//            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
//            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            for (int i = 0; i < posts.size(); i++) {
                if (posts.get(i).getAcc_id() == id) {
                    System.out.print("New post : ");
                    fname = inputpost.nextLine();
                    String sql = "UPDATE Post SET `ACC_ID` = '" + id + "', `post` = '" + fname + "', `date` = '" + posts.get(i).getDate() + "' WHERE `ID` = '" + posts.get(i).getId() + "'";
                    System.out.println(sql);
                    stmt.executeUpdate(sql);
                    System.out.println("Saved!");
                }
            }
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

    public void delete() {
        java.sql.Connection conn;
        Statement stmt;
        String fname = null, lname = null, age = null;
        System.out.print("Enter account ID : ");
        int id = inputID.nextInt();

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
//            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
//            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            for (int i = 0; i < posts.size(); i++) {
                if (posts.get(i).getAcc_id() == id) {
                    String sql = "DELETE FROM Post WHERE `ACC_ID` = '" + id + "'";
                    System.out.println(sql);
                    stmt.executeUpdate(sql);
                    System.out.println("Saved!");
                }
            }
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
}

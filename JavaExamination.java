/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexamination;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author morrejo_sd2023
 */
public class JavaExamination {
    public static void main(String[] args) {
        Scanner input1 = new Scanner(System.in);
        String choice1;
        Scanner input2 = new Scanner(System.in);
        String choice2;
        User_Interface users = new User_Interface();
        Post_Interface posts = new Post_Interface();

        while (true) {
            ArrayList<String> ret = null;
            boolean exit = false;
            System.out.println("\n\t1 -- Register\n\t2 -- Log in\n");
            System.out.print("Enter choice : ");
            choice1 = input1.nextLine();
            if ("1".equals(choice1)) {
                users.retrieve();
                users.register();
            } else if ("2".equals(choice1)) {
                users.retrieve();
                try{
                    ret  = users.logIn();
                    if (ret.get(0) == "true") {
                        while (exit == false) {
                            System.out.println("\nHi " + ret.get(1) + "!\n");
                            System.out.println("1 -- create\n2 -- retrieve\n3 -- update\n4 -- delete\n5 -- logout\n");
                            System.out.print("Enter choice : ");
                            choice2 = input2.nextLine();
                            switch(choice2) {
                                case "1":
                                    posts.create();
                                    break;
                                case "2":
                                    posts.retrieve(ret.get(1));
                                    break;
                                case "3":
                                    posts.update();
                                    break;
                                case "4":
                                    posts.delete();
                                    break;
                                case "5":
                                    exit = true;
                                    break;
                            }
                        }
                    }
                }
                catch (NullPointerException e) {
                    System.out.println("Null!");
                }
            }
        }

    }

}

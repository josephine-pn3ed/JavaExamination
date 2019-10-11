/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexamination;

/**
 *
 * @author morrejo_sd2023
 */
public class User {
    private String username;
    private String email;
    private String password;
    private String confirm;
    static int account_id = 1;
    private int id = account_id;

    public User(int id, String username, String email, String password, String confirm) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirm = confirm;
    }

    User(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirm = confirm;    }
    
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
    
}

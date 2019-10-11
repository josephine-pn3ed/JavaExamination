/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexamination;

import static javaexamination.User.account_id;

/**
 *
 * @author morrejo_sd2023
 */
public class Post {
    private String post;
    private String date;
    private int acc_id = account_id;
    private int id;

    public Post(int id,int acc_id, String post, String date) {
        this.acc_id = acc_id;
        this.id = id;
        this.post = post;
        this.date = date;
    }


    public int getId() {
        return id;
    }
    
    public int getAcc_id() {
        return acc_id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}

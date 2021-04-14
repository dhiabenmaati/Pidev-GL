/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author houssem
 */
public class comment_blog {
    private int id ;
    private int id_blog_id ;
    private String comment ;
    private Date date ;
    private int signaler ;
    private int user_id ;

    public comment_blog() {
    }

    public comment_blog(int id, int id_blog_id, String comment, Date date, int signaler, int user_id) {
        this.id = id;
        this.id_blog_id = id_blog_id;
        this.comment = comment;
        this.date = date;
        this.signaler = signaler;
        this.user_id = user_id;
    }
    
    public comment_blog(int id_blog_id, String comment, Date date, int signaler, int user_id) {
        this.id_blog_id = id_blog_id;
        this.comment = comment;
        this.date = date;
        this.signaler = signaler;
        this.user_id = user_id;
    }
        public comment_blog(int id_blog_id, String comment, Date date, int user_id) {
        this.id_blog_id = id_blog_id;
        this.comment = comment;
        this.date = date;
        this.user_id = user_id;
    }
      public comment_blog(String comment) {
        this.comment = comment;
    }
      

    public int getId() {
        return id;
    }


    public int getId_blog_id() {
        return id_blog_id;
    }

    public void setId_blog_id(int id_blog_id) {
        this.id_blog_id = id_blog_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSignaler() {
        return signaler;
    }

    public void setSignaler(int signaler) {
        this.signaler = signaler;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    
}

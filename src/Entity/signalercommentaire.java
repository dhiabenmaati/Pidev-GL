/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author houssem
 */
public class signalercommentaire {
    private int id;
    private int user_id;
    private int comment_blog_id;

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getComment_blog_id() {
        return comment_blog_id;
    }

    public void setComment_blog_id(int comment_blog_id) {
        this.comment_blog_id = comment_blog_id;
    }

    public signalercommentaire() {
    }

    public signalercommentaire(int user_id, int comment_blog_id) {
        this.user_id = user_id;
        this.comment_blog_id = comment_blog_id;
    }
    
}

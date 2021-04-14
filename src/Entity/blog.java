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
public class blog {
    private int id;
    private String title;
    private String description;
    private Date date;
    private String image;
    private Date updated_at;
    private int valid;
    private int user_id;

    public blog() {
    }

    public blog(String title, String description, Date date, String image, Date updated_at, int valid, int user_id) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.image = image;
        this.updated_at = updated_at;
        this.valid = valid;
        this.user_id = user_id;
    }
        public blog(String title, String description, Date date, String image, Date updated_at, int user_id) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.image = image;
        this.updated_at = updated_at;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "blog{" + "id=" + id + ", title=" + title + ", description=" + description + ", date=" + date + ", image=" + image + ", updated_at=" + updated_at + ", valid=" + valid + ", user_id=" + user_id + '}';
    }
    
    
    
}

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
public class reclamation {
    private int id;
    private String desc_rec;
    private String status_rec;
    private Date progress_at;
    private Date valid_at ;
    private Date reclamation_at;
    private String type;
    private int user_id ;

    public reclamation() {
    }

    public reclamation(int id, String desc_rec, String status_rec, Date progress_at, Date valid_at, Date reclamation_at, String type, int user_id) {
        this.id = id;
        this.desc_rec = desc_rec;
        this.status_rec = status_rec;
        this.progress_at = progress_at;
        this.valid_at = valid_at;
        this.reclamation_at = reclamation_at;
        this.type = type;
        this.user_id = user_id;
    }
        public reclamation(String desc_rec, String status_rec, Date progress_at, Date valid_at, Date reclamation_at, String type, int user_id) {
        this.desc_rec = desc_rec;
        this.status_rec = status_rec;
        this.progress_at = progress_at;
        this.valid_at = valid_at;
        this.reclamation_at = reclamation_at;
        this.type = type;
        this.user_id = user_id;
    }
     
        public reclamation(String desc_rec, String type) {
        this.desc_rec = desc_rec;
        this.type = type;
    }
        public reclamation(String desc_rec, String status_rec,String type) {
        this.desc_rec = desc_rec;
        this.status_rec = status_rec;
        this.type = type;
    }
        public reclamation(String desc_rec, Date reclamation_at, String type, int user_id) {
        this.desc_rec = desc_rec;
        this.reclamation_at = reclamation_at;
        this.type = type;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc_rec() {
        return desc_rec;
    }

    public void setDesc_rec(String desc_rec) {
        this.desc_rec = desc_rec;
    }

    public String getStatus_rec() {
        return status_rec;
    }

    public void setStatus_rec(String status_rec) {
        this.status_rec = status_rec;
    }

    public Date getProgress_at() {
        return progress_at;
    }

    public void setProgress_at(Date progress_at) {
        this.progress_at = progress_at;
    }

    public Date getValid_at() {
        return valid_at;
    }

    public void setValid_at(Date valid_at) {
        this.valid_at = valid_at;
    }

    public Date getReclamation_at() {
        return reclamation_at;
    }

    public void setReclamation_at(Date reclamation_at) {
        this.reclamation_at = reclamation_at;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "reclamation{" + "id=" + id + ", desc_rec=" + desc_rec + ", status_rec=" + status_rec + ", progress_at=" + progress_at + ", valid_at=" + valid_at + ", reclamation_at=" + reclamation_at + ", type=" + type + ", user_id=" + user_id + '}';
    }


    
}

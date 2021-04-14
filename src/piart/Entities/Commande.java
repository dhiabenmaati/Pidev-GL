/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Entities;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Amine
 */
public class Commande {
    private int id;
    private int livreur_id;
    private int user_id;
    private Date data_creer;
    private Date date_expedirer;
    private int status;

    public Commande(int user_id, Date data_creer, int status) {
        this.user_id = user_id;
        this.data_creer = data_creer;
        this.status = status;
    }

    public Commande(int id, int livreur_id, int user_id, Date data_creer, Date date_expedirer, int status) {
        this.id = id;
        this.livreur_id = livreur_id;
        this.user_id = user_id;
        this.data_creer = data_creer;
        this.date_expedirer = date_expedirer;
        this.status = status;
    }

    
    
    public int getId() {
        return id;
    }

    public int getLivreur_id() {
        return livreur_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public Date getData_creer() {
        return data_creer;
    }

    public Date getDate_expedirer() {
        return date_expedirer;
    }

    public int getStatus() {
        return status;
    }

    public void setLivreur_id(int livreur_id) {
        this.livreur_id = livreur_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setData_creer(Date data_creer) {
        this.data_creer = data_creer;
    }

    public void setDate_expedirer(Date date_expedirer) {
        this.date_expedirer = date_expedirer;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commande other = (Commande) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.livreur_id != other.livreur_id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.data_creer, other.data_creer)) {
            return false;
        }
        if (!Objects.equals(this.date_expedirer, other.date_expedirer)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", livreur_id=" + livreur_id + ", user_id=" + user_id + ", data_creer=" + data_creer + ", date_expedirer=" + date_expedirer + ", status=" + status + '}';
    }
    
}

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
public class reponse_reclamation {
    private int id;
    private int id_rec_id;
    private String desc_reponse_rec ;

    public reponse_reclamation() {
    }

    public reponse_reclamation(int id_rec_id, String desc_reponse_rec) {
        this.id_rec_id = id_rec_id;
        this.desc_reponse_rec = desc_reponse_rec;
    }
    public reponse_reclamation(String desc_reponse_rec) {
        this.desc_reponse_rec = desc_reponse_rec;
    }
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "reponse_reclamation{" + "id=" + id + ", id_rec_id=" + id_rec_id + ", desc_reponse_rec=" + desc_reponse_rec + '}';
    }


    public int getId_rec_id() {
        return id_rec_id;
    }

    public void setId_rec_id(int id_rec_id) {
        this.id_rec_id = id_rec_id;
    }

    public String getDesc_reponse_rec() {
        return desc_reponse_rec;
    }

    public void setDesc_reponse_rec(String desc_reponse_rec) {
        this.desc_reponse_rec = desc_reponse_rec;
    }
    
}

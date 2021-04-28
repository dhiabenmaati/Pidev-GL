/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;

/**
 *
 * @author 21624
 */
public class Categorie {
       private int id;
    private String nom_cat;
    private String desc_cat;
    private String confirmation;

    public Categorie() {
    }

    public Categorie(int id, String nom_cat, String desc_cat, String confirmation) {
        this.id = id;
        this.nom_cat = nom_cat;
        this.desc_cat = desc_cat;
        this.confirmation = confirmation;
    }

    public Categorie(String nom_cat, String desc_cat, String confirmation) {
        this.nom_cat = nom_cat;
        this.desc_cat = desc_cat;
        this.confirmation = confirmation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_cat() {
        return nom_cat;
    }

    public void setNom_cat(String nom_cat) {
        this.nom_cat = nom_cat;
    }

    public String getDesc_cat() {
        return desc_cat;
    }

    public void setDesc_cat(String desc_cat) {
        this.desc_cat = desc_cat;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
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
        final Categorie other = (Categorie) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom_cat, other.nom_cat)) {
            return false;
        }
        if (!Objects.equals(this.desc_cat, other.desc_cat)) {
            return false;
        }
        if (!Objects.equals(this.confirmation, other.confirmation)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nom_cat=" + nom_cat + ", desc_cat=" + desc_cat + ", confirmation=" + confirmation + '}';
    }

    
}

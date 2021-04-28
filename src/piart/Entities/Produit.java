/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Entities;

import java.util.Objects;

/**
 *
 * @author Amine
 */
public class Produit {
    private int id;
    private String nom_prod;
    private String desc_prod;
    private Double prix_prod;
    private int qte_prod;
    private String image_prod;
    private boolean valid_prod;

    public Produit() {
        
    }
    
    public Produit(int id, String nom_prod, String desc_prod, Double prix_prod, int qte_prod, String image_prod, boolean valid_prod) {
        this.id = id;
        this.nom_prod = nom_prod;
        this.desc_prod = desc_prod;
        this.prix_prod = prix_prod;
        this.qte_prod = qte_prod;
        this.image_prod = image_prod;
        this.valid_prod = valid_prod;
    }
    
    public Produit(String nom_prod, String desc_prod, Double prix_prod, int qte_prod, String image_prod, boolean valid_prod) {
        this.nom_prod = nom_prod;
        this.desc_prod = desc_prod;
        this.prix_prod = prix_prod;
        this.qte_prod = qte_prod;
        this.image_prod = image_prod;
        this.valid_prod = valid_prod;
    }

    public int getId() {
        return id;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public String getDesc_prod() {
        return desc_prod;
    }

    public Double getPrix_prod() {
        return prix_prod;
    }

    public int getQte_prod() {
        return qte_prod;
    }

    public String getImage_prod() {
        return image_prod;
    }

    public boolean isValid_prod() {
        return valid_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public void setDesc_prod(String desc_prod) {
        this.desc_prod = desc_prod;
    }

    public void setPrix_prod(Double prix_prod) {
        this.prix_prod = prix_prod;
    }

    public void setQte_prod(int qte_prod) {
        this.qte_prod = qte_prod;
    }

    public void setImage_prod(String image_prod) {
        this.image_prod = image_prod;
    }

    public void setValid_prod(boolean valid_prod) {
        this.valid_prod = valid_prod;
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
        final Produit other = (Produit) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.qte_prod != other.qte_prod) {
            return false;
        }
        if (this.valid_prod != other.valid_prod) {
            return false;
        }
        if (!Objects.equals(this.nom_prod, other.nom_prod)) {
            return false;
        }
        if (!Objects.equals(this.desc_prod, other.desc_prod)) {
            return false;
        }
        if (!Objects.equals(this.image_prod, other.image_prod)) {
            return false;
        }
        if (!Objects.equals(this.prix_prod, other.prix_prod)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom_prod=" + nom_prod + ", desc_prod=" + desc_prod + ", prix_prod=" + prix_prod + ", qte_prod=" + qte_prod + ", image_prod=" + image_prod + ", valid_prod=" + valid_prod + '}';
    }
   
}

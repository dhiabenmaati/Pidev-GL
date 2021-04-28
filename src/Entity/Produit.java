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
public class Produit {

    public static Object getSelectionModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int id;
    private String nom_prod;
    private String desc_prod;
    private int prix_prod;
    private int qte_prod;
    private String image_prod;
    private int valid_prod;
    private int categorie_id;
 

    public Produit() {
    }

    public Produit(int id, String nom_prod, String desc_prod, int prix_prod, int qte_prod, String image_prod, int valid_prod, int categorie_id) {
        this.id = id;
        this.nom_prod = nom_prod;
        this.desc_prod = desc_prod;
        this.prix_prod = prix_prod;
        this.qte_prod = qte_prod;
        this.image_prod = image_prod;
        this.valid_prod = valid_prod;
        this.categorie_id = categorie_id;
        
    }

  

    public Produit(String nom_prod, String desc_prod, int prix_prod, int qte_prod, String image_prod, int valid_prod, int categorie_id) {
        this.nom_prod = nom_prod;
        this.desc_prod = desc_prod;
        this.prix_prod = prix_prod;
        this.qte_prod = qte_prod;
        this.image_prod = image_prod;
        this.valid_prod = valid_prod;
        this.categorie_id = categorie_id;
       
    
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public String getDesc_prod() {
        return desc_prod;
    }

    public void setDesc_prod(String desc_prod) {
        this.desc_prod = desc_prod;
    }

    public int getPrix_prod() {
        return prix_prod;
    }

    public void setPrix_prod(int prix_prod) {
        this.prix_prod = prix_prod;
    }

    public int getQte_prod() {
        return qte_prod;
    }

    public void setQte_prod(int qte_prod) {
        this.qte_prod = qte_prod;
    }

    public String getImage_prod() {
        return image_prod;
    }

    public void setImage_prod(String image_prod) {
        this.image_prod = image_prod;
    }

    public int getValid_prod() {
        return valid_prod;
    }

    public void setValid_prod(int valid_prod) {
        this.valid_prod = valid_prod;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
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
        if (this.prix_prod != other.prix_prod) {
            return false;
        }
        if (this.qte_prod != other.qte_prod) {
            return false;
        }
        if (this.valid_prod != other.valid_prod) {
            return false;
        }
        if (this.categorie_id != other.categorie_id) {
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
        return true;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom_prod=" + nom_prod + ", desc_prod=" + desc_prod + ", prix_prod=" + prix_prod + ", qte_prod=" + qte_prod + ", image_prod=" + image_prod + ", valid_prod=" + valid_prod + ", categorie_id=" + categorie_id + '}';
    }
}

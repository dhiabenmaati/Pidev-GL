/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Entities;

/**
 *
 * @author Amine
 */
public class DetailCommande {
    private int id;
    private int commande_id;
    private int produit_id;
    private int qte;

    public DetailCommande(int id, int commande_id, int produit_id, int qte) {
        this.id = id;
        this.commande_id = commande_id;
        this.produit_id = produit_id;
        this.qte = qte;
    }

    public DetailCommande(int commande_id, int produit_id, int qte) {
        this.commande_id = commande_id;
        this.produit_id = produit_id;
        this.qte = qte;
    }

    public int getId() {
        return id;
    }

    public int getCommande_id() {
        return commande_id;
    }

    public int getProduit_id() {
        return produit_id;
    }

    public int getQte() {
        return qte;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public void setQte(int qte) {
        this.qte = qte;
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
        final DetailCommande other = (DetailCommande) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.commande_id != other.commande_id) {
            return false;
        }
        if (this.produit_id != other.produit_id) {
            return false;
        }
        if (this.qte != other.qte) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetailCommande{" + "id=" + id + ", commande_id=" + commande_id + ", produit_id=" + produit_id + ", qte=" + qte + '}';
    }
    
}

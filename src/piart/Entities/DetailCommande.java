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
    private Commande commande;
    private Produit produit;
    private int qte;

    public DetailCommande(int id, Commande commande, Produit produit, int qte) {
        this.id = id;
        this.commande = commande;
        this.produit = produit;
        this.qte = qte;
    }

    public DetailCommande(Commande commande, Produit produit, int qte) {
        this.commande = commande;
        this.produit = produit;
        this.qte = qte;
    }

    public int getId() {
        return id;
    }

    public Commande getCommande() {
        return commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public int getQte() {
        return qte;
    }

    public void setCommande_id(Commande commande) {
        this.commande = commande;
    }

    public void setProduit_id(Produit produit) {
        this.produit = produit;
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
        if (this.commande != other.commande) {
            return false;
        }
        if (this.produit != other.produit) {
            return false;
        }
        if (this.qte != other.qte) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetailCommande{" + "id=" + id + ", commande=" + commande + ", produit=" + produit + ", qte=" + qte + '}';
    }

    
}

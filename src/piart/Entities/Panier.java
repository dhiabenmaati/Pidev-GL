package piart.Entities;

import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amine
 */
public class Panier {
    private String nom;
    private double prix;
    private int qte;
    private double totale;
    private int id_prod;

    public Panier(int id_prod, String nom, Double prix, int qte) {
        this.nom = nom;
        this.prix = prix;
        this.qte = qte;
        this.totale = calcTotalePerItem();
        this.id_prod = id_prod;
    }
    
    public double calcTotalePerItem() {
        return this.prix * this.qte;
    }

    public String getNom() {
        return nom;
    }

    public int getID_Prod() {
        return this.id_prod;
    }
    
    public double getPrix() {
        return prix;
    }

    public int getQte() {
        return qte;
    }

    public double getTotale() {
        return totale;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setTotale(double totale) {
        this.totale = totale;
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
        final Panier other = (Panier) obj;
        if (Double.doubleToLongBits(this.prix) != Double.doubleToLongBits(other.prix)) {
            return false;
        }
        if (this.qte != other.qte) {
            return false;
        }
        if (Double.doubleToLongBits(this.totale) != Double.doubleToLongBits(other.totale)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Panier{" + "nom=" + nom + ", prix=" + prix + ", qte=" + qte + ", totale=" + totale + '}';
    }
    
}

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
    private String nom_prod;
    private Double prix;
    private int qte;
    private Double totale;

    public DetailCommande(Commande commande, String nom_prod, Double prix, int qte) {
        this.commande = commande;
        this.nom_prod = nom_prod;
        this.prix = prix;
        this.qte = qte;
        this.totale = prix * qte;
    }

    public DetailCommande(String nom_prod, Double prix, int qte) {
        this.nom_prod = nom_prod;
        this.prix = prix;
        this.qte = qte;
        this.totale = prix * qte;
    }

    public int getId() {
        return id;
    }

    public Commande getCommande() {
        return commande;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public Double getPrix() {
        return prix;
    }

    public int getQte() {
        return qte;
    }

    public Double getTotale() {
        return totale;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setTotale(Double totale) {
        this.totale = totale;
    }

    @Override
    public String toString() {
        return "DetailCommande{" + "id=" + id + ", commande=" + commande + ", nom_prod=" + nom_prod + ", prix=" + prix + ", qte=" + qte + ", totale=" + totale + '}';
    }

        
    
    
}

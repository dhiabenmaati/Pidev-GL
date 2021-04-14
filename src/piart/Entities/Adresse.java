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
public class Adresse {
    private int id;
    private String adresse;
    private int codepostal;
    private String ville;
    private int num_tel;
    private int user_id;

    public Adresse(int id, String adresse, int codepostal, String ville, int num_tel, int user_id) {
        this.id = id;
        this.adresse = adresse;
        this.codepostal = codepostal;
        this.ville = ville;
        this.num_tel = num_tel;
        this.user_id = user_id;
    }

    public Adresse(String adresse, int codepostal, String ville, int num_tel, int user_id) {
        this.adresse = adresse;
        this.codepostal = codepostal;
        this.ville = ville;
        this.num_tel = num_tel;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getCodepostal() {
        return codepostal;
    }

    public String getVille() {
        return ville;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCodepostal(int codepostal) {
        this.codepostal = codepostal;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public void setUser_id(User user) {
        this.user_id = user_id;
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
        final Adresse other = (Adresse) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.codepostal != other.codepostal) {
            return false;
        }
        if (this.num_tel != other.num_tel) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.ville, other.ville)) {
            return false;
        }
        if (!Objects.equals(this.user_id, other.user_id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Adresse{" + "id=" + id + ", adresse=" + adresse + ", codepostal=" + codepostal + ", ville=" + ville + ", num_tel=" + num_tel + ", user=" + user_id + '}';
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Entities;

/**
 *
 * @author alabe
 */
public class Users {

    int id, num_tel;
    String email, password, name , surname;

    public Users(int id, int num_tel, String email, String password, String name, String surname) {
        this.id = id;
        this.num_tel = num_tel;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public Users(int num_tel, String email, String password, String name, String surname) {
        this.num_tel = num_tel;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public Users(String email, String password, String name, String surname) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public Users(int id, int num_tel, String email, String name, String surname) {
        this.id = id;
        this.num_tel = num_tel;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    
    
    public int getId() {
        return this.id;
    }

    public int getNum_tel() {
        return this.num_tel;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", num_tel=" + num_tel + ", email=" + email + ", password=" + password + ", name=" + name + ", surname=" + surname + '}';
    }
   
}

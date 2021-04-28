/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.reclamationService;

/**
 *
 * @author houssem
 */
class ListData {
    private ObservableList<reclamation> reclamations=FXCollections.observableArrayList();

    public ListData() {
        
 reclamationService rService=new reclamationService();
    reclamations=(ObservableList<reclamation>) rService.readAll();
        System.out.println(reclamations);
    }
    
    public ObservableList<reclamation> getReclamationList(){
        return reclamations;
    }
   
}

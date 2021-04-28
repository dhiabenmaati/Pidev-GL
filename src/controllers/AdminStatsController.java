/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entity.Users;
import static Services.mysqlconnect.ConnectDb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author alabe
 */
public class AdminStatsController {
    
    public void start() throws Exception {

        // graph Titles
        
        CategoryAxis xAxis    = new CategoryAxis();
        xAxis.setLabel(" ");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Total");
        BarChart     barChart = new BarChart(xAxis, yAxis);

        // users data
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Users : "+ TotalUsers() );
        dataSeries1.getData().add(new XYChart.Data("Users", TotalUsers()));
        
        // category data
        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("Category : "+ TotalCategory() );
        dataSeries2.getData().add(new XYChart.Data("Category", TotalCategory()));
        
        // produit data
        XYChart.Series dataSeries3 = new XYChart.Series();
        dataSeries3.setName("Produit : "+ TotalProduit() );
        dataSeries3.getData().add(new XYChart.Data("Produit", TotalProduit()));
        
        // enchere data
        XYChart.Series dataSeries4 = new XYChart.Series();
        dataSeries4.setName("Enchere : "+ TotalEnchere() );
        dataSeries4.getData().add(new XYChart.Data("Enchere", TotalEnchere()));

        
        
        
        
        //add total data to the chart
        barChart.getData().add(dataSeries1);
        barChart.getData().add(dataSeries2);
        barChart.getData().add(dataSeries3);
        barChart.getData().add(dataSeries4);
        VBox vbox = new VBox(barChart);
        Scene scene = new Scene(vbox, 400, 200);
    }
    
    // get users data from base
    public int TotalUsers(){
        Connection conn = ConnectDb();
        int TotalUsers = 0 ;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from user");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){   TotalUsers++ ;             }
        } catch (Exception e) { }
        return TotalUsers;
    }
    
        // get category data from base
    public int TotalCategory(){
        Connection conn = ConnectDb();
        int TotalCategory = 0 ;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from categorie");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){   TotalCategory++ ;             }
        } catch (Exception e) { }
        return TotalCategory;
    }
    
            // get Produits data from base
    public int TotalProduit(){
        Connection conn = ConnectDb();
        int TotalProduit = 0 ;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from produit");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){   TotalProduit++ ;             }
        } catch (Exception e) { }
        return TotalProduit;
    }
    
            // get Enchere data from base
    public int TotalEnchere(){
        Connection conn = ConnectDb();
        int TotalEnchere = 0 ;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from enchere");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){   TotalEnchere++ ;             }
        } catch (Exception e) { }
        return TotalEnchere;
    }
    
}

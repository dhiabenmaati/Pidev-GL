/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import piart.Entities.Commande;
import piart.Service.CommandeService;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class ShowCommandeConroller {

    @FXML
    private TableView<Commande> tvCommandes;
    @FXML
    private TableColumn<Commande, Date> colDateCreer;
    @FXML
    private TableColumn<Commande, Date> colDateExpedirer;
    @FXML
    private TableColumn<Commande, Integer> colStatus;
    @FXML
    private CheckBox cbNonLivrer;
    @FXML
    private CheckBox cbLivrer;
    @FXML
    private DatePicker dateDeb;
    @FXML
    private DatePicker DateFin;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnExportPDF;
    @FXML
    private TableColumn<Commande, Integer> colClient;
    @FXML
    private TableColumn<Commande, Integer> colLivreur;

    
    public void initialize() {
        showCommandes();
    }
    
    private void goToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/menu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void showCommandes() {
        CommandeService cs = new CommandeService();
        ObservableList<Commande> commandesList = FXCollections.observableArrayList();
        List<Commande> cmds = new ArrayList(cs.getAllCommandes());
        for(Commande c : cmds) {
            commandesList.add(c);
        }
        colClient.setCellValueFactory(new PropertyValueFactory<>("livreur_id"));
        colLivreur.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colDateExpedirer.setCellValueFactory(new PropertyValueFactory<>("date_expedirer"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDateCreer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Commande, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getData_creer());
            }
        });
        colDateExpedirer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Commande, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDate_expedirer());
            }
        });
        tvCommandes.setItems(commandesList);
    }

    
    public void showCommandesCustom(List<Commande> cmds) {
        CommandeService cs = new CommandeService();
        ObservableList<Commande> commandesList = FXCollections.observableArrayList();
        for(Commande c : cmds) {
            commandesList.add(c);
        }
        colClient.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colLivreur.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colDateExpedirer.setCellValueFactory(new PropertyValueFactory<>("date_expedirer"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDateCreer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Commande, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getData_creer());
            }
        });
        colDateExpedirer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Commande, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDate_expedirer());
            }
        });
        tvCommandes.setItems(commandesList);
    }
    
    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            if (cbLivrer.isSelected()) {
                CommandeService cs = new CommandeService();
                showCommandesCustom(cs.getCommandeByStatus(2));
                
                System.out.println("livre : " + cs.getCommandeByStatus(2).toString());
            }
            if(cbNonLivrer.isSelected()) {
                CommandeService cs = new CommandeService();
                showCommandesCustom(cs.getCommandeByStatus(0));
                System.out.println("non livrer : " + cs.getCommandeByStatus(0).toString());
            }
            if(!cbNonLivrer.isSelected() && !cbLivrer.isSelected())
                showCommandes();
            if(cbNonLivrer.isSelected() && cbLivrer.isSelected())
                showCommandes();
        }
    };
    
    @FXML
    private void LivrerOnly() {
        cbLivrer.setOnAction(event);
    }

    @FXML
    private void NonLivrerOnly() {
        cbNonLivrer.setOnAction(event);
    }

    @FXML
    private void SearchByDate(ActionEvent event) {
        CommandeService cs = new CommandeService();
        showCommandesCustom(cs.getCommandeByDate(dateDeb.getValue(), DateFin.getValue()));
    }

    @FXML
    private void ExporterPDF(ActionEvent event) throws DocumentException  {
        String filename = "C:\\Users\\Amine\\Desktop\\GeneratedPDF\\Commandes.pdf";
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();
            Paragraph p = new Paragraph("Liste Des Commandes");
            PdfPTable table = new PdfPTable(4);
            PdfPCell c1 = new PdfPCell(new Phrase("ID"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Date Créer"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Date Expedirer"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Status"));
            table.addCell(c1);
            CommandeService cs = new CommandeService();
            List<Commande> cmds = cs.getAllCommandes();
            table.setHeaderRows(cmds.size());
            System.out.println(cmds.toString());
            for(Commande cmd : cmds) {
                table.addCell(Integer.toString(cmd.getId()));
                table.addCell(cmd.getData_creer().toString());
                table.addCell(cmd.getDate_expedirer() != null ? cmd.getDate_expedirer().toString() : "-");
                if(cmd.getStatus() == 0)
                    table.addCell("En cours de préparation");
                else if(cmd.getStatus() == 1)
                    table.addCell("En cours de livraison");
                else
                    table.addCell("Livré");
            }
            document.add(p);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(table);
            document.close();
            JOptionPane.showMessageDialog(null, "Votre Fichier PDF est exportée !");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @FXML
    private void getDetailCommande(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/piart/gui/DetailCommande.fxml"));
            Scene scene;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Detail de la commande");
            stage.setScene(scene);
            // Send Parameters
            DetailCommandeController controller = fxmlLoader.getController();
            Commande c = tvCommandes.getSelectionModel().getSelectedItem();
            controller.initData(c);
            stage.show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void goToCommandes(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/showCommande.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    
    @FXML
    private void goToLivreurs(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/UserView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void goToLivraison(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/LivreurPannel.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    
    
}
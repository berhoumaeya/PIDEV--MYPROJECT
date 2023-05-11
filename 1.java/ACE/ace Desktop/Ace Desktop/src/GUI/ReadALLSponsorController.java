/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.sponsor;
import Services.ServiceSponsor;
import com.itextpdf.text.BadElementException;

import java.io.FileNotFoundException;

import java.net.URL;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.TextField;
import javafx.stage.Stage;




import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.DocumentException;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

//import com.itextpdf.text.Document;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.ListView;




/**
 * FXML Controller class
 *
 * @author eyamo
 */
public class ReadALLSponsorController implements Initializable {

    @FXML
    private ListView<sponsor> listview;
    @FXML
    private TextField search;
    
    private ObservableList<sponsor> sponsorList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
     
            

            
            try {
                
          
                
                
                try {
                    ServiceSponsor sc = new ServiceSponsor();
                    List<sponsor> ls = sc.readAll();
                    
                    for (int i = 0; i < ls.size(); i++) {
                        sponsor s = ls.get(i);
                        String text = s.getIntitule() + " - " + s.getDuree_contrat() + " - " + s.getDatdebc()+ " - " + s.getDatefinc() ;
                        listview.getItems().add(s); // add the text to the list view
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(ReadALLSponsorController.class.getName()).log(Level.SEVERE, null, ex);
                    
                    
                }
                //recherche
                ServiceSponsor os = new ServiceSponsor();
                List<sponsor> ls = os.readAll();
                sponsorList.addAll(ls);
                
                listview.setItems(sponsorList);
                
         
                
                
            } catch (SQLException ex) {
                Logger.getLogger(ReadALLSponsorController.class.getName()).log(Level.SEVERE, null, ex);
                
       
            }
              
  
    
    }

   
    @FXML
    private void buthome(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("windowSponsor.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void butmodifier(ActionEvent event) throws IOException {
    sponsor selectedDest = listview.getSelectionModel().getSelectedItem();
        if (selectedDest != null) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("updatesponsor.fxml"));
           Parent root = loader.load();
            UpdateSponsorController mod = loader.getController();
             mod.setSponsor(selectedDest);
           
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }
        
    } 

    @FXML
    private void butsupp(ActionEvent event) {
      
      
      ServiceSponsor ser = new ServiceSponsor();
    sponsor selected = listview.getSelectionModel().getSelectedItem();
    try {
        ser.Delete(selected.getId_sponsor());
        // Remove the selected item from the listview
        listview.getItems().remove(selected);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Suppression d'un sponsor");
        alert.setHeaderText(null);
        alert.setContentText("Le sponsor a été supprimé avec succès");
        alert.showAndWait();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void butajouter(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("addsponsor.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
 
     


    @FXML
    private void buttsearch(ActionEvent event) throws DocumentException, FileNotFoundException, SQLException {
              String query = search.getText().toLowerCase();
    if (query.trim().equals("")) {
        listview.setItems(sponsorList);
    } else {
        ObservableList<sponsor> filteredList = FXCollections.observableArrayList();
        for (sponsor s : sponsorList) {
            if (s.getIntitule().toLowerCase().contains(query) ) {
    filteredList.add(s);
}
        }
        listview.setItems(filteredList);
    }
    }

    @FXML
    private void buttpdf(ActionEvent event) throws FileNotFoundException, DocumentException, SQLException, BadElementException, IOException {
 
  
try{
        ServiceSponsor sponsorService = new ServiceSponsor();
List<sponsor> sponsors = sponsorService.findAllSponsors();

// Create a new PDF document
com.itextpdf.text.Document document = new com.itextpdf.text.Document();
PdfWriter.getInstance(document, new FileOutputStream("sponsors.pdf"));

// Open the document
document.open();

// Create a table with four columns
PdfPTable table = new PdfPTable(4);

// Add table headers
table.addCell("Intitule");
table.addCell("Durée");
table.addCell("Date début");
table.addCell("Date fin");

// Add the information of all sponsors to the table
for (sponsor s : sponsors) {
    table.addCell(s.getIntitule());
    table.addCell(s.getDuree_contrat() + " months");
    table.addCell(s.getDatdebc().toString());
    table.addCell(s.getDatefinc().toString());
}

// Add the table to the document
document.add(table);

// Close the document
document.close(); 

// Show a success message
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Impression réussie");
        alert.setContentText("La liste des sponsors a été imprimée avec succès.");
        alert.showAndWait();
    }
    catch (Exception e) {
        // Show an error message if an exception occurs
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur lors de l'impression");
        alert.setContentText("Une erreur s'est produite lors de l'impression de la liste des sponsors.");
        alert.showAndWait();
  
 

    }


    

    
}

    @FXML
    private void buttrefresh(ActionEvent event) {
             try {
            // retrieve the updated list of data from the database
            ServiceSponsor service = new ServiceSponsor();
            List<sponsor> dataList = service.readAll();

            // update the listview with the new data
            listview.getItems().clear();
            listview.getItems().addAll(((dataList)));

        } catch (SQLException e) {
            // handle the exception
        }

        
        
    }
}

    


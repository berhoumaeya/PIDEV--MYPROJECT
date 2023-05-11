/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Evenement;
import Services.Servicesevent;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author farah
 */
public class AfficherController implements Initializable {

    @FXML
    private ListView<Evenement> listview;
    @FXML
    private Button buttnupdate;
    private ObservableList<Evenement> eventList = FXCollections.observableArrayList();
    @FXML
    private TextField txtresearch;
    @FXML
    private Button seDeconnecter;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            try {
                Servicesevent os = new Servicesevent();
                List<Evenement> ls = os.readAll();

                for (int i = 0; i < ls.size(); i++) {
                    Evenement e = ls.get(i);
                    String text = e.getDuree() + " - " + e.getPrix();
                    listview.getItems().add(e); // add the text to the list view

                }

            } catch (SQLException ex) {
                Logger.getLogger(AfficherController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Servicesevent os = new Servicesevent();
            List<Evenement> ls = os.readAll();
            eventList.addAll(ls);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listview.setItems(eventList);

    }

    @FXML
    private void backbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void update(ActionEvent event) throws IOException {

        Evenement selectedDest = listview.getSelectionModel().getSelectedItem();
        if (selectedDest != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("update.fxml"));
            Parent root = loader.load();
            UpdateController44 mod = loader.getController();
            mod.setEvent(selectedDest);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    private void refresh(ActionEvent event) {

        try {
            // retrieve the updated list of data from the database
            Servicesevent service = new Servicesevent();
            List<Evenement> dataList = service.readAll();

            // update the listview with the new data
            listview.getItems().clear();
            listview.getItems().addAll(dataList);

        } catch (SQLException e) {
            // handle the exception
        }
    }

    @FXML
    private void btnsupprimer(ActionEvent event) {

        Servicesevent ser = new Servicesevent();
        Evenement selected = listview.getSelectionModel().getSelectedItem();
        try {
            ser.Delete(selected.getId_event());
            // Remove the selected item from the listview
            listview.getItems().remove(selected);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression d'un event");
            alert.setHeaderText(null);
            alert.setContentText("L'event a été supprimé avec succès");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void research(ActionEvent event) {
        String query = txtresearch.getText().toLowerCase();
        if (query.trim().equals("")) {
            listview.setItems(eventList);
        } else {
            ObservableList<Evenement> filteredList = FXCollections.observableArrayList();
            for (Evenement evente : eventList) {
                if (evente.getNom_event().toLowerCase().contains(query) || Float.toString(evente.getDuree()).contains(query)) {
                    filteredList.add(evente);
                }
            }
            listview.setItems(filteredList);
        }
    }

    @FXML
    void pdf(ActionEvent event) throws FileNotFoundException, DocumentException, SQLException {
        Servicesevent eventservice = new Servicesevent();
        List<Evenement> events = eventservice.findAllEvents();

// Create a new PDF document
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        PdfWriter.getInstance(document, new FileOutputStream("events.pdf"));

// Open the document
        document.open();

// Create a table with four columns
        PdfPTable table = new PdfPTable(6);

// Add table headers
        table.addCell("id_Event");
        table.addCell("Durée");
        table.addCell("prix");
        table.addCell("Date début");
        table.addCell("Date fin");
        table.addCell("id dest");
        // table.addCell("nom_event");

//table.addCell("id destination ");
// Add the information of all sponsors to the table
        for (Evenement s : events) {
            table.addCell(Integer.toString(s.getId_event()));
            table.addCell(s.getDuree() + " mois");
            table.addCell(Float.toString(s.getPrix()));
            table.addCell(s.getDate_deb().toString());
            table.addCell(s.getDate_fin().toString());
            table.addCell(Integer.toString(s.getId()));

            //  table.addCell(new Paragraph(s.getNom_event()));
        }

// Add the table to the document
        document.add(table);

// Close the document
        document.close();

    }

    @FXML
    private void deleteUser(MouseEvent event) {
    }

    @FXML
    private void seDeconnecter(ActionEvent event) {
                  try {
               Parent page1 = FXMLLoader.load(getClass().getResource("LoginAdmin.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

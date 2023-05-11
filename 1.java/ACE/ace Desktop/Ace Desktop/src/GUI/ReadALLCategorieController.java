/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Categorie;

import Services.ServiceCategorie;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eyamo
 */
public class ReadALLCategorieController implements Initializable {

    @FXML
    private ListView<Categorie> listeviewc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            ServiceCategorie sc = new ServiceCategorie();
            List<Categorie> ls = sc.readAllC();

            for (int i = 0; i < ls.size(); i++) {
                Categorie c = ls.get(i);
                String text = c.getCategories();
                listeviewc.getItems().add(c); // add the text to the list view
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReadALLCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void buttsupp(ActionEvent event) {

        ServiceCategorie ser = new ServiceCategorie();
        Categorie selected = listeviewc.getSelectionModel().getSelectedItem();
        try {
            ser.DeleteC(selected.getId_cat());
            // Remove the selected item from the listview
            listeviewc.getItems().remove(selected);

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
    private void buttmodif(ActionEvent event) throws IOException {
        Categorie selectedpub = listeviewc.getSelectionModel().getSelectedItem();
        if (selectedpub != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("updatec.fxml"));
            Parent root = loader.load();
            UpdateCategorieController mod = loader.getController();
            mod.setcategorie(selectedpub);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    private void butthome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("windowSponsor.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttrefresh(ActionEvent event) {

        try {
            // retrieve the updated list of data from the database
            ServiceCategorie service = new ServiceCategorie();
            List<Categorie> dataList = service.readAllC();

            // update the listview with the new data
            listeviewc.getItems().clear();
            listeviewc.getItems().addAll(((dataList)));

        } catch (SQLException e) {
            // handle the exception
        }

    }

}

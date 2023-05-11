/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Categorie;
import Services.ServiceCategorie;
import java.net.URL;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eyamo
 */
public class AddCategorieController implements Initializable {

    @FXML
    private TextField txtcatg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajoutercat(ActionEvent event) throws SQLException {
        String categories = txtcatg.getText();
        //  int id_sponsor=Integer.parseInt((tfidsponsor.getText()));

        Categorie c = new Categorie(categories, 1);
        ServiceCategorie sc = new ServiceCategorie();

        sc.ajouterC(c);

        // Display a success message to the user
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("La categorie a  éte ajouter avec succés.");
        alert.showAndWait();
        // Clear the input fields
        //tfidsponsor.clear();
        txtcatg.clear();

    }

    @FXML
    private void butaff(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ReadALLc.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

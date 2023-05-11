/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class GestionController implements Initializable {

    private Button btn_res1;
    @FXML
    private Button home;
    @FXML
    private Hyperlink evenements;
    @FXML
    private Hyperlink forum;
    @FXML
    private Hyperlink dest;
    @FXML
    private Hyperlink Réservation;
    @FXML
    private Hyperlink sponsor;
    @FXML
    private Hyperlink Catégories;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }




    private void gest_res(ActionEvent event) {

        Stage stage = (Stage) btn_res1.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void deleteUser(MouseEvent event) {
    }

    @FXML
    private void seDeconnecter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            Stage stage = (Stage) home.getScene().getWindow();
            stage.setScene(scene);
            // stage.show();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Succes");
            alert.setContentText("Déconnexion  avec succes");
            alert.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void evenements(ActionEvent event) throws IOException {
            Parent page1 = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
    }

    private void utilisateurs(ActionEvent event) throws IOException {
              Parent page1 = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

    private void administrateurs(ActionEvent event) throws IOException {
            Parent page1 = FXMLLoader.load(getClass().getResource("AccueilAd.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void sponsor(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("windowSponsor.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void Réservation(ActionEvent event) throws IOException {
           Parent page1 = FXMLLoader.load(getClass().getResource("Acceuil2.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        
        
    }

    @FXML
    private void dest(ActionEvent event) throws IOException {
        //  Parent page1 = FXMLLoader.load(getClass().getResource("hot_des.fxml"));
                   // Parent page1 = FXMLLoader.load(getClass().getResource("gereHotUser.fxml"));

          //  Scene scene = new Scene(page1);
          //  Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           // stage.setScene(scene);
           // stage.show();
             FXMLLoader loader = new FXMLLoader(getClass().getResource("gereHotUser.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }

    @FXML
    private void Catégories(ActionEvent event) throws IOException {
              Parent page1 = FXMLLoader.load(getClass().getResource("ReadALLc.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void forum(ActionEvent event) throws IOException {
                      Parent page1 = FXMLLoader.load(getClass().getResource("Forum.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        
    }

}

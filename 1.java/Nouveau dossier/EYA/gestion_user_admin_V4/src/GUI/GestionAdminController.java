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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class GestionAdminController implements Initializable {

    @FXML
    private Button btn_uti;
    @FXML
    private Button btn_ad;
    @FXML
    private Button btn_forum;
    @FXML
    private Button btn_eve;
    @FXML
    private Button btn_des;
    @FXML
    private Button btn_spons;
    @FXML
    private Button btn_res;
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void gest_us(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gest_admin(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("AccueilAd.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AccueilAdController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gest_forum(ActionEvent event) {
    }

    @FXML
    private void gest_eve(ActionEvent event) {
    }

    @FXML
    private void gest_des(ActionEvent event) {
    }

    @FXML
    private void gest_spons(ActionEvent event) {
    }

    @FXML
    private void gest_res(ActionEvent event) {
    }

    @FXML
    private void deleteUser(MouseEvent event) {
    }

    @FXML
    private void seDeconnecter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginAdmin.fxml"));
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

}

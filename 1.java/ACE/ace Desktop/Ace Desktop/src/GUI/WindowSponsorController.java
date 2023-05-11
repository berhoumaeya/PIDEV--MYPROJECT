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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author eyamo
 */
public class WindowSponsorController implements Initializable {

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
    private void butafficher(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ReadALLsponsor.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void butajout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addsponsor.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttajouterc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addc.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttaffc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ReadALLc.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttmail(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Mailing.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttsms(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("smsSponsor.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void menu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionAdmin.fxml"));
            JOptionPane.showMessageDialog(null, "Welcome HOME !!");
            Parent root = loader.load();
            home.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GestionAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Logger.getLogger(LoginAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

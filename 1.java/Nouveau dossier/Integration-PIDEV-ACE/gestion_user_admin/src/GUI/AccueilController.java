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
 * @author Lenovo
 */
public class AccueilController implements Initializable {

    @FXML
    private Button home;
    private Button btnA;
    @FXML
    private Button btnM;
    @FXML
    private Button btnAF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void menu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionAdmin.fxml"));
            Parent root = loader.load();
            home.getScene().setRoot(root);
            JOptionPane.showMessageDialog(null, "Welcome HOME !!");
        } catch (IOException ex) {
            Logger.getLogger(GestionAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*  private void bntA(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutUser.fxml"));
            Parent root = loader.load();
            btnA.getScene().setRoot(root);
            // JOptionPane.showMessageDialog(null, "Welcome HOME !!");
        } catch (IOException ex) {
            Logger.getLogger(AjoutUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    @FXML
    private void btnM(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierUser.fxml"));
            Parent root = loader.load();
            btnM.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(AjoutUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnAF(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherUser.fxml"));
            Parent root = loader.load();
            btnAF.getScene().setRoot(root);
            // JOptionPane.showMessageDialog(null, "Welcome HOME !!");
        } catch (IOException ex) {
            Logger.getLogger(AjoutUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @FXML
    private void deleteUser(MouseEvent event) {
    }

}

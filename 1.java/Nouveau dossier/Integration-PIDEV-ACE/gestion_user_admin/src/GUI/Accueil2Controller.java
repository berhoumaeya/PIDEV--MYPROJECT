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
 * @author achref
 */
public class Accueil2Controller implements Initializable {

    @FXML
    private Button home;
    @FXML
    private Button btnR2;
    @FXML
    private Button btnR3;
    @FXML
    private Button btnR1;
    @FXML
    private Button btnR4;
    @FXML
    private Button btnP1;
    @FXML
    private Button btnP2;
    @FXML
    private Button btnP3;
    @FXML
    private Button btnP4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void btnaffr(ActionEvent event) {try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResAfficher.fxml"));
            Parent root = loader.load();
            btnR2.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(ResAjoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnsupr(ActionEvent event) {try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResSupp.fxml"));
            Parent root = loader.load();
            btnR3.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(ResAjoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnajr(ActionEvent event) {try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResAjout.fxml"));
            Parent root = loader.load();
            btnR1.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(ResAjoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void btnmodr(ActionEvent event) {try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Res.fxml"));
            Parent root = loader.load();
            btnR4.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(ResAjoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnajp(ActionEvent event) {try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PromAjout.fxml"));
            Parent root = loader.load();
            btnP1.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(PromAjoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnaffp(ActionEvent event) {try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PromAfficher.fxml"));
            Parent root = loader.load();
            btnP2.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(PromAjoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnsupp(ActionEvent event) {try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PromSupp.fxml"));
            Parent root = loader.load();
            btnP3.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(PromAjoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnmodp(ActionEvent event) {
    }
    
}

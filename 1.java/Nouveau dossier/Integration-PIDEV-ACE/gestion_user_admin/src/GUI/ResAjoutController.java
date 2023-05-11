/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Reservation;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Services.ReservationCrud;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author achref
 */
public class ResAjoutController implements Initializable {
    @FXML
    private Label idclient;
    @FXML
    private Label idevent;
    @FXML
    private TextField txtidclient;
    @FXML
    private TextField txtidevent;
    @FXML
    private Label qte;
    @FXML
    private TextField txtqte;
    @FXML
    private Label remplissez;
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
    private void createReservation(ActionEvent event) throws SQLException {
                int idclient = Integer.parseInt(txtidclient.getText());
        int idevent = Integer.parseInt(txtidevent.getText());
        int qte = Integer.parseInt(txtqte.getText());
        int idres = 0;
        
        Reservation r = new Reservation (idres , idclient, idevent, qte);
        ReservationCrud rc = new ReservationCrud();
        rc.createReservation(r);
    }

    @FXML
    private void menu(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionAdmin.fxml"));
            
            Parent root = loader.load();
            home.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GestionAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    
}

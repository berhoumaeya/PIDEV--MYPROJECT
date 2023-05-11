/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Reservation;
import Services.ReservationCrud;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author achref
 */
public class ResUpdateController implements Initializable {


    @FXML
    private TextField txtidres;
    @FXML
    private Label txtchamps;
    @FXML
    private TextField txtidevent;
    @FXML
    private TextField txtclient;
    @FXML
    private TextField txtqte;
    @FXML
    private Label labelidclient;
    @FXML
    private Label labelidevent;
    @FXML
    private Label labelqte;
    @FXML
    private Label labelidres;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void updateReservation(ActionEvent event) throws SQLException {
                int idclient = Integer.parseInt(txtclient.getText());
        int idevent = Integer.parseInt(txtidevent.getText());
        int qte = Integer.parseInt(txtqte.getText());
        
        
        ReservationCrud rc = new ReservationCrud();
        rc.updateReservation(idclient, idevent, qte);
    }

    @FXML
    private void move(ActionEvent event) {
    }

    @FXML
    private void Add(ActionEvent event) {
    }

}

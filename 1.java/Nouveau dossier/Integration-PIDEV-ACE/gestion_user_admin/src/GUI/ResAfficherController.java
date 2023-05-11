/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Entite.Reservation;
import Services.ReservationCrud;
import Utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author achref
 */
public class ResAfficherController implements Initializable {

 @FXML
private TableView<Reservation> tableViewReservations;
@FXML
private TableColumn<Reservation, Integer> idres;
@FXML
private TableColumn<Reservation, Integer> idclient;
@FXML
private TableColumn<Reservation, Integer> idevent;
@FXML
private TableColumn<Reservation, Integer> qte;
    @FXML
    private Button afficher;
    @FXML
    private Button afficheparres;
@FXML
private TextField idrestxt;
    @FXML
    private TextField idusertxt;
    @FXML
    private TextField ideventtxt;
    @FXML
    private Button afficheparuser;
    @FXML
    private Button afficheparevent;
    @FXML
    private Button tri;
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    
public void initialize(URL url, ResourceBundle rb) {
    
}

    @FXML

    public void afficherRes(ActionEvent event) {

     ObservableList<Reservation> ResList = FXCollections.observableArrayList();
        try {
            String requete;
            requete = "SELECT * FROM reservation r  ";
            Statement st = DataSource.getInstance().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reservation r = new Reservation();
                r.setIdRes(rs.getInt("id_res"));
                r.setIdUser(rs.getInt("id_user"));
                r.setIdEvent(rs.getInt("id_event"));
                r.setQte(rs.getInt("qte"));
               
                

                ResList.add(r);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        idres.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdRes()));
        idclient.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdUser()));
        idevent.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdEvent()));
        qte.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getQte()));

        

        tableViewReservations.setItems(ResList);
       // search();
    }
    @FXML
private void afficherReservationsParUser(ActionEvent event) {
    ObservableList<Reservation> ResList = FXCollections.observableArrayList();
    try {
        String requete;
        requete = "SELECT * FROM reservation r WHERE r.id_user=" + idusertxt.getText();
        Statement st = DataSource.getInstance().getConnection()
                .createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            Reservation r = new Reservation();
            r.setIdRes(rs.getInt("id_res"));
            r.setIdUser(rs.getInt("id_user"));
            r.setIdEvent(rs.getInt("id_event"));
            r.setQte(rs.getInt("qte"));

            ResList.add(r);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    idres.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdRes()));
    idclient.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdUser()));
    idevent.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdEvent()));
    qte.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getQte()));

    tableViewReservations.setItems(ResList);
}

@FXML
private void afficherReservationsParEvent(ActionEvent event) {
    ObservableList<Reservation> ResList = FXCollections.observableArrayList();
    try {
        String requete;
        requete = "SELECT * FROM reservation r WHERE r.id_event=" + ideventtxt.getText();
        Statement st = DataSource.getInstance().getConnection()
                .createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            Reservation r = new Reservation();
            r.setIdRes(rs.getInt("id_res"));
            r.setIdUser(rs.getInt("id_user"));
            r.setIdEvent(rs.getInt("id_event"));
            r.setQte(rs.getInt("qte"));

            ResList.add(r);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    idres.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdRes()));
    idclient.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdUser()));
    idevent.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdEvent()));
    qte.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getQte()));

    tableViewReservations.setItems(ResList);
}

@FXML
private void afficherReservationsParRes(ActionEvent event) {
    ObservableList<Reservation> ResList = FXCollections.observableArrayList();
    try {
        String requete;
        requete = "SELECT * FROM reservation r WHERE r.id_res=" + idrestxt.getText();
        Statement st = DataSource.getInstance().getConnection()
                .createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            Reservation r = new Reservation();
            r.setIdRes(rs.getInt("id_res"));
            r.setIdUser(rs.getInt("id_user"));
            r.setIdEvent(rs.getInt("id_event"));
            r.setQte(rs.getInt("qte"));

            ResList.add(r);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    idres.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdRes()));
    idclient.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdUser()));
    idevent.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdEvent()));
    qte.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getQte()));

    tableViewReservations.setItems(ResList);
}
@FXML
public void trierParQte(ActionEvent event) {
    // Get the current items in the table view
    ObservableList<Reservation> items = tableViewReservations.getItems();
    
    // Sort the items based on the qte attribute
    items.sort((r1, r2) -> Integer.compare(r1.getQte(), r2.getQte()));
    
    // Update the table view with the sorted items
    tableViewReservations.setItems(items);
}

    @FXML
    private void menu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil2.fxml"));
            Parent root = loader.load();
            home.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Accueil2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
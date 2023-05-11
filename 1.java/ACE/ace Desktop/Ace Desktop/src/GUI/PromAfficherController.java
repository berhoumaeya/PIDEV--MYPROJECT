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
import Entite.Promotion;
import Services.PromotionCrud;
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
public class PromAfficherController implements Initializable {

 @FXML
private TableView<Promotion> tableViewPromotions;
@FXML
private TableColumn<Promotion, Integer> idprom;
@FXML
private TableColumn<Promotion, Integer> idclient;
@FXML
private TableColumn<Promotion, Integer> remise;

    @FXML
    private Button afficher;
    @FXML
    private TextField idusertxt;
    private TextField idpromtxt;
    @FXML
    private Button afficheparuser;
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

    public void afficherProm(ActionEvent event) {

     ObservableList<Promotion> ResList = FXCollections.observableArrayList();
        try {
            String requete;
            requete = "SELECT * FROM promotion p  ";
            Statement st = DataSource.getInstance().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Promotion p = new Promotion();
                p.setIdProm(rs.getInt("id_prom"));
                p.setIdUser(rs.getInt("id_client"));
                p.setRemise(rs.getInt("remise"));
                ResList.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        idprom.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdProm()));
        idclient.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdUser()));
        remise.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getRemise()));
        

        

        tableViewPromotions.setItems(ResList);
       // search();
    }
    @FXML
private void afficherPromotionsParUser(ActionEvent event) {
    ObservableList<Promotion> ResList = FXCollections.observableArrayList();
    try {
        String requete;
        requete = "SELECT * FROM promotion p WHERE p.id_client=" + idusertxt.getText();
        Statement st = DataSource.getInstance().getConnection()
                .createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            Promotion p = new Promotion();
            p.setIdProm(rs.getInt("id_prom"));
            p.setIdUser(rs.getInt("id_client"));
            p.setRemise(rs.getInt("remise"));
            

            ResList.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    idprom.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdProm()));
    idclient.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdUser()));
    remise.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getRemise()));
  

    tableViewPromotions.setItems(ResList);
}

private void afficherPromotionsParProm(ActionEvent event) {
    ObservableList<Promotion> ResList = FXCollections.observableArrayList();
    try {
        String requete;
        requete = "SELECT * FROM promotion r WHERE r.id_prom=" + idpromtxt.getText();
        Statement st = DataSource.getInstance().getConnection()
                .createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            Promotion p = new Promotion();
            p.setIdProm(rs.getInt("id_prom"));
            p.setIdUser(rs.getInt("id_client"));
            p.setRemise(rs.getInt("remise"));
            

            ResList.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    idprom.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdProm()));
    idclient.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdUser()));
    remise.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getRemise()));
    

    tableViewPromotions.setItems(ResList);
}


@FXML
public void trierParRemise(ActionEvent event) {
    // Get the current items in the table view
    ObservableList<Promotion> items = tableViewPromotions.getItems();
    
    // Sort the items based on the qte attribute
    items.sort((p1, p2) -> Integer.compare(p1.getRemise(), p2.getRemise()));
    
    // Update the table view with the sorted items
    tableViewPromotions.setItems(items);
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
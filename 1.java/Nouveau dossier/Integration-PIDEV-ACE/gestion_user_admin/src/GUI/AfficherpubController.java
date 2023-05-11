/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Evenement;
import Entite.publicite;
import Services.Servicepublicite;
import Services.Servicesevent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author farah
 */
public class AfficherpubController implements Initializable {

    @FXML
    private ListView<publicite> listviewpub;
    @FXML
    private Button seDeconnecter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Servicepublicite os = new Servicepublicite();
            List<publicite> ls = os.readAllPub();

            for (int i = 0; i < ls.size(); i++) {
                publicite e = ls.get(i);
                String text = e.getType()  ;
                listviewpub.getItems().add(e); // add the text to the list view
            }

        } catch (SQLException ex) {
            Logger.getLogger(AfficherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    

    @FXML
    private void btnrefresh(ActionEvent event) {
        try {
            // retrieve the updated list of data from the database
            Servicepublicite service = new Servicepublicite();
            List<publicite> dataList = service.readAllPub();

            // update the listview with the new data
            listviewpub.getItems().clear();
            listviewpub.getItems().addAll(dataList);

           
        } catch (SQLException e) {
            // handle the exception
        }
    }

    @FXML
    private void backhome(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void btnsuppub(ActionEvent event) {
               Servicepublicite ser = new Servicepublicite();
    publicite selected = listviewpub.getSelectionModel().getSelectedItem();
    try {
        ser.DeletePub(selected.getId_pub());
        // Remove the selected item from the listview
        listviewpub.getItems().remove(selected);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Suppression d'un event");
        alert.setHeaderText(null);
        alert.setContentText("L'event a été supprimé avec succès");
        alert.showAndWait();
    } catch (SQLException e) {
        e.printStackTrace();
    }
        
        
    }

    @FXML
    private void updatepub(ActionEvent event) throws IOException {
          publicite selectedpub = listviewpub.getSelectionModel().getSelectedItem();
        if (selectedpub != null) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("updatepub.fxml"));
           Parent root = loader.load();
            UpdatepubController mod = loader.getController();
             mod.setPub(selectedpub);
           
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }
    
    }

    @FXML
    private void deleteUser(MouseEvent event) {
    }

    @FXML
    private void seDeconnecter(ActionEvent event) {
              try {
        Parent page1 = FXMLLoader.load(getClass().getResource("LoginAdmin.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
          } catch (IOException ex) {
            Logger.getLogger(LoginAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
}

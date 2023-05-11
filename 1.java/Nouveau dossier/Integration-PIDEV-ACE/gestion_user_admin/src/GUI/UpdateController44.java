/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Evenement;
import Services.Servicesevent;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import static java.awt.PageAttributes.MediaType.C;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import static javafx.print.Paper.C;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.C;
import static javax.print.attribute.standard.MediaSize.Engineering.C;
import static javax.print.attribute.standard.MediaSizeName.C;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author farah
 */
public class UpdateController44 implements Initializable {

    @FXML
    private TextField txtdureesup;
    @FXML
    private TextField txtprixsup;

    private Evenement evente;
    @FXML
    private Label labeldatedeb;
    @FXML
    private DatePicker txtdatedeb;
    @FXML
    private DatePicker txtdatefin;
    @FXML
    private Label labelnom;
    @FXML
    private TextField txtnomevent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
 private void update(ActionEvent event) throws SQLException {
    // Vérifier que tous les champs sont remplis
    if (txtprixsup.getText().isEmpty() || txtdureesup.getText().isEmpty() || 
            txtdatefin.getValue() == null || txtdatedeb.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Champs vides");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }
    
    // Vérifier l'unicité du nom de l'événement
    Servicesevent pc = new Servicesevent();
    String nomEvent = txtnomevent.getText();
    if (!nomEvent.equals(evente.getNom_event()) && pc.verifierUniciteEvenement(nomEvent)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Nom d'événement déjà utilisé");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir un autre nom d'événement.");
        alert.showAndWait();
        return;
    }
    
    float prix = Float.parseFloat(txtprixsup.getText());
    float duree = Float.parseFloat(txtdureesup.getText());
    LocalDate date_fin = txtdatefin.getValue();
    LocalDate date_deb = txtdatedeb.getValue();

    evente.setDate_deb(java.sql.Date.valueOf(date_deb));
    evente.setDate_fin(java.sql.Date.valueOf(date_fin));
    evente.setDuree(duree);
    evente.setPrix(prix);
    evente.setNom_event(nomEvent);

    Servicesevent serviceDest = new Servicesevent();
    serviceDest.update(evente);

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Modification d'un événement");
    alert.setHeaderText(null);
    alert.setContentText("L'événement a été modifiée avec succès");
    alert.showAndWait();
    Stage stage = (Stage) txtprixsup.getScene().getWindow();
    stage.close();
}


    @FXML
    private void backbtn(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //recuperation des donness
   public void setEvent(Evenement ev) {
        this.evente = ev;
    String nomEvent = evente.getNom_event();
        String prixAsString = Float.toString(evente.getPrix());
    String dureeAsString = Float.toString(evente.getDuree());
    LocalDate localDate = evente.getDate_deb().toLocalDate(); // conversion en LocalDate
    String dateDeb = localDate.toString();
      LocalDate localDate2 = evente.getDate_fin().toLocalDate(); // conversion en LocalDate
    String dateDeb2 = localDate.toString();// conversion en String


    txtdureesup.setText(dureeAsString);
    txtprixsup.setText(prixAsString);
    txtdatedeb.setValue(localDate);
     txtdatefin.setValue(localDate2);// affichage de la date dans le date picker 
txtnomevent.setText(nomEvent);
    }



    

}

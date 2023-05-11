/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Entite.sponsor;
import Services.ServiceSponsor;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eyamo
 */
public class AddSponsorController implements Initializable {

    @FXML
    private TextField tfintitule;
    @FXML
    private DatePicker tfdatedeb;

    @FXML
    private DatePicker tfdatefin;
    @FXML
    private TextField tfduree;
    @FXML
    private Button btnajouter;
public static final String ACCOUNT_SID = "ACb3512b1baa60eb994049eea0656f6934";

public static final String AUTH_TOKEN = "db782f1385c2a4a273b50e7c3222a46f";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    
    public static void sendSms(String recipient, String messageBody) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(
            new PhoneNumber(recipient), // To number
            new PhoneNumber("+15673343461"), // From number
            messageBody) // SMS body
        .create();

    System.out.println("Message sent: " + message.getSid());
  }
    @FXML
    private void savesponsor(ActionEvent event) throws SQLException {

        LocalDate date_fin = tfdatefin.getValue();
        LocalDate date_deb = tfdatedeb.getValue();
        int duree = Integer.parseInt(tfduree.getText());
        String intitule = tfintitule.getText();

// Validate non-empty fields
        if (intitule.isEmpty() || tfduree.getText().isEmpty() || date_deb == null || date_fin == null) {
            // Display an error alert
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all required fields!");
            alert.showAndWait();
            return;
        }

        try {
            ServiceSponsor pc = new ServiceSponsor();

            // Vérifier si un sponsor avec le même intitulé existe déjà
            if (pc.sponsorExiste(intitule)) {
                // Afficher un message d'erreur à l'utilisateur
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Un sponsor avec l'intitulé \"" + intitule + "\" existe déjà.");
                alert.showAndWait();
                return;
            }

            pc.ajouter(new sponsor(intitule, duree, java.sql.Date.valueOf(date_deb), java.sql.Date.valueOf(date_fin)));
sendSms("+21621560477","ce sponsor a ete ajoute");
            // Display a success alert
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Data inserted successfully!");
            alert.showAndWait();
        } catch (SQLException e) {
            // Display an error alert
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while inserting data into the database: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void buthome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ReadALLsponsor.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

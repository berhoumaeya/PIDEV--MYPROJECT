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
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author eyamo
 */


public class smsSponsorController implements Initializable {
    
public static final String ACCOUNT_SID = "ACb3512b1baa60eb994049eea0656f6934";

public static final String AUTH_TOKEN = "db782f1385c2a4a273b50e7c3222a46f";
    @FXML
    private TextField txtnum;
    @FXML
    private TextField txtmsg;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void buttsms(ActionEvent event) {
        sendSms(txtnum.getText(),txtmsg.getText());
        
        
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
    private void butthome(ActionEvent event) throws IOException {
             Parent root = FXMLLoader.load(getClass().getResource("windowSponsor.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
}

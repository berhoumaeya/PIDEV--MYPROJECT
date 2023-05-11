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
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author eyamo
 */


public class SmsController_1 implements Initializable {

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
       
        
    }
    
    @FXML
    private void butthome(ActionEvent event) {
    }
    
}

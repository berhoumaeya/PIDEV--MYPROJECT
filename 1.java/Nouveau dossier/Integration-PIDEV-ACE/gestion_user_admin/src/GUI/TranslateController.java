

package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class TranslateController implements Initializable {

    @FXML
    private TextField entered;
    @FXML
    private TextField result;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
     private static String translate(String langFrom, String langTo, String text) throws IOException {
        
        String urlStr = "https://script.google.com/macros/s/AKfycby7UDLfABPMWr4jV5YeH9FKclWuyJxHcHJSvTj2MWysaOw0z7cptN0S16rXQTaVw32I/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        
        in.close();
        return response.toString();
        
    }
     
     public void translated(){
        try {
            String text = entered.getText();
            result.setText(translate("en","fr",text));
        } catch (IOException ex) {
            Logger.getLogger(TranslateController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     @FXML
    private void toHome(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Forum.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     

}
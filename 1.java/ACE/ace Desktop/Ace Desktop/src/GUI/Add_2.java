/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author farah
 */
public class Add_2 extends Application {
     @FXML
    private Scene scene;
    private Stage stage;
    private Parent fxml;
    @Override
    public void start(Stage primaryStage) throws  Exception {
      
             FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                

    fxml=loader.load();
    scene = new Scene(fxml);

    stage = primaryStage;
    stage.setScene(scene);
    stage.show();
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         launch(args);

        // encode

    }
   
}

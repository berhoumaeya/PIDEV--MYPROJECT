/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.application.Application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author eyamo
 */
public class add_1 extends Application {

    @FXML
    private Scene scene;
    private Stage stage;
    private Parent fxml;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //ajouter**
        //   FXMLLoader loader = new FXMLLoader(getClass().getResource("add.fxml"));
        //supprimer**
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("delete.fxml"));
        //  FXMLLoader loader = new FXMLLoader(getClass().getResource("update.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("ReadALL.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("windowSponsor.fxml"));

        // FXMLLoader loader = new FXMLLoader(getClass().getResource("addc.fxml"));
        //   FXMLLoader loader = new FXMLLoader(getClass().getResource("updatec.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("deletec.fxml"));
//FXMLLoader loader = new FXMLLoader(getClass().getResource("ReadALLc.fxml"));
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("mail.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("sms.fxml"));
        fxml = loader.load();
        scene = new Scene(fxml);
        // titre stage.setTitle("Gestion Sponsors");
        stage = primaryStage;
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

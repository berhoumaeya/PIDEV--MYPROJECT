/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author farah
 */
public class WebpageController_1 implements Initializable {

    @FXML
    private Button btnload;
    @FXML
    private TextField search;
    @FXML
    private WebView webview;
    
     private WebEngine engine;
	private WebHistory history;
	private String homePage;
	private double webZoom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       engine = webview.getEngine();
		homePage = "www.google.com";
		search.setText(homePage);
		webZoom = 1;
		loadPage();
    }    
    	public void loadPage() {
		
		//engine.load("http://www.google.com");
		engine.load("http://"+search.getText());
	}
	
	public void refreshPage() {
		
		engine.reload();
	}
	
    @FXML
	public void zoomIn() {
		
		webZoom+=0.25;
		webview.setZoom(webZoom);
	}
	
    @FXML
	public void zoomOut() {
		
		webZoom-=0.25;
		webview.setZoom(webZoom);
	}
	
	public void displayHistory() {
		
		history = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = history.getEntries();
		
		for(WebHistory.Entry entry : entries) {
			
			//System.out.println(entry);
			System.out.println(entry.getUrl()+" "+entry.getLastVisitedDate());
		}
	}
	
    @FXML
	public void back() {
		
		history = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = history.getEntries();
		history.go(-1);
		
		search.setText(entries.get(history.getCurrentIndex()).getUrl());
	}
	
	public void forward() {
		
		history = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = history.getEntries();
		history.go(1);
		
		search.setText(entries.get(history.getCurrentIndex()).getUrl());
	}
	
    @FXML
	public void executelink() {
		
		engine.executeScript("window.location = \"https://www.youtube.com\";");
	}


    @FXML
    private void home(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}

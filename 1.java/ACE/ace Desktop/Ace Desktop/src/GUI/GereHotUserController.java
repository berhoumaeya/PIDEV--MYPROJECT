/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Destination;
import Entite.hotel;
import Services.ServiceHotel;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.zxing.client.j2se.MatrixToImageWriter;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class GereHotUserController implements Initializable {

    @FXML
    private ListView<hotel> lvdes;
         private ObservableList<hotel> listdes = FXCollections.observableArrayList();

    @FXML
    private Button btnrefresh;
    @FXML
    private Button btnhot;
    @FXML
    private Button btnex;
    @FXML
    private Button btnet;
    @FXML
    private Button qrcode;
    @FXML
    private ImageView logo;
    @FXML
    private Button seDeconnecter;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lvdes.setItems(listdes);
        display();
        
     Image myImage = new Image(getClass().getResourceAsStream("../Images/logo.png"));
      logo.setImage(myImage);
    }    

    @FXML
    private void refresh(ActionEvent event) {
          try {
            // retrieve the updated list of data from the database
            ServiceHotel service = new ServiceHotel();
            List<hotel> dataList = service.readAllhotels();

            // update the listview with the new data
            lvdes.getItems().clear();
            lvdes.getItems().addAll(dataList);

           
        } catch (SQLException e) {
            // handle the exception
        }
    }
      public void display() {
        try {
             ServiceHotel ser = new ServiceHotel();
        List<hotel> hotels =  ser.readAllhotels();
        listdes.clear();
        listdes.addAll(hotels);
    } catch (SQLException e) {
        e.printStackTrace();
    }
        
    }

    @FXML
    private void gohot(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/gmap.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }

    @FXML
    private void excel(ActionEvent event) throws FileNotFoundException, IOException {
        // Create a workbook and a sheet
    Workbook workbook = new XSSFWorkbook(); 
    Sheet sheet = workbook.createSheet("ListView Data");

    // Get the data from the ListView
    ObservableList<hotel> data = lvdes.getItems();

    // Create a font for the headers
    Font headerFont = workbook.createFont();
    headerFont.setBold(true);

    // Create a cell style for the headers
    CellStyle headerStyle = workbook.createCellStyle();
    headerStyle.setFont(headerFont);

    // Write the headers to the sheet
    Row headerRow = sheet.createRow(0);
    Cell headerCell1 = headerRow.createCell(0);
    headerCell1.setCellValue("nom");
    headerCell1.setCellStyle(headerStyle);
    Cell headerCell2 = headerRow.createCell(1);
    headerCell2.setCellValue("etoile");
    headerCell2.setCellStyle(headerStyle);
     Cell headerCell3 = headerRow.createCell(2);
    headerCell3.setCellValue("type");
    headerCell3.setCellStyle(headerStyle);

    // Write the data to the sheet
    for (int i = 0; i < data.size(); i++) {
        Row row = sheet.createRow(i + 1);
        Cell cell1 = row.createCell(0);
        cell1.setCellValue(data.get(i).getNom());
        Cell cell2 = row.createCell(1);
        cell2.setCellValue(data.get(i).getEtoile());
        Cell cell3 = row.createCell(2);
        cell3.setCellValue(data.get(i).getType());
    }

    // Save the workbook to a file
    FileOutputStream fileOut = new FileOutputStream("ListViewHotel.xlsx");
    workbook.write(fileOut);
    fileOut.close();

    // Open the file
    Desktop.getDesktop().open(new File("ListViewHotel.xlsx"));
    }

    @FXML
        private void trietoile(ActionEvent event) {
        
        
          // Get the items in the ListView
    ObservableList<hotel> items = lvdes.getItems();
    
    // Sort the items by ville
    items.sort(new Comparator<hotel>() {
        public int compare(hotel d1, hotel d2) {
            return d2.getEtoile().compareTo(d1.getEtoile());
        }

    });
    
    // Set the sorted items back to the ListView
    lvdes.setItems(items);
        
    }

    @FXML
      private void qrco(ActionEvent event) throws WriterException {// Get the selected Destination object from the ListView
 // Get the selected Destination object from the ListView
    hotel selectedDest = lvdes.getSelectionModel().getSelectedItem();

    if (selectedDest != null) {
        // Get the name of the "pays", "etoile" and "type" from the selected Destination
        String nom = selectedDest.getNom();
        String etoile = selectedDest.getEtoile();
        String type = selectedDest.getType();

        // Generate the QR code with the paysName, etoile and type as the content
        String content = nom + "|" + etoile + "|" + type; // Use the pipe symbol as separator
        int width = 300;
        int height = 300;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);

        // Convert the BitMatrix to a BufferedImage
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        // Display the image in a new window
        Stage stage = new Stage();
        stage.setTitle("QR Code");
        ImageView imageView = new ImageView(SwingFXUtils.toFXImage(qrImage, null));
        Scene scene = new Scene(new Group(imageView));
        stage.setScene(scene);
        stage.show();
    } else {
        // Handle case where no destination is selected
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("No hotel selected");
        alert.setContentText("Please select a hotel from the list.");
        alert.showAndWait();
    }   
    }

    @FXML
    private void deleteUser(MouseEvent event) {
    }

  

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

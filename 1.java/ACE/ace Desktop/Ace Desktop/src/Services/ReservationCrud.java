/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author achref
 */
import Entite.Reservation;
import GUI.ajout;
import MailTelegram.EmailService;
import MailTelegram.TelegramService;
import Utils.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ReservationCrud {

    private Connection connection;

    // constructor to establish database connection
    public ReservationCrud() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/ace2";
        String username = "root";
        String password = "";
        connection = DriverManager.getConnection(url, username, password);
    }

    /**
     *
     * @param r
     * @param id_user
     * @param id_event
     * @param qte
     * @throws SQLException
     */
public void createReservation(Reservation r) throws SQLException {
    String sql = "INSERT INTO reservation ( id_event,id_user, qte) VALUES (?, ?, ?)";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setInt(2, r.getIdUser());
    statement.setInt(1, r.getIdEvent());
    statement.setInt(3, r.getQte());
    statement.executeUpdate();
    
    // Send a message to the Telegram chat bot
    TelegramService telegramService = new TelegramService();
    String botToken = "6128512482:AAF8Lg8cnnhJRZ_0BMNyV4dK7TZW1TTT_P8";
    String chatId = "5461160264";
    String message = "Reservation created with success ";
    telegramService.sendMessageToChat(botToken, chatId, message);


    // retrieve the user's name, email and Telegram bot token from the database
    String email = "";
   // String telegramBotToken = "";
    String name="";
    String selectSql = "SELECT email , nom FROM user WHERE id = ?";
    PreparedStatement selectStatement = connection.prepareStatement(selectSql);
    selectStatement.setInt(1, r.getIdUser());
    ResultSet rs = selectStatement.executeQuery();
    if (rs.next()) {
        email = rs.getString("email");
        //telegramBotToken = rs.getString("telegram_bot_token");
        name = rs.getString("user_name");
    }
      // retrieve destination informations and eventDate from the database
    int destinationId = 2464648;
    java.sql.Date eventDate = null;
    String ville = "";
    String selectEventSql = "SELECT destination.id_weather AS destination_id, destination.ville ,  evenement.date_deb FROM evenement JOIN destination ON evenement.id = destination.id WHERE evenement.id_event = ?";
    PreparedStatement selectEventStatement = connection.prepareStatement(selectEventSql);
    selectEventStatement.setInt(1, r.getIdEvent());
    ResultSet eventRs = selectEventStatement.executeQuery();
    if (eventRs.next()) {
        destinationId = eventRs.getInt("destination_id");
        ville = eventRs.getString("ville");
        eventDate = eventRs.getDate("date_deb");
    }

    // retrieve weather information using the OpenWeatherMap API
    String apiKey = "6f467fcce969a6ac1268731d478e7152";
    String url = "http://api.openweathermap.org/data/2.5/weather?id=" + destinationId + "&appid=" + apiKey;
    JsonObject weatherJson = null;
    try (InputStream response = new URL(url).openStream()) {
        weatherJson = Json.createReader(response).readObject();
    } catch (IOException e) {
        e.printStackTrace();
    }
     // format weather information for the email body
    JsonObject weatherMain = weatherJson.getJsonObject("main");
    JsonObject weather = weatherJson.getJsonArray("weather").getJsonObject(0);
    double tempKelvin = weatherMain.getJsonNumber("temp").doubleValue();
    double tempCelsius = tempKelvin - 273.15;
    String weatherDescription = weather.getString("description");
    String weatherInfo = String.format("%.1f°C and %s", tempCelsius, weatherDescription);


// send an email to the user
String subject = "Reservation done with success";
String body = String.format("Hello mr(%s)   (ID: %d) \n Your Reservation at the date %tF has been successfully created.\n Some additional informations : \n The weather at your destination which is %s is currently %s.", name, r.getIdUser(), eventDate, ville, weatherInfo);
EmailService.sendEmail(email, subject, body);

  
}
//Affichage normale
    public void readReservations() throws SQLException {
        String sql = "SELECT * FROM reservation";
        
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("id");
            int id_user = result.getInt("id_user");
            int id_event = result.getInt("id_event");
            int qte = result.getInt("qte");
            System.out.println(id_event + "Reservation ID: " + id + ", User ID: " + id_user + ", Event ID: "+ qte + ", qte: ");
        }
    }
    
    //Affichage avec le tri des id
    public void readReservations2() throws SQLException {
    String sql = "SELECT * FROM reservation ORDER BY id";
    Statement statement = connection.createStatement();
    ResultSet result = statement.executeQuery(sql);
    while (result.next()) {
        int id = result.getInt("id");
        int id_user = result.getInt("id_user");
        int id_event = result.getInt("id_event");
        int qte = result.getInt("qte");
        System.out.println("Reservation ID: " + id + ", User ID: " + id_user + ", Event ID: "+ id_event + ", qte: " + qte);
    }
}

    /**
     *
     * @param id
     * @param id_user
     * @param id_event
     * @throws SQLException
     */
    public void updateReservation(int id, int id_user, int id_event) throws SQLException {
        String sql = "UPDATE reservation SET id_user=?, id_event=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_user);
        statement.setInt(2, id_event);
        statement.setInt(3, id);
        statement.executeUpdate();
    }

    public void deleteReservation(int id) throws SQLException {
        String sql = "DELETE FROM reservation WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
    public static void main(String[] args) throws SQLException {
    try {
        // Get the data source instance
        DataSource ds = DataSource.getInstance();
        Connection conn = ds.getConnection();

        // Create a new instance of Reservation and pass in the connection
        ReservationCrud reservation = new ReservationCrud();

        // Create a new reservation
        reservation.createReservation(r);
        // Read all reservations
        reservation.readReservations();

        // Update a reservation
        reservation.updateReservation(1, 3, 4);

        // Delete a reservation
        reservation.deleteReservation(1);

        // Close the connection
        conn.close();
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}

    public void ReadReservations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    
    








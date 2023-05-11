
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
import Entite.Promotion;
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

public class PromotionCrud {

    private Connection connection;

    // constructor to establish database connection
    public PromotionCrud() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/ace9";
        String username = "root";
        String password = "";
        connection = DriverManager.getConnection(url, username, password);
    }


public void createPromotion(Promotion p) throws SQLException {
    String sql = "INSERT INTO promotion (id_client, remise) VALUES (?, ?)";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setInt(1, p.getIdUser());
    statement.setInt(2, p.getRemise());
    statement.executeUpdate();
    
    
    // retrieve the user's name, email and Telegram bot token from the database
    String email = "";
   // String telegramBotToken = "";
    String name="";
    String selectSql = "SELECT email , user_name FROM utilisateur WHERE id_user = ?";
    PreparedStatement selectStatement = connection.prepareStatement(selectSql);
    selectStatement.setInt(1, p.getIdUser());
    ResultSet rs = selectStatement.executeQuery();
    if (rs.next()) {
        email = rs.getString("email");
        //telegramBotToken = rs.getString("telegram_bot_token");
        name = rs.getString("user_name");
    }

    // Send a message to the Telegram chat bot
    TelegramService telegramService = new TelegramService();
    String botToken = "6128512482:AAF8Lg8cnnhJRZ_0BMNyV4dK7TZW1TTT_P8";
    String chatId = "5461160264";
    String message = String.format("Promotion created with success for user (%s)   (ID: %d)",name,p.getIdUser());
    telegramService.sendMessageToChat(botToken, chatId, message);



// send an email to the user
String subject = "You won a prize";
String body = String.format("Hello mr(%s)   (ID: %d) \n Vous avez gagné Une remise de (%d) ", name, p.getIdUser(), p.getRemise());
EmailService.sendEmail(email, subject, body);

  
}

    public void readPromotions() throws SQLException {
        String sql = "SELECT * FROM promotion";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id_user = result.getInt("id_user");
            int id_prom = result.getInt("id_prom");
            int remise = result.getInt("remise");
            System.out.println(id_user + "Promotionid: " + id_prom + ", User ID: " + id_user + ", Event ID: "+ remise + ", remise: ");
        }
    }
        //Affichage avec le tri des id_prom
    public void readPromotions2() throws SQLException {
    String sql = "SELECT * FROM promotion ORDER BY id_prom";
    Statement statement = connection.createStatement();
    ResultSet result = statement.executeQuery(sql);
    while (result.next()) {
        int id_prom = result.getInt("id_prom");
        int id_user = result.getInt("id_user");
        int remise = result.getInt("remise");
        System.out.println(id_user + "Promotionid: " + id_prom + ", User ID: " + id_user + ", Event ID: "+ remise + ", remise: ");
    }
}

    /**
     *
     * @param id_user
     * @param id_prom
     * @param remise
     * @throws SQLException
     */
    public void updatePromotion(int id_user, int id_prom, int remise) throws SQLException {
        String sql = "UPDATE promotion SET id_user=?, remise=? WHERE id_prom=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_user);
        statement.setInt(2, id_prom);
        statement.setInt(3, remise);
        statement.executeUpdate();
    }

    public void deletePromotion(int id_prom) throws SQLException {
        String sql = "DELETE FROM promotion WHERE id_prom=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_prom);
        statement.executeUpdate();
    }
    public static void main(String[] args) throws SQLException {
    try {
        // Get the data source instance
        DataSource ds = DataSource.getInstance();
        Connection conn = ds.getConnection();

        // Create a new instance of Promotion and pass in the connection
        PromotionCrud promotion = new PromotionCrud();

        // Create a new promotion
        promotion.createPromotion(p);
        // Read all promotions
        promotion.readPromotions();

        // Update a promotion
        promotion.updatePromotion(1, 3, 4);

        // Delete a promotion
        promotion.deletePromotion(1);

        // Close the connection
        conn.close();
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}

    public void ReadPromotions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    
    








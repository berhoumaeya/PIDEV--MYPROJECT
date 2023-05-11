

package Services;

import java.math.BigDecimal;
import Entite.Message;
import Entite.Post;
import Utils.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
//imports notification
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author eyamo
 */
public class ServiceMessage implements InterfaceMessage <Message>{
     Connection con=DataSource.getInstance().getConnection();
    
    private Statement ste;

    public ServiceMessage() {
        
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
      
     @Override
    public void ajouterMsg(Message m) throws SQLException {
            String req = "INSERT INTO `message` ( `id_post`,`contenu`,`note`) VALUES "
                    + "( '"+m.getPostId()+"','"+m.getContenu()+"','"+m.getNote()+"');";
            ste.executeUpdate(req);
        String message = "Un nouveau commentaire a été ajouté.";
        notifyUser(message);
 
    }

      @Override
    public void updateMsg(Message m) throws SQLException {
        String req="update message set  contenu=?,note=? where id_message=? AND id_post=?";
            PreparedStatement ps=con.prepareStatement(req);
            
            ps.setString(1,m.getContenu());
            BigDecimal value = new BigDecimal(m.getNote()+"");
            ps.setBigDecimal(2, value);
            ps.setInt(3,m.getMsgId());
            ps.setInt(4,m.getPostId());   
            ps.executeUpdate();
            // Appeler la méthode de notification
        String message = "Le commentaire a été modifié.";
        notifyUser(message);
    }
    
    public void updateNote(Message m) throws SQLException {
    String req = "UPDATE message SET note = ? WHERE contenu = ? AND id_post = ?";
    try {
        PreparedStatement ps = con.prepareStatement(req);
        BigDecimal value = new BigDecimal(m.getNote()+"");
        System.out.println(m.getContenu());
        System.out.println(m.getPostId());
        System.out.println(m.getNote());

        ps.setBigDecimal(1, value);
        ps.setString(2, m.getContenu());
        ps.setInt(3, m.getPostId());
        int rowsUpdated = ps.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("The message was updated successfully.");
            notifyUser("Le commentaire a été noté.");
        } else {
            System.out.println("The message was not found, so it could not be updated.");
        }
    } catch (SQLException e) {
        System.out.println("An error occurred while updating the message: " + e.getMessage());
    }
}
    
    @Override
      public void DeleteMsg(int idM, String cnt) throws SQLException
     { 
        String req = "DELETE FROM message WHERE id_post=? AND contenu=?";
        PreparedStatement pre = con.prepareStatement(req); //executer requete
        pre.setInt(1, idM);
        pre.setString(2, cnt);
        
         pre.executeUpdate();
         // Appeler la méthode de notification
        String message = "Le commentaire a été supprimé.";
        notifyUser(message);
        
         
          
      }
      
 
      

    

    @Override
    public Message findByIdMsg(int idM, int idP) throws SQLException {
        String sql="select contenu from message where id_message=? AND id_post=?";
        PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idM);
            ps.setInt(2, idP);
            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                Message s=new Message();
                s.setPostId(rs.getInt("id_post"));
                s.setMessageId(rs.getInt("id_message"));
                s.setContenu(rs.getString("contenu"));
                return s;
            }
            
            
            
            else{
                return null; }
       
    }
    
    @Override
    public List<Message> readAllMsgs() throws SQLException{
        ArrayList<Message> comments=new ArrayList<>();
        
        String req="select id_post,contenu from message";
        
        ResultSet res=ste.executeQuery(req);
  
        while (res.next()) {            
            
            int id_post=res.getInt(1);
            String sujet=res.getString(2);

            Message p=new Message( id_post,sujet);
           
            
            comments.add(p);
        } 
        return comments;     
    }
    
    
    
    
    public boolean MessageExiste(String contenu) throws SQLException {
        Connection cnx = DataSource.getInstance().getConnection();

        String query = "SELECT * FROM message WHERE id_message=?";
        PreparedStatement preparedStmt = cnx.prepareStatement(query);
        preparedStmt.setString(1, contenu);
        ResultSet rs = preparedStmt.executeQuery();
        
        // Vérifier si le résultat de la requête contient des données
        boolean messageExiste = rs.next();
        
        // Fermer les ressources JDBC
        rs.close();
        preparedStmt.close();
        
        return messageExiste;
    }
    
    private void notifyUser(String message) {
    if (SystemTray.isSupported()) {
        try {
            // Initialiser SystemTray
            SystemTray tray = SystemTray.getSystemTray();

            // Créer une icône pour la notification
            Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
            TrayIcon trayIcon = new TrayIcon(image, "Notification");

            // Ajouter l'icône au SystemTray
            tray.add(trayIcon);

            // Afficher la notification
            trayIcon.displayMessage("Notification", message, TrayIcon.MessageType.INFO);
        } catch (AWTException e) {
            System.err.println("Erreur lors de l'initialisation du SystemTray: " + e);
        }
    } else {
        System.out.println("SystemTray n'est pas pris en charge");
    }
}
}


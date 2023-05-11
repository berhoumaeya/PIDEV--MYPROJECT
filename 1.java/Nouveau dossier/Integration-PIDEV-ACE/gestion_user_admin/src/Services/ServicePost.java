
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;


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
//import java.util.logging.Level;
//import java.util.logging.Logger;


public class ServicePost implements InterfacePost <Post>{
     Connection con=DataSource.getInstance().getConnection();
    
    private Statement ste;

    public ServicePost() {
        
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public void ajouter(Post s) throws SQLException {
            String req = "INSERT INTO `post` ( `sujet`) VALUES "
                    + "( '"+s.getSujet()+"');";

            
            ste.executeUpdate(req);
        String message = "Un nouveau post a été crée!";
        notifyUser(message);
 
    }
    
      @Override 
    public void update(Post p) throws SQLException {
        String req="update post set  sujet=? where id_post=?";
            PreparedStatement ps=con.prepareStatement(req);
            
            ps.setString(1,p.getSujet());
            ps.setInt(2, p.getPostId());
  
            ps.executeUpdate();
        String message = "Le post a été modifié!";
        notifyUser(message);
    }
    
    /**
     *
     * @param p
     * @throws SQLException
     */

    
    @Override
      public void Delete(int id) throws SQLException
     { 
        String req = "DELETE FROM post WHERE id_post=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
         pre.executeUpdate();

        String message = "le post a été supprimé!";
        notifyUser(message);
          
      }
      
 @Override
    public List<Post> readAll() throws SQLException{
        ArrayList<Post> forum=new ArrayList<>();
        
        String req="select id_post,sujet from post";
        
        ResultSet res=ste.executeQuery(req);
  
        while (res.next()) {            
            int id_post=res.getInt(1);
            String sujet=res.getString(2);

            Post p=new Post( id_post,sujet);
           
            forum.add(p);
        } 
        return forum;     
    }

    

    @Override
    public Post findById(int id) throws SQLException {
        String sql="select * from sponsor where id_post=?";
        PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                Post p=new Post();
                p.setPostId(rs.getInt("id_post"));
                p.setSujet(rs.getString("sujet"));

                return p;
            }
            
            else{
                return null; }
       
    }
    
    
    public boolean PostExiste(String sujet) throws SQLException {
        Connection cnx = DataSource.getInstance().getConnection();

        String query = "SELECT * FROM post WHERE sujet=?";
        PreparedStatement preparedStmt = cnx.prepareStatement(query);
        preparedStmt.setString(1, sujet);
        ResultSet rs = preparedStmt.executeQuery();
        boolean PostExiste = rs.next();
        rs.close();
        preparedStmt.close();
        
        return PostExiste;
    }
    
    private void notifyUser(String message) {
    if (SystemTray.isSupported()) {
        try {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
            TrayIcon trayIcon = new TrayIcon(image, "Notification");
            tray.add(trayIcon);
            trayIcon.displayMessage("Notification", message, TrayIcon.MessageType.INFO);
        } catch (AWTException e) {
            System.err.println("Erreur lors de l'initialisation du SystemTray: " + e);
        }
    } else {
        System.out.println("SystemTray n'est pas pris en charge");
    }
}
}


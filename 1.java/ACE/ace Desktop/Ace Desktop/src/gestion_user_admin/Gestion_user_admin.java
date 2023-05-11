/*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
// */
//package gestion_user_admin;
//
//import API.SendSMS;
//import Entite.Admin;
//import Entite.User;
//import Interface.IAdminService;
//import Interface.IUtilisateurService;
//import Services.ServiceAdmin;
//import Services.ServiceUser;
//import Utils.DataSource;
//import java.sql.SQLException;
//
///**
// *
// * @author Lenovo
// */
//public class Gestion_user_admin {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//         DataSource ds = DataSource.getInstance();
//        System.out.println(ds.hashCode());
//       
//
//        
//        IAdminService as = new ServiceAdmin();
//        IUtilisateurService us = new ServiceUser();
//        
//        
//        User u1 = new User(23,"bbbbbbb","bbbb","bbbb","bbbb",261514,"bbbb","admin");
//        //us.ajouterUser(u1);
//        //us.afficherUsers();
//        //us.supprimerUser(u1);
//        //us.modifierUser(u1);
//       
//        Admin a1 = new Admin(3);
//        //as.ajouterAdmin(a1);
//        //as.afficherAdmins();
//        //as.supprimerAdmin(a1);
//        //as.modifierAdmin(a1);
//        
//       // SendSMS sm = new SendSMS();
//      // sm.sendSMS(u1);
//    
//    }
//}

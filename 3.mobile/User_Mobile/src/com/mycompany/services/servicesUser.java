/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.SessionManager;
import com.mycompany.utils.statics;
import java.util.Map;

/**
 *
 * @author wiki
 */
public class servicesUser {

    public static servicesUser instance = null;
    public static boolean resultOk = true;
    private ConnectionRequest req;

    public static servicesUser getInstance() {
        if (instance == null) {
            instance = new servicesUser();
        }
        return instance;
    }

    public servicesUser() {
        req = new ConnectionRequest();
    }

    public void registerUser(TextField nom, TextField telephone, TextField login, TextField address, TextField email, TextField password) {
        String url = statics.BASE_URL + "user/signupUserJson?email=" + email.getText().toString() + "&password=" + password.getText().toString() + "&nom=" + nom.getText().toString() + "&login=" + login.getText().toString() + "&address=" + address.getText().toString() + "&telephone=" + telephone.getText().toString();
        req.setUrl(url);

        if (nom.getText().equals("") && telephone.getText().equals("") && login.getText().equals("") && address.getText().equals("") && email.getText().equals("") && password.getText().equals("")) {
            Dialog.show("Erreur", "Veuillez remplir les champs", "OK", null);
        }

        req.addResponseListener((e) -> {

            byte[] data = (byte[]) e.getMetaData();
            String responseData = new String(data);

            System.out.println("data===>" + responseData);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void login(TextField email, TextField password, Resources rs) {
        String url = statics.BASE_URL + "user/loginJSON?email=" + email.getText().toString() + "&password=" + password.getText().toString();
        req = new ConnectionRequest(url, false);
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json.equals("failed")) {
                    Dialog.show("Echec d'authentification", "Adresse e-mail ou mot de passe incorrect", "OK", null);
                } else {
                    System.out.println("data ==" + json);

                    Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));

                    //Session 
                    float id = Float.parseFloat(user.get("id").toString());
                    SessionManager se = new SessionManager();
                    se.setId((int) id);
                    se.setPassword(user.get("password").toString());
                    se.setEmail(user.get("email").toString());
                    se.setNom(user.get("nom").toString());
                    se.setTelephone(user.get("telephone").toString());
                    se.setAddress(user.get("address").toString());
                    se.setLogin(user.get("login").toString());
                    se.setRoles(user.get("roles").toString());

                    if (user.size() > 0 && se.getRoles().toString().equals("[ROLE_USER]")) {
                        new ProfileForm(rs, se).show();
                    }

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public static void editUser(String email, String password, String nom, String telephone, String address, String login) {
        String url = statics.BASE_URL + "user/editJSON?id=" + String.valueOf(SessionManager.getId()) + "&email=" + email + "&password=" + password + "&nom=" + nom + "&telephone=" + telephone + "&address=" + address +"&login=" + login;

        MultipartRequest req = new MultipartRequest();

        try {
            req.setUrl(url);
            req.setPost(true);
            req.addArgument("id", String.valueOf(SessionManager.getId()));
            req.addArgument("email", email);
            req.addArgument("Mot de passe", password);
            req.addArgument("Nom", nom);
            req.addArgument("Numéro de téléphone", telephone);
            req.addArgument("Address", address);
            req.addArgument("Login", login);

            req.addResponseListener((response) -> {
                byte[] data = (byte[]) response.getMetaData();
                String s = new String(data);
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     public void forgotPassword(TextField email, Resources rs) {
        String url = statics.BASE_URL + "user/sendPasswordByEmail?email=" + email.getText().toString();
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json.equals("failed")) {
                    Dialog.show("Echec d'authentification", "Adresse e-mail ou mot de passe incorrect", "OK", null);
                } else {
                    System.out.println("data ==" + json);

                    Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));

                    //Session 
                    float id = Float.parseFloat(user.get("id").toString());
                    SessionManager se = new SessionManager();
                    se.setId((int) id);
                    se.setPassword(user.get("password").toString());
                    se.setEmail(user.get("email").toString());
                    se.setNom(user.get("nom").toString());
                    se.setTelephone(user.get("telephone").toString());
                    se.setAddress(user.get("address").toString());
                    se.setLogin(user.get("login").toString());
                    se.setRoles(user.get("roles").toString());

                   
              

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

}

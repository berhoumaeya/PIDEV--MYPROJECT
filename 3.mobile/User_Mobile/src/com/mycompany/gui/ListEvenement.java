/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.entities.evenement;
import com.mycompany.gui.recSearch;
import com.mycompany.services.ServiceEvenement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sondes
 */
/*public class ListReclamation  extends Form{

   
  
 public ListReclamation(Form previous) {
     
       setTitle("Liste de catégories");
        setLayout(BoxLayout.y());

       
        ArrayList<category> tasks = ServiceReclamation.getInstance().affichageCategory();
        for (reclamation t : tasks) {
            addElement(t);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

   public void addElement(reclamation t) {
    Container container = new Container(BoxLayout.y());
     Label nom = new Label("Afficher Catégoerie");
     Label IDTxt = new Label("ID : " + t.getId());
    Label contenuTxt = new Label("Contenu : " + t.getContenu());
    Label nomTxt = new Label("nom : " + t.getNom());
    Label emailTxt = new Label("email : " + t.getEmail());
    Label prenomTxt = new Label("prenom : " + t.getPrenom());
    
     Button btnModifier = new Button("Modifier ");
      Button btnSupprimer = new Button("Supprimer ");
      
       container.addComponent(nom);
         container.addComponent( IDTxt);
    container.addComponent(contenuTxt);
    container.addComponent(nomTxt);
    container.addComponent(emailTxt);
    container.addComponent(prenomTxt);
     container.addComponent( btnModifier);
      container.addComponent( btnSupprimer);
      
     btnModifier.addActionListener(e-> new modifier(this).show());
     
     
    add(container);
      revalidate() ;
}
               
}
 */
public class ListEvenement extends Form {

    public ListEvenement(Form previous) {
        ServiceEvenement sp = new ServiceEvenement();
        add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {

            Display.getInstance().callSerially(() -> {

                removeAll();
                setLayout(BoxLayout.y());
                Button searchButton = new Button();
                FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, "Search Icon", 4);
                searchButton.setIcon(searchIcon);

                // back = Image.createImage("/logo.png");
                //back.scaled(1000, 1000);
                add(searchButton);
                Style s = UIManager.getInstance().getComponentStyle("Contenu");

                Form hi = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
                searchButton.addActionListener(e -> {
                    hi.show();
                });
                Button gui_Button_12 = new Button();
                gui_Button_12.setText("search");
                gui_Button_12.setUIID("Label");
                gui_Button_12.setName("Button_12");
                FontImage.setMaterialIcon(gui_Button_12, FontImage.MATERIAL_CIRCLE);
                TextField searchField = new TextField("", "Toolbar Search"); // <1>
                searchField.getHintLabel().setUIID("Title");
                searchField.setUIID("Title");
                searchField.getAllStyles().setAlignment(Component.LEFT);
                hi.getToolbar().setTitleComponent(searchField);
                // FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
                ArrayList<evenement> list1;
                list1 = ServiceEvenement.getInstance().affichageEvenement();
                //hi.add(gui_Button_12);
                searchField.addDataChangeListener((i1, i2) -> { // <2>
                    String t = searchField.getText();

                    if (t.length() < 1) {
                        for (Component cmp : hi.getContentPane()) {
                            cmp.setHidden(false);
                            cmp.setVisible(true);
                        }
                    } else {
                        t = t.toLowerCase();
                        for (Component cmp : hi.getContentPane()) {
                            String val = null;
                            //hi.add(gui_Button_12);
                            if (cmp instanceof Label) {
                                val = ((Label) cmp).getText();
                            } else {
                                if (cmp instanceof TextArea) {
                                    val = ((TextArea) cmp).getText();
                                } else {
                                    val = (String) cmp.getPropertyValue("text");
                                }
                            }
                            boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
                            cmp.setHidden(!show); // <3>
                            cmp.setVisible(show);
                            //hi.add(gui_Button_12);
                        }
                    }
                    hi.getContentPane().animateLayout(250);
                    //  hi.add(gui_Button_12);
                });
                hi.getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
                    searchField.startEditingAsync(); // <4>
                    //    hi.add(gui_Button_12);
                });

                for (evenement rec : list1) {
                    Label b = new Label(rec.getNom_event());

                    hi.add(b);

                    b.addPointerPressedListener(e -> {

                        if (rec.getNom_event() == b.getText()) {
                            new recSearch(previous).show();
                        }
                    });
                }
               /* Button mapButton = new Button("Afficher la carte");
                mapButton.addActionListener(e -> {
                    MapForm mapForm = new MapForm();
                    mapForm.show();
                });
                add(mapButton);*/
                Button refreshButton = new Button();
                FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_REFRESH, UIManager.getInstance().getComponentStyle("Button"));
                refreshButton.setIcon(icon1);

                refreshButton.addActionListener(e -> new ListEvenement(previous).show());
                add(refreshButton);

                List<evenement> listerec = sp.affichageEvenement();
                for (evenement p : listerec) {

                    MultiButton m = new MultiButton();

                    m.setTextLine1("duree:" + p.getDuree());
                    m.setTextLine2("Nom:" + p.getNom_event());
                    m.setTextLine3("prix:" + p.getPrix());
                   // m.setTextLine4("datedeb:" + p.getDate_deb());
                  //  m.setTextLine4("datefin:" + p.getDate_fin());

                    add(m);
                    Button btnModifier = new Button();
                    
                    Button mapsButton = new Button("Map");
mapsButton.addActionListener(e -> {
   String url = "https://www.google.com/maps/place/tunis"; // replace with your desired URL
    BrowserComponent browser = new BrowserComponent();
    browser.setURL(url);
    Form browserForm = new Form("Browser");
    browserForm.setLayout(new BorderLayout());
    browserForm.add(BorderLayout.CENTER, browser);
    browserForm.show();
    
}); add(mapsButton);
                    FontImage.setMaterialIcon(btnModifier, FontImage.MATERIAL_EDIT);
                    m.addComponent(BorderLayout.SOUTH, btnModifier);
                    btnModifier.addActionListener(e -> {
                        new modifierEve(this).show();
                    });

                    Button btnSupprimer = new Button();
                    Image icon = FontImage.createMaterial(FontImage.MATERIAL_DELETE, "ButtonIcon", 5);
                    btnSupprimer.setIcon(icon);
                    m.addComponent(BorderLayout.WEST, btnSupprimer);
                    btnSupprimer.addActionListener(e -> {
                        Dialog dig = new Dialog("Suppression");
                        if (dig.show("Suppression", "Êtes-vous sûr de vouloir supprimer cet élément ?", "Annuler", "Oui")) {
                            dig.dispose();
                        } else {
                            dig.dispose();
                            if (ServiceEvenement.getInstance().deleteEvemenent(p.getId())) {
                                // Élément supprimé avec succès
                            }
                        }
                    });
                }

                revalidate();
            });
        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    Style style = getStyle();
        style.setBgColor(0xD6DDDF);
    }

}

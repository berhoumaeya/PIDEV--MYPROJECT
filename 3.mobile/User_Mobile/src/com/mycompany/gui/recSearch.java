/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.gui;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.entities.evenement;
import com.mycompany.services.ServiceEvenement;
import java.util.List;

/**
 *
 * @author HP
 */
public class recSearch extends Form {
     Form current;
    public  recSearch(Form previous) {
        current = this;
        
       
      ServiceEvenement sp = new ServiceEvenement();
      add(new InfiniteProgress());
                Display.getInstance().scheduleBackgroundTask(()-> {
                    
                    Display.getInstance().callSerially(() -> {
                           
                        removeAll();
                      setLayout(BoxLayout.y());
                     Button searchButton = new Button();
FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, "Search Icon", 4);
searchButton.setIcon(searchIcon);
       
         
       add(searchButton);

       
       
             
      

                      
                      
                      
                      
                      
                
                      
                       
        Button refreshButton = new Button();
FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_REFRESH, UIManager.getInstance().getComponentStyle("Button"));
refreshButton.setIcon(icon1);

       
       refreshButton.addActionListener(e-> new ListEvenement(previous).show());
        add(refreshButton);

                        List<evenement> listerec = sp.affichageEvenement();
                       
                            
                            
                   for (evenement c : listerec) {
    MultiButton m = new MultiButton();
    
   m.setTextLine1("duree:" + c.getDuree());
                    m.setTextLine2("Nom:" + c.getNom_event());
                    m.setTextLine3("prix:" + c.getPrix());
    
    add(m);
}
                            
                            
                          
                           
                            
                         
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                     revalidate() ;   
                    });
                });
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       
       
       
    
                        }
}


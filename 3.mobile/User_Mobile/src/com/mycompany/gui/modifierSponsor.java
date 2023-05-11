/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Sponsor;
import com.mycompany.services.ServiceSponsor;

/**
 *
 * @author HP
 */
public class modifierSponsor extends Form {

    public modifierSponsor(Form previous) {
        setTitle("Modifier Sponosr");
        setLayout(BoxLayout.y());

        TextField ID = new TextField("", "ID");
        ID.setUIID("TextFieldBlack");

        TextField tintitule = new TextField("", "intitule");
        tintitule.setUIID("TextFieldBlack");

        TextField tdureecontrat = new TextField("", "duree_contrat");
        tdureecontrat.setUIID("TextFieldBlack");

        Picker tfDateDeb = new Picker();
        tfDateDeb.setType(Display.PICKER_TYPE_DATE);

        Picker tfDateFin = new Picker();
        tfDateFin.setType(Display.PICKER_TYPE_DATE);
        Button btnValider = new Button("Modifier ");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tintitule.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        float id = Float.parseFloat(ID.getText().toString());
                        Sponsor t;
                        int dureeContrat = Integer.parseInt(tdureecontrat.getText());
                        String intitule = tintitule.getText();
                        //  t = new evenement((int) id,Float.parseFloat(tfDuree.getText()), Float.parseFloat(tfPrix.getText()),tfNomEvent.getText(), tfDateDeb.getDate(), tfDateFin.getDate());
                        t = new Sponsor((int) id, Integer.parseInt(tdureecontrat.getText()), tintitule.getText(), tfDateDeb.getDate(), tfDateFin.getDate());
                        if (ServiceSponsor.getInstance().modifierSponsor(t)) {
                            Dialog.show("Success", "Congrats!!", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        addAll(ID, tintitule, tdureecontrat, tfDateDeb, tfDateFin, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}

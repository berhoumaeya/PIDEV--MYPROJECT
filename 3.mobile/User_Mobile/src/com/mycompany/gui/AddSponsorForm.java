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
import java.util.Date;

/**
 *
 * @author Sondes
 */
public class AddSponsorForm extends Form {

    public AddSponsorForm(Form previous) {
        setTitle("Ajoute un sponsor ");
        setLayout(BoxLayout.y());

        // Create input fields and button
        TextField tfintitule = new TextField("", "intitule");
        tfintitule.setUIID("TextFieldBlack");
        TextField tfdureecontrat = new TextField("", "duree_contrat");
        tfdureecontrat.setUIID("TextFieldBlack");

        Picker tfDateDeb = new Picker();
        tfDateDeb.setType(Display.PICKER_TYPE_DATE);

        Picker tfDateFin = new Picker();
        tfDateFin.setType(Display.PICKER_TYPE_DATE);

        Button btnAdd = new Button("Ajouter");

        // Add action listener to button
        btnAdd.addActionListener(evt -> {
            if (tfdureecontrat.getText().length() == 0
                    || tfintitule.getText().length() == 0 || tfDateDeb.getText().length() == 0
                    || tfDateFin.getText().length() == 0) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    // Create new sponsor object and add it to the server
                    Sponsor ev = new Sponsor(
                            tfintitule.getText(), Integer.parseInt(tfdureecontrat.getText()), tfDateDeb.getDate(), tfDateFin.getDate());

                    if (ServiceSponsor.getInstance().addSponsor(ev)) {
                        Dialog.show("Success", "Congrats", new Command("OK"));
                    } else {
                        Dialog.show("Error", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("Error", "Invalid input", new Command("OK"));
                }
            }
        });

        // Add input fields and button to the form
        addAll(tfintitule, tfdureecontrat, tfDateDeb, tfDateFin, btnAdd);

        // Add back button to the toolbar
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack());

    }

}

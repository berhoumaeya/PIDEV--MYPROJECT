/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.services.servicesUser;
import java.io.IOException;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {

    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;
    String uurlI;

    public ProfileForm(Resources res, SessionManager se) {
        super("", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Mon Profil");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res, se);

        tb.addSearchCommand(e -> {
        });

        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                FlowLayout.encloseCenter(
                                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
                        )
                )
        ));

        TextField nom = new TextField(se.getNom(), "Nom", 20, TextField.ANY);
        nom.setUIID("TextFieldBlack");
        addStringValue("Nom", nom);

        TextField email = new TextField(se.getEmail(), "Adresse E-mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("Adresse E-mail", email);

        TextField address = new TextField(se.getAddress(), "Address", 20, TextField.ANY);
        address.setUIID("TextFieldBlack");
        addStringValue("Address", address);

        TextField login = new TextField(se.getLogin(), "Login", 20, TextField.ANY);
        login.setUIID("TextFieldBlack");
        addStringValue("Login", login);

        TextField telephone = new TextField(se.getTelephone(), "Numéro de téléphone", 20, TextField.ANY);
        telephone.setUIID("TextFieldBlack");
        addStringValue("Numéro de téléphone", telephone);

        TextField password = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Mot de passe", password);

        Button modif = new Button("Modifier");
        addStringValue("", modif);

            modif.addActionListener((edit) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInfiniteBlocking();
            servicesUser.editUser(email.getText(), password.getText(), nom.getText(), telephone.getText(),address.getText(),login.getText());
            SessionManager.setEmail(email.getText());
            SessionManager.setPassword(password.getText());
            SessionManager.setNom(nom.getText());
            SessionManager.setTelephone(telephone.getText());
            SessionManager.setAddress(address.getText());
            SessionManager.setLogin(login.getText());

            Dialog.show("Profile utilisateur", "Modifications des coordonnées avec succès", "OK", null);
            ipDlg.dispose();
            refreshTheme();
        });
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}

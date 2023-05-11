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

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.servicesUser;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {

    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField nom = new TextField("", "Nom", 20, TextField.ANY);
        TextField telephone = new TextField("", "Telephone", 20, TextField.ANY);
        TextField login = new TextField("", "Login", 20, TextField.ANY);
         TextField address = new TextField("", "Address", 20, TextField.ANY);
        TextField email = new TextField("", "Adresse e-mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        nom.setSingleLineTextArea(false);
        telephone.setSingleLineTextArea(false);
        login.setSingleLineTextArea(false);
                address.setSingleLineTextArea(false);

        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        
        Button next = new Button("S'inscrire");
        Button signIn = new Button("S'identifier");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Vous avez déjà un compte?");
        
        Container content = BoxLayout.encloseY(
                new Label("", "LogoLabel"),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(telephone),
                createLineSeparator(),
                new FloatingHint(login),
                createLineSeparator(),
                new FloatingHint(address),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator()

        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener((e) -> {
            servicesUser.getInstance().registerUser(nom, telephone, login,address, email, password);
            Dialog.show("S'inscrire ", "Votre compte a été ajouté avec succès","OK",null);
            new SignInForm(res).show();
        });
    }
    
}

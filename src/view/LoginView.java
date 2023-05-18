package view;
import Controller.LoginController;

import net.sds.mvvm.bindings.Binder;

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.*;

public class LoginView {

    private JTextField emailField;
    private JPasswordField passwordField;

    private JButton loginBtn;
    private JButton registerBtn;
    private JLabel jcomp4;
    private JLabel jcomp6;
    private JPanel mainPanel;
    private JComboBox langChoice;
    LoginController loginController;
    String[] langChoiceItems = {"en", "it", "fr"};;
    JFrame frame = new JFrame ("Log in");
    public LoginView(LoginController loginController) {
        loginBtn = new JButton ("Login");
        registerBtn = new JButton ("Register");
        emailField = new JTextField (4);
        jcomp4 = new JLabel ("Email");
        passwordField = new JPasswordField (5);
        jcomp6 = new JLabel ("Password");
        langChoice = new JComboBox<>(langChoiceItems);
        this.loginController = loginController;
        try {
            Binder.bind(this, loginController);
        } catch (Exception E) {
            E.printStackTrace();
        }
        //adjust size and set layout
        mainPanel = new JPanel();
        mainPanel.setPreferredSize (new Dimension (944, 563));
        mainPanel.setLayout (null);

        //add components
        mainPanel.add (loginBtn);
        mainPanel.add(langChoice);
        mainPanel.add (registerBtn);
        mainPanel.add (emailField);
        mainPanel.add (jcomp4);
        mainPanel.add (passwordField);
        mainPanel.add (jcomp6);

        //set component bounds (only needed by Absolute Positioning)
        loginBtn.setBounds (115, 150, 100, 20);
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginController.mainVM.lang = langChoice.getItemAt(langChoice.getSelectedIndex()).toString();
                loginController.logIn();
            }
        });

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginController.register();
            }
        });


        registerBtn.setBounds (290, 155, 100, 20);
        emailField.setBounds (205, 25, 230, 25);
        jcomp4.setBounds (105, 25, 100, 25);
        passwordField.setBounds (205, 70, 225, 25);
        jcomp6.setBounds (105, 70, 100, 25);
        langChoice.setBounds(40, 295, 100, 25);
        langChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginController.mainVM.lang = langChoice.getItemAt(langChoice.getSelectedIndex()).toString();
                changeLanguage(loginController.mainVM.lang);
            }
        });
    }


    public void init () {
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (mainPanel);
        frame.pack();
        frame.setVisible (true);
        changeLanguage(loginController.mainVM.lang);
    }

    public String getEmail(){
        return emailField.getText();
    }
    public String getPassword(){
        return new String(passwordField.getPassword());
    }

    public void close(){
        ///frame.removeAll();
        frame.setVisible(false);
    }

    public void changeLanguage(String lang){
        if (lang != null){
            Locale locale = new Locale(lang.toLowerCase());
            ResourceBundle resourceBundle = ResourceBundle.getBundle("newResourceBundle", locale);
            //System.out.println(resourceBundle.getString("playBtn"));
            loginBtn.setText(resourceBundle.getString("loginBtn"));
            registerBtn.setText(resourceBundle.getString("registerBtn"));
            jcomp6.setText(resourceBundle.getString("passwordField"));
        }
    }

}

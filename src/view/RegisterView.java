package view;

import Controller.RegisterController;
import models.UserType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterView {
    private JButton btnRegister;
    private JLabel jcomp2;
    private JTextField fldEmail;
    private JLabel jcomp4;
    private JTextField fldPassword;
    private JLabel jcomp6;
    private JTextField fldName;
    private RegisterController registerController;

    private JLabel jcomp8;
    private JTextField fldRole;

    JFrame frame;
    JPanel mainPanel;

    public RegisterView(RegisterController registerController) {
        this.registerController = registerController;
        frame = new JFrame("Register");
        mainPanel = new JPanel();
        //construct components
        btnRegister = new JButton ("Register");
        jcomp2 = new JLabel ("Email");
        fldEmail = new JTextField (5);
        jcomp4 = new JLabel ("Password");
        fldPassword = new JTextField (5);
        jcomp6 = new JLabel ("Name");
        fldName = new JTextField (5);
        jcomp8 = new JLabel ("Role");
        fldRole = new JTextField (5);

        //adjust size and set layout
        mainPanel.setPreferredSize (new Dimension(944, 563));
        mainPanel.setLayout (null);

        //add components
        mainPanel.add (btnRegister);
        mainPanel.add (jcomp2);
        mainPanel.add (fldEmail);
        mainPanel.add (jcomp4);
        mainPanel.add (fldPassword);
        mainPanel.add (jcomp6);
        mainPanel.add (fldName);
        mainPanel.add (jcomp8);
        mainPanel.add (fldRole);
        //set component bounds (only needed by Absolute Positioning)
        btnRegister.setBounds (45, 175, 210, 55);
        jcomp2.setBounds (10, 20, 100, 25);
        fldEmail.setBounds (110, 20, 150, 25);
        jcomp4.setBounds (10, 50, 100, 25);
        fldPassword.setBounds (110, 50, 150, 25);
        jcomp6.setBounds (5, 80, 100, 25);
        fldName.setBounds (110, 85, 150, 25);
        jcomp8.setBounds (5, 115, 100, 25);
        fldRole.setBounds (110, 120, 150, 25);
    }


    public void init () {
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (registerController.checkEmail(fldEmail.getText())){
                    registerController.addUser();
                }
            }
        });
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (mainPanel);
        frame.pack();
        frame.setVisible (true);
    }

    public String getEmail(){
        return fldEmail.getText();
    }
    public String getPassword(){
        return fldPassword.getText();
    }
    public String getName(){
        return fldName.getText();
    }
    public UserType getRole(){
        if (fldRole.getText().equals("admin"))
            return UserType.ADMIN;
        return UserType.PLAYER;
    }
    public void close(){
        frame.setVisible(false);
    }

}

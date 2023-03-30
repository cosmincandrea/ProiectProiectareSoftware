package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class LoginView extends JPanel {
    private JButton loginBtn;
    private JButton registerBtn;
    private JTextField emailField;
    private JLabel jcomp4;
    private JPasswordField passwordField;
    private JLabel jcomp6;

    public LoginView() {
        //construct components
        loginBtn = new JButton ("Login");
        registerBtn = new JButton ("Register");
        emailField = new JTextField (4);
        jcomp4 = new JLabel ("Email");
        passwordField = new JPasswordField (5);
        jcomp6 = new JLabel ("Password");

        //adjust size and set layout
        setPreferredSize (new Dimension (944, 563));
        setLayout (null);

        //add components
        add (loginBtn);
        add (registerBtn);
        add (emailField);
        add (jcomp4);
        add (passwordField);
        add (jcomp6);

        //set component bounds (only needed by Absolute Positioning)
        loginBtn.setBounds (115, 150, 100, 20);
        registerBtn.setBounds (290, 155, 100, 20);
        emailField.setBounds (205, 25, 230, 25);
        jcomp4.setBounds (105, 25, 100, 25);
        passwordField.setBounds (205, 70, 225, 25);
        jcomp6.setBounds (105, 70, 100, 25);
    }


    public void init () {
        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new LoginView());
        frame.pack();
        frame.setVisible (true);
    }
}

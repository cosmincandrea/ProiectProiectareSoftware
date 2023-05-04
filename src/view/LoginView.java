package view;
import ViewModel.LoginVM;

import net.sds.mvvm.bindings.Bind;
import net.sds.mvvm.bindings.Binder;
import net.sds.mvvm.bindings.BindingType;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginView {

    @Bind(value = "text", target = "usernameTf.value", type = BindingType.BI_DIRECTIONAL)
    private JTextField emailField;
    @Bind(value = "text", target = "passwordTf.value", type = BindingType.BI_DIRECTIONAL)
    private JPasswordField passwordField;

    private JButton loginBtn;
    private JButton registerBtn;
    private JLabel jcomp4;
    private JLabel jcomp6;
    private JPanel mainPanel;
    LoginVM loginVM;
    JFrame frame = new JFrame ("Log in");
    public LoginView(LoginVM loginVM) {
        //construct components
        loginBtn = new JButton ("Login");
        registerBtn = new JButton ("Register");
        emailField = new JTextField (4);
        jcomp4 = new JLabel ("Email");
        passwordField = new JPasswordField (5);
        jcomp6 = new JLabel ("Password");
        this.loginVM = loginVM;
        try {
            Binder.bind(this, loginVM);
        } catch (Exception E) {
            E.printStackTrace();
        }
        //adjust size and set layout
        mainPanel = new JPanel();
        mainPanel.setPreferredSize (new Dimension (944, 563));
        mainPanel.setLayout (null);

        //add components
        mainPanel.add (loginBtn);
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
                loginVM.logIn();
            }
        });

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginVM.register();
            }
        });

        registerBtn.setBounds (290, 155, 100, 20);
        emailField.setBounds (205, 25, 230, 25);
        jcomp4.setBounds (105, 25, 100, 25);
        passwordField.setBounds (205, 70, 225, 25);
        jcomp6.setBounds (105, 70, 100, 25);

    }


    public void init () {

        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (mainPanel);
        frame.pack();
        frame.setVisible (true);
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

}

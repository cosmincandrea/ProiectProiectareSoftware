package view;

import Controller.MainController;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView {
    MainController mainVM;
    private JButton btnPlay;
    private JLabel labelUserGreet;
    private JButton btnCRUD;

    private JPanel mainPanel;
    private JFrame frame;
    private JComboBox comboLevel;
    private JLabel jcomp5;
    private JButton btnReport;

    public MainView(MainController mainVM) {
        //construct components
        String[] comboLevelItems = {"5", "6", "7", "8", "9", "10"};

        mainPanel = new JPanel();
        frame = new JFrame();
        btnPlay = new JButton ("PlayGame");
        comboLevel = new JComboBox (comboLevelItems);
        jcomp5 = new JLabel("Level");
        btnReport = new JButton ("Show Graph");

        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int level = Integer.parseInt(comboLevel.getSelectedItem().toString());
                mainVM.play(level);
            }
        });

        btnReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        labelUserGreet = new JLabel ("Hello Cosmin, welcome back");
        btnCRUD = new JButton ("CRUD ON USERS");


        this.mainVM = mainVM;

        //adjust size and set layout
        mainPanel.setPreferredSize (new Dimension(944, 563));
        mainPanel.setLayout (null);

        btnCRUD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainVM.startCRUDoperations();
            }
        });

        //add components
        mainPanel.add(comboLevel);
        mainPanel.add (btnPlay);
        mainPanel.add (labelUserGreet);
        mainPanel.add (btnCRUD);
        mainPanel.add(jcomp5);
        mainPanel.add(btnReport);


        //set component bounds (only needed by Absolute Positioning)
        comboLevel.setBounds (360, 110, 100, 25);
        btnPlay.setBounds (55, 110, 170, 25);
        labelUserGreet.setBounds (60, 0, 500, 150);
        btnCRUD.setBounds (55, 170, 170, 30);
        jcomp5.setBounds (275, 110, 100, 25);
        btnReport.setBounds(50, 245, 175, 30);
    }



    public void setGreeting(User user){
        labelUserGreet.setText("Welcome back " + user.getName() + ", your best current score is "+ user.getScore());
    }

    public void init () {
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (mainPanel);
        frame.pack();
        frame.setVisible (true);
    }
    public void hideCRUDBtn(){
        btnCRUD.setVisible(false);
    }

}

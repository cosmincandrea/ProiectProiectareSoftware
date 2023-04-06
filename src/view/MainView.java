package view;

import Presenter.MainPresenter;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView {
    MainPresenter mainPresenter;
    private JButton btnPlay;
    private JLabel labelUserGreet;
    private JButton btnCRUD;

    private JPanel mainPanel;
    private JFrame frame;
    private JComboBox comboLevel;
    private JLabel jcomp5;

    public MainView(MainPresenter mainPresenter) {
        //construct components
        String[] comboLevelItems = {"3", "4", "5"};

        mainPanel = new JPanel();
        frame = new JFrame();
        btnPlay = new JButton ("PlayGame");
        comboLevel = new JComboBox (comboLevelItems);
        jcomp5 = new JLabel("Level");

        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int level = Integer.parseInt(comboLevel.getSelectedItem().toString());
                mainPresenter.play(level);
            }
        });


        labelUserGreet = new JLabel ("Hello Cosmin, welcome back");
        btnCRUD = new JButton ("CRUD ON USERS");


        this.mainPresenter = mainPresenter;

        //adjust size and set layout
        mainPanel.setPreferredSize (new Dimension(944, 563));
        mainPanel.setLayout (null);

        btnCRUD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPresenter.startCRUDoperations();
            }
        });

        //add components
        mainPanel.add(comboLevel);
        mainPanel.add (btnPlay);
        mainPanel.add (labelUserGreet);
        mainPanel.add (btnCRUD);
        mainPanel.add(jcomp5);


        //set component bounds (only needed by Absolute Positioning)
        comboLevel.setBounds (360, 110, 100, 25);
        btnPlay.setBounds (55, 110, 170, 25);
        labelUserGreet.setBounds (60, 0, 500, 150);
        btnCRUD.setBounds (55, 170, 170, 30);
        jcomp5.setBounds (275, 110, 100, 25);
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

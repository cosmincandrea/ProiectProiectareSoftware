package view;

import Controller.MainController;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import models.repo.UserDao;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;

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
                double[] xValues = new double[101] ;
                double[] yValues = new double[101];
                UserDao userDao = new UserDao();
                List<User> users = userDao.getUsers();
                for(int i = 0; i<101; i++) {
                    yValues[i] = 0;
                    xValues[i] = i;
                }
                for (User user : users){
                    int score = user.getScore();
                    yValues[score]+=5;

                }



                // Create a dataset
                DefaultXYDataset dataset = new DefaultXYDataset();
                double[][] data = {xValues, yValues};
                dataset.addSeries("Data", data);

                // Create the line chart
                JFreeChart chart = ChartFactory.createXYLineChart(
                        "Game Chart",
                        "Score",
                        "Gamers",
                        dataset,
                        PlotOrientation.VERTICAL,
                        true,
                        true,
                        false
                );

                // Create a chart panel
                ChartPanel chartPanel = new ChartPanel(chart);
                // Create a chart frame
                ChartFrame chartFrame = new ChartFrame("Line Graph Game", chartPanel.getChart());
                chartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                chartFrame.pack();
                chartFrame.setVisible(true);
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
        String lang = mainVM.lang;
        Locale locale = new Locale(lang.toLowerCase());
        ResourceBundle resourceBundle = ResourceBundle.getBundle("newResourceBundle", locale);
        labelUserGreet.setText(resourceBundle.getString("greetField") +" "+ user.getScore());
    }

    public void init () {
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (mainPanel);
        frame.pack();
        changeLanguage(mainVM.lang);
        frame.setVisible (true);
    }
    public void hideCRUDBtn(){
        btnCRUD.setVisible(false);
    }
    public void changeLanguage(String lang){
        if (lang != null){
            Locale locale = new Locale(lang.toLowerCase());
            ResourceBundle resourceBundle = ResourceBundle.getBundle("newResourceBundle", locale);
            //System.out.println(resourceBundle.getString("playBtn"));
            btnPlay.setText(resourceBundle.getString("playBtn"));
            btnCRUD.setText(resourceBundle.getString("crudBtn"));
            btnReport.setText(resourceBundle.getString("grafBtn"));
            jcomp5.setText(resourceBundle.getString("levelField"));
        }
    }

}

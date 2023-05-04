package view;

import ViewModel.CRUDVM;
import models.User;
import models.UserType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CRUDview {

    CRUDVM CRUDVM;
    private JButton btnRefresh;
    private JButton btnInsert;
    private JButton btnDelete;
    private JList jcomp4;
    private JTable jTable;
    private JLabel jcomp5;
    private JLabel jcomp6;
    private JLabel jcomp7;
    private JTextField fldName;
    private JTextField fldEmail;
    private JTextField fldScore;
    private JButton btnUpdate;
    private JLabel jcomp12;
    private JTextField fldPassword;
    private JLabel jcomp14;
    private JTextField fldRole;
    JFrame frame;
    JPanel mainPanel;

    public CRUDview(CRUDVM CRUDVM) {
        //construct preComponents
        //String[] jcomp4Items = {"Item 1", "Item 2", "Item 3"};

        this.CRUDVM = CRUDVM;
        frame = new JFrame("CRUD Operations");
        mainPanel = new JPanel();

        //construct components
        btnRefresh = new JButton ("Refresh");
        btnInsert = new JButton ("Insert");
        btnDelete = new JButton ("Delete");
        jTable = new JTable();
        jcomp5 = new JLabel ("Name");
        jcomp6 = new JLabel ("Email");
        jcomp7 = new JLabel ("Score");
        fldName = new JTextField (5);
        fldEmail = new JTextField (5);
        fldScore = new JTextField (5);
        btnUpdate = new JButton ("Update");
        jcomp12 = new JLabel ("Password");
        fldPassword = new JTextField (5);
        jcomp14 = new JLabel ("Role");
        fldRole = new JTextField (5);

        //adjust size and set layout
        mainPanel.setPreferredSize (new Dimension(944, 563));
        mainPanel.setLayout (null);

        //add components
        mainPanel.add (btnRefresh);
        mainPanel.add (btnInsert);
        mainPanel.add (btnDelete);
        JScrollPane scr = new JScrollPane(jTable);
        mainPanel.add (scr);
        mainPanel.add (jcomp5);
        mainPanel.add (jcomp6);
        mainPanel.add (jcomp7);
        mainPanel.add (fldName);
        mainPanel.add (fldEmail);
        mainPanel.add (fldScore);
        mainPanel.add (btnUpdate);
        mainPanel.add (jcomp12);
        mainPanel.add (fldPassword);
        mainPanel.add (jcomp14);
        mainPanel.add (fldRole);

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jTable.getSelectedRow();
                User user = new User();
                String password = jTable.getValueAt(index, 2).toString();
                String name = jTable.getValueAt(index, 0).toString();
                String email = jTable.getValueAt(index, 1).toString();
                String role = jTable.getValueAt(index, 4).toString();
                int score = Integer.parseInt(jTable.getValueAt(index, 3).toString());
                if (role.equals("admin"))
                    user.setRole(UserType.ADMIN);
                else
                    user.setRole(UserType.PLAYER);
                user.setPassword(password);
                user.setName(name);
                user.setEmail(email);
                user.setScore(score);
                CRUDVM.deleteUser(user);
            }
        });


        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jTable.getSelectedRow();
                User user = new User();
                String password = jTable.getValueAt(index, 2).toString();
                String name = jTable.getValueAt(index, 0).toString();
                String email = jTable.getValueAt(index, 1).toString();
                String role = jTable.getValueAt(index, 4).toString();
                int score = Integer.parseInt(jTable.getValueAt(index, 3).toString());
                if (role.equals("admin"))
                    user.setRole(UserType.ADMIN);
                else
                    user.setRole(UserType.PLAYER);
                user.setPassword(password);
                user.setName(name);
                user.setEmail(email);
                user.setScore(score);
                CRUDVM.updateUser(user);
            }
        });


        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDVM.refresh();
            }
        });

        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                User user = new User();
                String password = fldPassword.getText();
                String name = fldName.getText();
                String email = fldEmail.getText();
                String role =fldRole.getText();
                int score = Integer.parseInt(fldScore.getText());
                if (role.equals("admin"))
                    user.setRole(UserType.ADMIN);
                else
                    user.setRole(UserType.PLAYER);
                user.setPassword(password);
                user.setName(name);
                user.setEmail(email);
                user.setScore(score);
                CRUDVM.insertUser(user);
            }
        });

        //set component bounds (only needed by Absolute Positioning)
        btnRefresh.setBounds (675, 10, 175, 30);
        btnInsert.setBounds (680, 140, 175, 40);
        btnDelete.setBounds (675, 70, 180, 35);
        scr.setBounds (10, 10, 525, 240);
        jcomp5.setBounds (10, 270, 100, 25);
        jcomp6.setBounds (10, 310, 100, 25);
        jcomp7.setBounds (5, 355, 100, 25);
        fldName.setBounds (65, 275, 230, 30);
        fldEmail.setBounds (65, 315, 230, 30);
        fldScore.setBounds (65, 360, 235, 25);
        btnUpdate.setBounds (645, 235, 280, 80);
        jcomp12.setBounds (5, 400, 100, 25);
        fldPassword.setBounds (70, 400, 230, 25);
        jcomp14.setBounds (5, 445, 100, 25);
        fldRole.setBounds (70, 445, 235, 25);
    }

    public void setTableColumns(String[][] data, String[] column){
        jTable.setModel(new DefaultTableModel(data,column));
    }




    public void init () {
        frame.getContentPane().add (mainPanel);
        frame.pack();
        frame.setVisible (true);
    }
}

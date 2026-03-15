package gui;

import javax.swing.*;


public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("Student Management System");
        setSize(500,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new java.awt.GridLayout(7,1,10,10));
        
        JLabel title = new JLabel("Student Management System");
        title.setBounds(120,30,300,30);
        add(title);

        JButton addBtn = new JButton("Add Student");
        
        addBtn.setBounds(150,80,200,40);
        add(addBtn);
        addBtn.addActionListener(e -> {
            new AddStudentFrame().setVisible(true);
        });
        
        
        
        JButton viewBtn = new JButton("View Students");
        viewBtn.setBounds(150,130,200,40);
        add(viewBtn);
        viewBtn.addActionListener(e -> {
            new ViewStudentsFrame().setVisible(true);
        });

      

    }

}
package gui;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    public DashboardPanel(MainFrame frame){

        setLayout(new GridLayout(3,1,20,20));

        setBorder(BorderFactory.createEmptyBorder(100,200,100,200));

        JButton addBtn = new JButton("Add Student");
        JButton viewBtn = new JButton("View Students");
        JButton exitBtn = new JButton("Exit");

        addBtn.setFont(new Font("Segoe UI",Font.BOLD,16));
        viewBtn.setFont(new Font("Segoe UI",Font.BOLD,16));
        exitBtn.setFont(new Font("Segoe UI",Font.BOLD,16));

        add(addBtn);
        add(viewBtn);
        add(exitBtn);

        addBtn.addActionListener(e -> frame.showPanel("add"));
        viewBtn.addActionListener(e -> frame.showPanel("view"));
        exitBtn.addActionListener(e -> System.exit(0));
    }
}
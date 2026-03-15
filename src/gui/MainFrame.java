package gui;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
	
	CardLayout layout = new CardLayout();
    JPanel container = new JPanel(layout);
    
    public MainFrame() {

    	setTitle("Student Management System");
        setSize(800,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        container.add(new DashboardPanel(this),"dashboard");
        container.add(new AddStudentPanel(this),"add");
        container.add(new ViewStudentsPanel(this),"view");

        add(container);

        layout.show(container,"dashboard");
    }

    public void showPanel(String name){
        layout.show(container,name);
    

    }

}
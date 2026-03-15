package gui;

import javax.swing.*;
import database.StudentDAO;
import model.Student;
import java.awt.*;

public class AddStudentPanel extends JPanel {

    public AddStudentPanel(MainFrame frame){

        setLayout(new GridLayout(5,2,10,10));

        setBorder(BorderFactory.createEmptyBorder(40,200,40,200));

        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField majorField = new JTextField();

        JButton saveBtn = new JButton("Save");
        JButton backBtn = new JButton("Back");

        add(new JLabel("Name"));
        add(nameField);

        add(new JLabel("Email"));
        add(emailField);

        add(new JLabel("Major"));
        add(majorField);

        add(saveBtn);
        add(backBtn);

        saveBtn.addActionListener(e -> {

            Student student = new Student(
                    nameField.getText(),
                    emailField.getText(),
                    majorField.getText()
            );

            new StudentDAO().addStudent(student);

            JOptionPane.showMessageDialog(this,"Student Added");

            nameField.setText("");
            emailField.setText("");
            majorField.setText("");

        });

        backBtn.addActionListener(e -> frame.showPanel("dashboard"));
    }
}
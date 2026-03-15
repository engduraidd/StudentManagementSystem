package gui;

import javax.swing.*;
import database.StudentDAO;
import model.Student;

public class AddStudentFrame extends JFrame {

    JTextField nameField;
    JTextField emailField;
    JTextField majorField;

    public AddStudentFrame() {

        setTitle("Add Student");
        setSize(350,250);
        setLocationRelativeTo(null);
        

        setLayout(new java.awt.GridLayout(4,2,10,10));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Major:"));
        majorField = new JTextField();
        add(majorField);

        JButton saveBtn = new JButton("Save Student");
        add(saveBtn);

        saveBtn.addActionListener(e -> saveStudent());

    }

    private void saveStudent() {

        String name = nameField.getText();
        String email = emailField.getText();
        String major = majorField.getText();

        Student student = new Student(name,email,major);

        StudentDAO dao = new StudentDAO();
        dao.addStudent(student);

        JOptionPane.showMessageDialog(this,"Student Added!");

        dispose();
    }
}
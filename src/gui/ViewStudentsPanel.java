package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

import database.DBConnection;

public class ViewStudentsPanel extends JPanel {

	JTable table;
    DefaultTableModel model;
    JTextField searchField;

    public ViewStudentsPanel(MainFrame frame){

        setLayout(new BorderLayout());

        // SEARCH PANEL
        JPanel topPanel = new JPanel();

        JLabel searchLabel = new JLabel("Search:");
        
        searchField = new JTextField(20);
        

        topPanel.add(searchLabel);
        topPanel.add(searchField);

        add(topPanel, BorderLayout.NORTH);
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchStudents();
            }
        });


        // TABLE
        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("Major");

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);


        // BUTTON PANEL
        JPanel buttonPanel = new JPanel();

        JButton refreshBtn = new JButton("Refresh");
        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");
        JButton exportBtn = new JButton("Export Excel");
        JButton backBtn = new JButton("Back");

        buttonPanel.add(refreshBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(exportBtn);
        buttonPanel.add(backBtn);

        add(buttonPanel, BorderLayout.SOUTH);


        // BUTTON ACTIONS
        refreshBtn.addActionListener(e -> refreshTable());
        deleteBtn.addActionListener(e -> deleteStudent());
        editBtn.addActionListener(e -> editStudent());
        exportBtn.addActionListener(e -> exportToExcel());
        backBtn.addActionListener(e -> frame.showPanel("dashboard"));


        loadStudents();
    }
    private void refreshTable(){

        model.setRowCount(0);

        loadStudents();

    }
    private void loadStudents(){

        try{

            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM students";

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("major")
                });

            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void editStudent(){

        int selectedRow = table.getSelectedRow();

        if(selectedRow == -1){
            JOptionPane.showMessageDialog(this,"Select a student first");
            return;
        }

        int id = (int) model.getValueAt(selectedRow,0);
        String name = model.getValueAt(selectedRow,1).toString();
        String email = model.getValueAt(selectedRow,2).toString();
        String major = model.getValueAt(selectedRow,3).toString();

        JTextField nameField = new JTextField(name);
        JTextField emailField = new JTextField(email);
        JTextField majorField = new JTextField(major);

        Object[] message = {
            "Name:", nameField,
            "Email:", emailField,
            "Major:", majorField
        };

        int option = JOptionPane.showConfirmDialog(
                this,message,"Edit Student",JOptionPane.OK_CANCEL_OPTION);

        if(option == JOptionPane.OK_OPTION){

            try{

                Connection conn = DBConnection.getConnection();

                String sql =
                        "UPDATE students SET name=?, email=?, major=? WHERE id=?";

                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1,nameField.getText());
                stmt.setString(2,emailField.getText());
                stmt.setString(3,majorField.getText());
                stmt.setInt(4,id);

                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this,"Student updated");

                refreshTable();

            }catch(Exception e){
                e.printStackTrace();
            }

        }

    }
    private void searchStudents(){

        model.setRowCount(0);

        String keyword = searchField.getText();

        try{

            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM students WHERE name LIKE ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("major")
                });

            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
    
    private void exportToExcel(){

        JFileChooser chooser = new JFileChooser();

        int option = chooser.showSaveDialog(this);

        if(option == JFileChooser.APPROVE_OPTION){

            try{

                java.io.File file = chooser.getSelectedFile();

                org.apache.poi.ss.usermodel.Workbook workbook =
                        new org.apache.poi.xssf.usermodel.XSSFWorkbook();

                org.apache.poi.ss.usermodel.Sheet sheet =
                        workbook.createSheet("Students");

                org.apache.poi.ss.usermodel.Row header =
                        sheet.createRow(0);

                for(int i=0;i<model.getColumnCount();i++){
                    header.createCell(i)
                            .setCellValue(model.getColumnName(i));
                }

                for(int i=0;i<model.getRowCount();i++){

                    org.apache.poi.ss.usermodel.Row row =
                            sheet.createRow(i+1);

                    for(int j=0;j<model.getColumnCount();j++){

                        row.createCell(j)
                                .setCellValue(model.getValueAt(i,j).toString());

                    }

                }

                java.io.FileOutputStream out =
                        new java.io.FileOutputStream(file + ".xlsx");

                workbook.write(out);

                workbook.close();
                out.close();

                JOptionPane.showMessageDialog(this,"Excel exported");

            }catch(Exception e){
                e.printStackTrace();
            }

        }

    }
    private void deleteStudent(){

        int selectedRow = table.getSelectedRow();

        if(selectedRow == -1){
            JOptionPane.showMessageDialog(this,"Select a student first");
            return;
        }

        int id = (int) model.getValueAt(selectedRow,0);

        try{

            Connection conn = DBConnection.getConnection();

            String sql = "DELETE FROM students WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1,id);

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this,"Student deleted");

            refreshTable();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
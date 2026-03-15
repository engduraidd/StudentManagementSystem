package database;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;

import model.Student;

public class StudentDAO {
	
			public void addStudent(Student student) {


	        try {

	            Connection conn = DBConnection.getConnection();

	            String sql = "INSERT INTO students (name, email, major) VALUES (?, ?, ?)";

	            PreparedStatement stmt = conn.prepareStatement(sql);

	            stmt.setString(1, student.getName());
	            stmt.setString(2, student.getEmail());
	            stmt.setString(3, student.getMajor());

	            stmt.executeUpdate();

	            System.out.println("Student added successfully!");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 }
	        
	        
	        public void viewStudents() {

	        	
	        	
	        	try {
	        		
	                Connection conn = DBConnection.getConnection();
	        		String sql = "SELECT * FROM students";
	                PreparedStatement stmt = conn.prepareStatement(sql);
	                
	                ResultSet rs = stmt.executeQuery();

	                while (rs.next()) {

	                    System.out.println(
	                            rs.getInt("id") + " | " +
	                            rs.getString("name") + " | " +
	                            rs.getString("email") + " | " +
	                            rs.getString("major")
	                    );

	                }

	            } catch (Exception e) {
	                e.printStackTrace();
	            }


	 }

	        public void deleteStudent(int id) {

	            try {

	                Connection conn = DBConnection.getConnection();

	                String sql = "DELETE FROM students WHERE id = ?";

	                PreparedStatement stmt = conn.prepareStatement(sql);

	                stmt.setInt(1, id);

	                int rows = stmt.executeUpdate();

	                if (rows > 0) {
	                    System.out.println("Student deleted successfully!");
	                } else {
	                    System.out.println("Student not found.");
	                }

	            } catch (Exception e) {
	                e.printStackTrace();
	            }

	        }

	        public void updateStudent(int id, String name, String email, String major) {

	            try {

	                Connection conn = DBConnection.getConnection();

	                String sql = "UPDATE students SET name=?, email=?, major=? WHERE id=?";

	                PreparedStatement stmt = conn.prepareStatement(sql);

	                stmt.setString(1, name);
	                stmt.setString(2, email);
	                stmt.setString(3, major);
	                stmt.setInt(4, id);

	                int rows = stmt.executeUpdate();

	                if (rows > 0) {
	                    System.out.println("Student updated successfully!");
	                } else {
	                    System.out.println("Student not found.");
	                }

	            } catch (Exception e) {
	                e.printStackTrace();
	            }

	        }

	        public void searchStudent(String name) {

	            try {

	                Connection conn = DBConnection.getConnection();

	                String sql = "SELECT * FROM students WHERE name LIKE ?";

	                PreparedStatement stmt = conn.prepareStatement(sql);

	                stmt.setString(1, "%" + name + "%");

	                ResultSet rs = stmt.executeQuery();

	                System.out.println("\nSearch Results:");

	                while (rs.next()) {

	                    System.out.println(
	                            rs.getInt("id") + " | " +
	                            rs.getString("name") + " | " +
	                            rs.getString("email") + " | " +
	                            rs.getString("major")
	                    );

	                }

	            } catch (Exception e) {
	                e.printStackTrace();
	            }

	        }


	        public void exportToExcel() {

	            try {

	                Connection conn = DBConnection.getConnection();

	                String sql = "SELECT * FROM students";

	                PreparedStatement stmt = conn.prepareStatement(sql);

	                ResultSet rs = stmt.executeQuery();

	                Workbook workbook = new XSSFWorkbook();
	                Sheet sheet = workbook.createSheet("Students");

	                Row header = sheet.createRow(0);
	                header.createCell(0).setCellValue("ID");
	                header.createCell(1).setCellValue("Name");
	                header.createCell(2).setCellValue("Email");
	                header.createCell(3).setCellValue("Major");

	                int rowIndex = 1;

	                while (rs.next()) {

	                    Row row = sheet.createRow(rowIndex++);

	                    row.createCell(0).setCellValue(rs.getInt("id"));
	                    row.createCell(1).setCellValue(rs.getString("name"));
	                    row.createCell(2).setCellValue(rs.getString("email"));
	                    row.createCell(3).setCellValue(rs.getString("major"));
	                }

	                FileOutputStream fileOut = new FileOutputStream("students.xlsx");
	                workbook.write(fileOut);

	                fileOut.close();
	                workbook.close();

	                System.out.println("Students exported to Excel successfully!");

	            } catch (Exception e) {
	                e.printStackTrace();
	            }

	        }
}



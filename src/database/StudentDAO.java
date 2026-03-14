package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}


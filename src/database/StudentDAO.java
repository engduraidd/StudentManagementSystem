package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
}

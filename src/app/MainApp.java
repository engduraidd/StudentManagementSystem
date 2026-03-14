package app;
import database.DBConnection;
import database.StudentDAO;
import model.Student;


public class MainApp {

	public static void main(String[] args) {
		
		
        Student student1 = new Student(
                "Ali Hassan",
                "ali@email.com",
                "Computer Science"
        );

        StudentDAO dao = new StudentDAO();

        dao.addStudent(student1);


	
	}

}

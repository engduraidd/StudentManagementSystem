package database;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {
	
public static Connection getConnection() {
	
	
	Connection conn = null;
	
	try { 
		 conn = DriverManager.getConnection(
				   "jdbc:mysql://localhost:3306/studentdb",
                   "root",
                   "1234"
				
				
				);
		
		   System.out.println("Database connected!");

    } catch (Exception e) {
        e.printStackTrace();
    }

    return conn;
	
}
}

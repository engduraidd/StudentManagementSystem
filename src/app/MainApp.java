package app;

import java.util.Scanner;

import database.StudentDAO;
import model.Student;

public class MainApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {

            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {

                System.out.print("Enter name: ");
                String name = scanner.nextLine();

                System.out.print("Enter email: ");
                String email = scanner.nextLine();

                System.out.print("Enter major: ");
                String major = scanner.nextLine();
                

                Student student = new Student(name, email, major);

                dao.addStudent(student);

            }

            else if (choice == 2) {

                dao.viewStudents();

            }
            else if (choice == 3) {

                System.out.print("Enter name to search: ");
                String name = scanner.nextLine();

                dao.searchStudent(name);

            }
            
            else if (choice == 4) {

                System.out.print("Enter student ID to update: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter new name: ");
                String name = scanner.nextLine();

                System.out.print("Enter new email: ");
                String email = scanner.nextLine();

                System.out.print("Enter new major: ");
                String major = scanner.nextLine();

                dao.updateStudent(id, name, email, major);

            }
            else if (choice == 5) {

                System.out.print("Enter student ID to delete: ");
                int id = scanner.nextInt();

                dao.deleteStudent(id);

            }

            
            else if (choice == 6) {

                System.out.println("Goodbye!");
                break;

            }

            else {

                System.out.println("Invalid option!");

            }

        }

        scanner.close();

    }

}
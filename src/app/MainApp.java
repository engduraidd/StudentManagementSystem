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
            System.out.println("3. Exit");

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
package app;

import dao.StudentFileDAO;
import model.Student;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentFileDAO dao = new StudentFileDAO(); // uses "data/students.csv"

    public static void main(String[] args) {
        System.out.println("=== Student Management System (Java — File Storage) ===");
        while (true) {
            showMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: searchStudent(); break;
                case 4: deleteStudent(); break;
                case 5:
                    System.out.println("Exiting. Bye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Delete Student by ID");
        System.out.println("5. Exit");
    }

    private static void addStudent() {
        int id = readInt("Enter ID (integer): ");
        String name = readString("Enter Name: ");
        int age = readInt("Enter Age: ");
        String course = readString("Enter Course: ");
        Student s = new Student(id, name, age, course);
        try {
            boolean ok = dao.addStudent(s);
            if (ok) System.out.println("Student added successfully.");
            else System.out.println("A student with this ID already exists.");
        } catch (IOException e) {
            System.err.println("Failed to add student: " + e.getMessage());
        }
    }

    private static void viewStudents() {
        try {
            List<Student> list = dao.getAll();
            if (list.isEmpty()) {
                System.out.println("No students found.");
                return;
            }
            System.out.println("\n--- Students ---");
            list.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Error reading students: " + e.getMessage());
        }
    }

    private static void searchStudent() {
        int id = readInt("Enter ID to search: ");
        try {
            Optional<Student> s = dao.findById(id);
            if (s.isPresent()) System.out.println("Found: " + s.get());
            else System.out.println("Student not found.");
        } catch (IOException e) {
            System.err.println("Error searching student: " + e.getMessage());
        }
    }

    private static void deleteStudent() {
        int id = readInt("Enter ID to delete: ");
        try {
            boolean ok = dao.deleteById(id);
            if (ok) System.out.println("Student deleted.");
            else System.out.println("No student with that ID was found.");
        } catch (IOException e) {
            System.err.println("Error deleting student: " + e.getMessage());
        }
    }

    // Helpers
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}

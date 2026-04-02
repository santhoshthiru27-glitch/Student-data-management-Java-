// =====================================================================
//  Main.java – Entry point. Console I/O loop using Scanner.
//  OOP Concepts used:
//    • Encapsulation  : Student fields are private, exposed via getters/setters
//    • Abstraction    : StudentManager hides array logic behind clean methods
//    • Modularity     : Split across Student, StudentManager, Menu, Main
//    • Object Creation: new Student(...) via StudentManager
//  No Collections (ArrayList, HashMap, etc.) are used anywhere.
// =====================================================================
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        seedDemoData(manager);          // Pre-load a few students for demo

        System.out.println("\n  Welcome to the Student Management System!");

        boolean running = true;
        while (running) {
            Menu.showMainMenu();
            int choice = readInt();

            Menu.showDivider();

            switch (choice) {
                case 1:  addStudent(manager);         break;
                case 2:  manager.displayAllStudents(); break;
                case 3:  searchById(manager);         break;
                case 4:  searchByName(manager);       break;
                case 5:  searchByCourse(manager);     break;
                case 6:  updateStudent(manager);      break;
                case 7:  deleteStudent(manager);      break;
                case 8:  manager.sortByMarksDescending(); break;
                case 9:  manager.displayStatistics(); break;
                case 0:
                    System.out.println("  Goodbye! Exiting system...");
                    running = false;
                    break;
                default:
                    System.out.println("  ✗ Invalid choice. Please try again.");
            }
        }
        sc.close();
    }

    // ── ADD ──────────────────────────────────────────────────────────

    private static void addStudent(StudentManager manager) {
        System.out.println("  [ Add New Student ]");
        System.out.print("  Name   : "); String name   = sc.nextLine().trim();
        System.out.print("  Age    : "); int    age    = readInt();
        System.out.print("  Course : "); String course = sc.nextLine().trim();
        System.out.print("  Marks  : "); double marks  = readDouble();
        manager.addStudent(name, age, course, marks);
    }

    // ── SEARCH BY ID ─────────────────────────────────────────────────

    private static void searchById(StudentManager manager) {
        System.out.print("  Enter Student ID: ");
        int id = readInt();
        manager.displayStudentById(id);
    }

    // ── SEARCH BY NAME ───────────────────────────────────────────────

    private static void searchByName(StudentManager manager) {
        System.out.print("  Enter Name (or part of name): ");
        String keyword = sc.nextLine().trim();
        manager.searchByName(keyword);
    }

    // ── SEARCH BY COURSE ─────────────────────────────────────────────

    private static void searchByCourse(StudentManager manager) {
        System.out.print("  Enter Course (or part of course name): ");
        String course = sc.nextLine().trim();
        manager.searchByCourse(course);
    }

    // ── UPDATE ───────────────────────────────────────────────────────

    private static void updateStudent(StudentManager manager) {
        System.out.println("  [ Update Student ]");
        System.out.print("  Enter Student ID to update: ");
        int id = readInt();

        System.out.print("  New Name   (Enter to skip): "); String name   = sc.nextLine().trim();
        System.out.print("  New Age    (0  to skip)   : "); int    age    = readInt();
        System.out.print("  New Course (Enter to skip): "); String course = sc.nextLine().trim();
        System.out.print("  New Marks  (-1 to skip)   : "); double marks  = readDouble();

        manager.updateStudent(id, name, age, course, marks);
    }

    // ── DELETE ───────────────────────────────────────────────────────

    private static void deleteStudent(StudentManager manager) {
        System.out.print("  Enter Student ID to delete: ");
        int id = readInt();
        System.out.print("  Are you sure? (y/n): ");
        String confirm = sc.nextLine().trim();
        if (confirm.equalsIgnoreCase("y")) {
            manager.deleteStudent(id);
        } else {
            System.out.println("  ✗ Delete cancelled.");
        }
    }

    // ── INPUT HELPERS ─────────────────────────────────────────────────

    private static int readInt() {
        int val = 0;
        try {
            String line = sc.nextLine().trim();
            val = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("  ⚠ Invalid number – defaulting to 0.");
        }
        return val;
    }

    private static double readDouble() {
        double val = 0;
        try {
            String line = sc.nextLine().trim();
            val = Double.parseDouble(line);
        } catch (NumberFormatException e) {
            System.out.println("  ⚠ Invalid number – defaulting to 0.");
        }
        return val;
    }

    // ── SEED DATA ─────────────────────────────────────────────────────

    private static void seedDemoData(StudentManager m) {
        m.addStudent("Alice Johnson",  20, "Computer Science",  92.5);
        m.addStudent("Bob Smith",      22, "Mathematics",       74.0);
        m.addStudent("Clara Perez",    19, "Physics",           85.5);
        m.addStudent("David Kumar",    21, "Computer Science",  63.0);
        m.addStudent("Eva Chen",       23, "Data Science",      97.0);
        m.addStudent("Frank Williams", 20, "Mathematics",       45.5);
        m.addStudent("Grace Lee",      22, "Physics",           78.0);
        System.out.println("  ✓ 7 demo students loaded.\n");
    }
}

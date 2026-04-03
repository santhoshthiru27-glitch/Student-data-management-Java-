import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        try {
            while (true) {
                System.out.println("\n1.Add 2.View 3.Update 4.Delete 5.Exit");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        System.out.print("Email: ");
                        String email = sc.nextLine();

                        System.out.print("Course: ");
                        String course = sc.nextLine();

                        Student s = new Student(name, email, course);
                        dao.addStudent(s);
                        break;

                    case 2:
                        dao.viewStudents();
                        break;

                    case 3:
                        System.out.print("ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("New Name: ");
                        String newName = sc.nextLine();

                        dao.updateStudent(id, newName);
                        break;

                    case 4:
                        System.out.print("ID: ");
                        int delId = sc.nextInt();

                        dao.deleteStudent(delId);
                        break;

                    case 5:
                        System.exit(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
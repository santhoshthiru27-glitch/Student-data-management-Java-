// =============================================
//  Menu.java – Displays the console UI
// =============================================
public class Menu {

    public static void showMainMenu() {
        System.out.println("\n  ╔══════════════════════════════════════╗");
        System.out.println("  ║   STUDENT MANAGEMENT SYSTEM  v1.0    ║");
        System.out.println("  ╠══════════════════════════════════════╣");
        System.out.println("  ║  1. Add Student                      ║");
        System.out.println("  ║  2. Display All Students             ║");
        System.out.println("  ║  3. Search Student by ID             ║");
        System.out.println("  ║  4. Search Student by Name           ║");
        System.out.println("  ║  5. Search Student by Course         ║");
        System.out.println("  ║  6. Update Student                   ║");
        System.out.println("  ║  7. Delete Student                   ║");
        System.out.println("  ║  8. Sort Students by Marks           ║");
        System.out.println("  ║  9. Statistics                       ║");
        System.out.println("  ║  0. Exit                             ║");
        System.out.println("  ╚══════════════════════════════════════╝");
        System.out.print("  Enter choice: ");
    }

    public static void showDivider() {
        System.out.println("  ─────────────────────────────────────────");
    }
}

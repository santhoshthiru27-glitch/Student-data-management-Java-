// =====================================================================
//  StudentManager.java - Manages student records using a fixed array
//  Demonstrates: Encapsulation, Abstraction, Array-based data storage
// =====================================================================
public class StudentManager {

    private static final int MAX_SIZE = 100;

    private Student[] students;   // Array – no Collections used
    private int       count;      // Current number of students
    private int       nextId;     // Auto-increment ID counter

    // Constructor
    public StudentManager() {
        students = new Student[MAX_SIZE];
        count    = 0;
        nextId   = 1;
    }

    // ── Helpers ──────────────────────────────────────────────────────

    private boolean isFull()  { return count == MAX_SIZE; }
    private boolean isEmpty() { return count == 0;        }

    /** Linear search by ID; returns index or -1 */
    private int findIndexById(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId() == id) return i;
        }
        return -1;
    }

    /** Validate marks range */
    private boolean isValidMarks(double marks) {
        return marks >= 0 && marks <= 100;
    }

    /** Validate age range */
    private boolean isValidAge(int age) {
        return age >= 5 && age <= 100;
    }

    // ── CREATE ───────────────────────────────────────────────────────

    public boolean addStudent(String name, int age, String course, double marks) {
        if (isFull()) {
            System.out.println("  ✗ Storage is full. Cannot add more students.");
            return false;
        }
        if (name == null || name.trim().isEmpty()) {
            System.out.println("  ✗ Name cannot be empty.");
            return false;
        }
        if (!isValidAge(age)) {
            System.out.println("  ✗ Age must be between 5 and 100.");
            return false;
        }
        if (!isValidMarks(marks)) {
            System.out.println("  ✗ Marks must be between 0 and 100.");
            return false;
        }

        students[count] = new Student(nextId++, name.trim(), age, course.trim(), marks);
        count++;
        System.out.println("  ✓ Student added successfully with ID " + (nextId - 1));
        return true;
    }

    // ── READ (All) ───────────────────────────────────────────────────

    public void displayAllStudents() {
        if (isEmpty()) {
            System.out.println("  ⚠ No students found.");
            return;
        }
        printTableHeader();
        for (int i = 0; i < count; i++) {
            System.out.println(students[i]);
        }
        printTableFooter();
        System.out.println("  Total students: " + count);
    }

    // ── READ (By ID) ─────────────────────────────────────────────────

    public void displayStudentById(int id) {
        int idx = findIndexById(id);
        if (idx == -1) {
            System.out.println("  ✗ Student with ID " + id + " not found.");
        } else {
            students[idx].display();
        }
    }

    // ── UPDATE ───────────────────────────────────────────────────────

    public boolean updateStudent(int id, String name, int age,
                                  String course, double marks) {
        int idx = findIndexById(id);
        if (idx == -1) {
            System.out.println("  ✗ Student with ID " + id + " not found.");
            return false;
        }
        if (name != null && !name.trim().isEmpty()) {
            students[idx].setName(name.trim());
        }
        if (isValidAge(age)) {
            students[idx].setAge(age);
        } else {
            System.out.println("  ⚠ Invalid age – keeping old value.");
        }
        if (course != null && !course.trim().isEmpty()) {
            students[idx].setCourse(course.trim());
        }
        if (isValidMarks(marks)) {
            students[idx].setMarks(marks);
        } else {
            System.out.println("  ⚠ Invalid marks – keeping old value.");
        }
        System.out.println("  ✓ Student ID " + id + " updated successfully.");
        return true;
    }

    // ── DELETE ───────────────────────────────────────────────────────

    public boolean deleteStudent(int id) {
        int idx = findIndexById(id);
        if (idx == -1) {
            System.out.println("  ✗ Student with ID " + id + " not found.");
            return false;
        }
        // Shift elements left
        for (int i = idx; i < count - 1; i++) {
            students[i] = students[i + 1];
        }
        students[count - 1] = null;
        count--;
        System.out.println("  ✓ Student ID " + id + " deleted successfully.");
        return true;
    }

    // ── SEARCH by Name ───────────────────────────────────────────────

    public void searchByName(String keyword) {
        if (isEmpty()) {
            System.out.println("  ⚠ No students to search.");
            return;
        }
        boolean found = false;
        String  kw    = keyword.toLowerCase();
        printTableHeader();
        for (int i = 0; i < count; i++) {
            if (students[i].getName().toLowerCase().contains(kw)) {
                System.out.println(students[i]);
                found = true;
            }
        }
        printTableFooter();
        if (!found) {
            System.out.println("  ✗ No students matching \"" + keyword + "\" found.");
        }
    }

    // ── SEARCH by Course ─────────────────────────────────────────────

    public void searchByCourse(String course) {
        if (isEmpty()) {
            System.out.println("  ⚠ No students to search.");
            return;
        }
        boolean found = false;
        String  kw    = course.toLowerCase();
        printTableHeader();
        for (int i = 0; i < count; i++) {
            if (students[i].getCourse().toLowerCase().contains(kw)) {
                System.out.println(students[i]);
                found = true;
            }
        }
        printTableFooter();
        if (!found) {
            System.out.println("  ✗ No students in course \"" + course + "\" found.");
        }
    }

    // ── STATISTICS ───────────────────────────────────────────────────

    public void displayStatistics() {
        if (isEmpty()) {
            System.out.println("  ⚠ No data available for statistics.");
            return;
        }

        double total   = 0, highest = students[0].getMarks(), lowest = students[0].getMarks();
        int    topIdx  = 0, lowIdx  = 0;

        for (int i = 0; i < count; i++) {
            double m = students[i].getMarks();
            total += m;
            if (m > highest) { highest = m; topIdx = i; }
            if (m < lowest)  { lowest  = m; lowIdx = i; }
        }

        double average = total / count;

        System.out.println("\n  ╔══════════════════════════════════════╗");
        System.out.println("  ║         STATISTICS REPORT            ║");
        System.out.println("  ╠══════════════════════════════════════╣");
        System.out.printf ("  ║  Total Students  : %-18d ║%n", count);
        System.out.printf ("  ║  Average Marks   : %-18.2f ║%n", average);
        System.out.printf ("  ║  Highest Marks   : %-18.2f ║%n", highest);
        System.out.printf ("  ║  Topper          : %-18s ║%n", students[topIdx].getName());
        System.out.printf ("  ║  Lowest Marks    : %-18.2f ║%n", lowest);
        System.out.printf ("  ║  Lowest Scorer   : %-18s ║%n", students[lowIdx].getName());
        System.out.println("  ╚══════════════════════════════════════╝");

        // Grade distribution
        int[] gradeCounts = new int[6]; // A+, A, B, C, D, F
        for (int i = 0; i < count; i++) {
            String g = students[i].getGrade();
            switch (g) {
                case "A+": gradeCounts[0]++; break;
                case "A":  gradeCounts[1]++; break;
                case "B":  gradeCounts[2]++; break;
                case "C":  gradeCounts[3]++; break;
                case "D":  gradeCounts[4]++; break;
                default:   gradeCounts[5]++; break;
            }
        }
        String[] labels = {"A+", "A ", "B ", "C ", "D ", "F "};
        System.out.println("\n  Grade Distribution:");
        for (int i = 0; i < 6; i++) {
            System.out.printf("    %s : %d student(s)%n", labels[i], gradeCounts[i]);
        }
    }

    // ── SORT by Marks (Bubble Sort – descending) ──────────────────────

    public void sortByMarksDescending() {
        if (isEmpty()) { System.out.println("  ⚠ Nothing to sort."); return; }
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (students[j].getMarks() < students[j + 1].getMarks()) {
                    Student temp     = students[j];
                    students[j]     = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
        System.out.println("  ✓ Students sorted by marks (highest first).");
        displayAllStudents();
    }

    // ── TABLE HELPERS ─────────────────────────────────────────────────

    private void printTableHeader() {
        System.out.println("+------+--------------------+-----+-----------------+--------+-----+");
        System.out.println("| ID   | Name               | Age | Course          | Marks  | Grd |");
        System.out.println("+------+--------------------+-----+-----------------+--------+-----+");
    }

    private void printTableFooter() {
        System.out.println("+------+--------------------+-----+-----------------+--------+-----+");
    }

    public int getCount() { return count; }
}

// =============================================
//  Student.java - Encapsulates Student Entity
// =============================================
public class Student {

    // Private fields - Encapsulation
    private int    id;
    private String name;
    private int    age;
    private String course;
    private double marks;

    // Constructor
    public Student(int id, String name, int age, String course, double marks) {
        this.id     = id;
        this.name   = name;
        this.age    = age;
        this.course = course;
        this.marks  = marks;
    }

    // ── Getters ──────────────────────────────
    public int    getId()     { return id;     }
    public String getName()   { return name;   }
    public int    getAge()    { return age;    }
    public String getCourse() { return course; }
    public double getMarks()  { return marks;  }

    // ── Setters ──────────────────────────────
    public void setName(String name)     { this.name   = name;   }
    public void setAge(int age)          { this.age    = age;    }
    public void setCourse(String course) { this.course = course; }
    public void setMarks(double marks)   { this.marks  = marks;  }

    // Grade logic – abstracted inside the class
    public String getGrade() {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 70) return "B";
        if (marks >= 60) return "C";
        if (marks >= 50) return "D";
        return "F";
    }

    // Display a single student record
    public void display() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.printf("│  ID     : %-30d │%n", id);
        System.out.printf("│  Name   : %-30s │%n", name);
        System.out.printf("│  Age    : %-30d │%n", age);
        System.out.printf("│  Course : %-30s │%n", course);
        System.out.printf("│  Marks  : %-30.2f │%n", marks);
        System.out.printf("│  Grade  : %-30s │%n", getGrade());
        System.out.println("└─────────────────────────────────────────┘");
    }

    @Override
    public String toString() {
        return String.format("| %-4d | %-18s | %-3d | %-15s | %-6.2f | %-3s |",
                id, name, age, course, marks, getGrade());
    }
}

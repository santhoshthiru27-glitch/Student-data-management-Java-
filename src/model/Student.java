package model;

public class Student {
    private int id;
    private String name;
    private int age;
    private String course;

    public Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name.trim();
        this.age = age;
        this.course = course.trim();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Age: %d | Course: %s",
                id, name, age, course);
    }

    // Convert to a CSV line (simple escaping: replace newline and comma)
    public String toCSV() {
        String cleanName = name.replace(",", " ").replace("\n", " ");
        String cleanCourse = course.replace(",", " ").replace("\n", " ");
        return id + "," + cleanName + "," + age + "," + cleanCourse;
    }

    public static Student fromCSV(String line) {
        if (line == null || line.isBlank()) return null;
        String[] parts = line.split(",", 4); // id,name,age,course
        if (parts.length < 4) return null;
        try {
            int id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            int age = Integer.parseInt(parts[2].trim());
            String course = parts[3].trim();
            return new Student(id, name, age, course);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

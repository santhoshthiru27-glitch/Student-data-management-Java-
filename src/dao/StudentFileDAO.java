package dao;

import model.Student;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class StudentFileDAO {
    private final Path dataDir;
    private final Path filePath;
    private static final String FILE_NAME = "students.csv";

    public StudentFileDAO() {
        this("data");
    }

    public StudentFileDAO(String dataDirectory) {
        this.dataDir = Paths.get(dataDirectory);
        this.filePath = dataDir.resolve(FILE_NAME);
        ensureDataFile();
    }

    private void ensureDataFile() {
        try {
            if (Files.notExists(dataDir)) {
                Files.createDirectories(dataDir);
            }
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to create data file: " + e.getMessage(), e);
        }
    }

    // Add student; returns true if added, false if id already exists
    public synchronized boolean addStudent(Student s) throws IOException {
        if (findById(s.getId()).isPresent()) return false;
        String line = s.toCSV();
        try (BufferedWriter bw = Files.newBufferedWriter(filePath, StandardOpenOption.APPEND)) {
            bw.write(line);
            bw.newLine();
            bw.flush();
            return true;
        }
    }

    // Get all students
    public synchronized List<Student> getAll() throws IOException {
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            return br.lines()
                     .map(Student::fromCSV)
                     .filter(Objects::nonNull)
                     .collect(Collectors.toList());
        }
    }

    // Find by ID
    public synchronized Optional<Student> findById(int id) throws IOException {
        return getAll().stream().filter(s -> s.getId() == id).findFirst();
    }

    // Delete by ID; returns true if deleted
    public synchronized boolean deleteById(int id) throws IOException {
        List<Student> all = getAll();
        boolean removed = all.removeIf(s -> s.getId() == id);
        if (!removed) return false;
        // Write back atomically
        Path temp = Files.createTempFile(dataDir, "students", ".tmp");
        try (BufferedWriter bw = Files.newBufferedWriter(temp, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Student s : all) {
                bw.write(s.toCSV());
                bw.newLine();
            }
        }
        Files.move(temp, filePath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        return true;
    }
}

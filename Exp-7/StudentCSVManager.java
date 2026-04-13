import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StudentCSVManager {
    private static final String HEADER = "studentId,name,branch,marks1,marks2,marks3,marks4,marks5,percentage";

    public void createInitialFile(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(HEADER);
            bw.newLine();
            bw.write("S101,Aarav,CSE,78,82,75,80,84,79.80");
            bw.newLine();
            bw.write("S102,Meera,AIML,88,90,86,91,87,88.40");
            bw.newLine();
            System.out.println("CREATE: Students.csv created with header and 2 rows.\n");
        } catch (IOException e) {
            System.out.println("IOException while creating file: " + e.getMessage());
        }
    }

    public void addStudent(String fileName, StudentRecord student) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write(student.toCSVRow());
            bw.newLine();
            System.out.println("ADD: Added student -> " + student.getStudentId() + " " + student.getName());
        } catch (IOException e) {
            System.out.println("IOException while adding record: " + e.getMessage());
        }
    }

    public ArrayList<StudentRecord> readAllStudents(String fileName) {
        ArrayList<StudentRecord> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            if (line == null) {
                return students;
            }
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 9) {
                    StudentRecord student = new StudentRecord(
                            parts[0],
                            parts[1],
                            parts[2],
                            Integer.parseInt(parts[3]),
                            Integer.parseInt(parts[4]),
                            Integer.parseInt(parts[5]),
                            Integer.parseInt(parts[6]),
                            Integer.parseInt(parts[7]),
                            Double.parseDouble(parts[8])
                    );
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.out.println("IOException while reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception while parsing data: " + e.getMessage());
        }
        return students;
    }

    public void updateAllRows(String fileName) {
        ArrayList<StudentRecord> students = readAllStudents(fileName);

        for (StudentRecord student : students) {
            if (student.getStudentId().equals("S103")) {
                student.setMarks4(72);
                student.setMarks5(78);
            } else if (student.getStudentId().equals("S104")) {
                student.setMarks4(85);
                student.setMarks5(81);
            } else if (student.getStudentId().equals("S105")) {
                student.setMarks4(69);
                student.setMarks5(74);
            }
            student.calculatePercentage();
        }

        writeAllStudents(fileName, students);
        System.out.println("\nUPDATE: All rows updated with correct marks and percentage.");
    }

    public void deleteStudentById(String fileName, String studentId) {
        ArrayList<StudentRecord> students = readAllStudents(fileName);
        boolean removed = students.removeIf(student -> student.getStudentId().equals(studentId));

        if (removed) {
            writeAllStudents(fileName, students);
            System.out.println("\nDELETE: Row deleted for studentId = " + studentId);
        } else {
            System.out.println("\nDELETE: No row found for studentId = " + studentId);
        }
    }

    private void writeAllStudents(String fileName, ArrayList<StudentRecord> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(HEADER);
            bw.newLine();
            for (StudentRecord student : students) {
                bw.write(student.toCSVRow());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("IOException while writing file: " + e.getMessage());
        }
    }

    public void displayFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("---------------------------------------------------------------");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("---------------------------------------------------------------\n");
        } catch (IOException e) {
            System.out.println("IOException while displaying file: " + e.getMessage());
        }
    }

    public void showExceptionCondition() {
        System.out.println("EXCEPTION CASE: Trying to read a file that does not exist...");
        displayFile("MissingStudents.csv");
    }
}

public class StudentRecord {
    private String studentId;
    private String name;
    private String branch;
    private int marks1;
    private int marks2;
    private int marks3;
    private int marks4;
    private int marks5;
    private double percentage;

    public StudentRecord(String studentId, String name, String branch,
                         int marks1, int marks2, int marks3, int marks4, int marks5, double percentage) {
        this.studentId = studentId;
        this.name = name;
        this.branch = branch;
        this.marks1 = validateMarks(marks1);
        this.marks2 = validateMarks(marks2);
        this.marks3 = validateMarks(marks3);
        this.marks4 = validateMarks(marks4);
        this.marks5 = validateMarks(marks5);
        this.percentage = percentage;
    }

    private int validateMarks(int marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }
        return marks;
    }

    public void setMarks4(int marks4) {
        this.marks4 = validateMarks(marks4);
    }

    public void setMarks5(int marks5) {
        this.marks5 = validateMarks(marks5);
    }

    public void calculatePercentage() {
        int total = marks1 + marks2 + marks3 + marks4 + marks5;
        this.percentage = total / 5.0;
    }

    public String toCSVRow() {
        return studentId + "," + name + "," + branch + "," + marks1 + "," + marks2 + "," + marks3 + "," + marks4 + "," + marks5 + "," + String.format("%.2f", percentage);
    }

    @Override
    public String toString() {
        return String.format("%-8s %-15s %-8s %3d %3d %3d %3d %3d %8.2f%%",
                studentId, name, branch, marks1, marks2, marks3, marks4, marks5, percentage);
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getBranch() { return branch; }
    public int getMarks1() { return marks1; }
    public int getMarks2() { return marks2; }
    public int getMarks3() { return marks3; }
    public int getMarks4() { return marks4; }
    public int getMarks5() { return marks5; }
}

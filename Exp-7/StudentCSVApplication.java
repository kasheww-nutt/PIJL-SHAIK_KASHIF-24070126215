public class StudentCSVApplication {
    public static void main(String[] args) {
        String fileName = "Students.csv";
        StudentCSVManager manager = new StudentCSVManager();

        System.out.println("================ STUDENT CSV CRUD OPERATIONS ================\n");

        manager.createInitialFile(fileName);
        System.out.println("After CREATE operation:");
        manager.displayFile(fileName);

        manager.addStudent(fileName, new StudentRecord("S103", "Riya", "ECE", 71, 76, 73, 0, 0, 0));
        manager.addStudent(fileName, new StudentRecord("S104", "Kabir", "CSE", 84, 79, 88, 0, 0, 0));
        manager.addStudent(fileName, new StudentRecord("S105", "Neha", "AIML", 67, 72, 70, 0, 0, 0));
        System.out.println("\nAfter ADD operation:");
        manager.displayFile(fileName);

        manager.updateAllRows(fileName);
        System.out.println("After UPDATE operation:");
        manager.displayFile(fileName);

        manager.deleteStudentById(fileName, "S104");
        System.out.println("After DELETE operation:");
        manager.displayFile(fileName);

        manager.showExceptionCondition();
    }
}

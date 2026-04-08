import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public abstract class Employee {
    protected String name;
    protected String panNo;
    protected LocalDate joiningDate;
    protected String designation;
    protected int empId;
    protected String department;

    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Employee(String name, String panNo, String joiningDate, String designation, int empId, String department) {
        this.name = validateText(name, "Name");
        this.panNo = validatePAN(panNo);
        this.joiningDate = validateJoiningDate(joiningDate);
        this.designation = validateText(designation, "Designation");
        this.department = validateText(department, "Department");
        this.empId = validateEmpId(empId);
    }

    private String validateText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }

    private int validateEmpId(int empId) {
        if (empId <= 0) {
            throw new IllegalArgumentException("Employee ID must be positive.");
        }
        return empId;
    }

    private String validatePAN(String panNo) {
        String cleaned = validateText(panNo, "PAN Number").toUpperCase();
        if (!cleaned.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
            throw new IllegalArgumentException("Invalid PAN format. Example: ABCDE1234F");
        }
        return cleaned;
    }

    private LocalDate validateJoiningDate(String joiningDate) {
        LocalDate date = LocalDate.parse(joiningDate, FORMATTER);
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Joining date cannot be in the future.");
        }
        return date;
    }

    public int getYearsOfService() {
        return Period.between(joiningDate, LocalDate.now()).getYears();
    }

    public void displayBasicDetails() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Employee ID       : " + empId);
        System.out.println("Name              : " + name);
        System.out.println("PAN Number        : " + panNo);
        System.out.println("Joining Date      : " + joiningDate.format(FORMATTER));
        System.out.println("Years Of Service  : " + getYearsOfService());
        System.out.println("Designation       : " + designation);
        System.out.println("Department        : " + department);
    }

    public abstract double calcCTC();
}

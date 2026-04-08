import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();

        employees.add(new FullTimeEmployee(
                "Riya Sharma", "ABCDE1234F", "15-07-2022", "Software Engineer",
                101, "Engineering", "SWE", "Senior", 700000, 80000, 0
        ));

        employees.add(new FullTimeEmployee(
                "Ankit Verma", "PQRSX5678L", "20-01-2021", "HR Executive",
                102, "Human Resources", "HR", "Mid", 500000, 0, 60000
        ));

        employees.add(new ContractEmployee(
                "Sneha Patil", "LMNOP9876K", "10-09-2024", "UI Consultant",
                103, "Design", 176, 750, true
        ));

        employees.add(new Manager(
                "Rahul Mehta", "ZXCVB4567N", "05-03-2019", "Engineering Manager",
                104, "Engineering", "SWE", "Lead", 1200000, 150000,
                0, 50000, 40000, 12
        ));

        System.out.println("================ EMPLOYEE PAYROLL / CTC REPORT ================\n");

        for (Employee employee : employees) {
            if (employee instanceof Manager) {
                ((Manager) employee).displayDetails();
            } else if (employee instanceof FullTimeEmployee) {
                ((FullTimeEmployee) employee).displayDetails();
            } else if (employee instanceof ContractEmployee) {
                ((ContractEmployee) employee).displayDetails();
            }
            System.out.println();
        }
    }
}

public class FullTimeEmployee extends Employee {
    protected String role;
    protected String level;
    protected double baseSalary;
    protected double perfBonus;
    protected double hiringCommission;

    public FullTimeEmployee(String name, String panNo, String joiningDate, String designation,
                            int empId, String department, String role, String level,
                            double baseSalary, double perfBonus, double hiringCommission) {
        super(name, panNo, joiningDate, designation, empId, department);
        this.role = validateRole(role);
        this.level = validateLevel(level);
        this.baseSalary = validateAmount(baseSalary, "Base salary");
        this.perfBonus = validateAmount(perfBonus, "Performance bonus");
        this.hiringCommission = validateAmount(hiringCommission, "Hiring commission");
    }

    protected String validateRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Role cannot be empty.");
        }
        return role.trim().toUpperCase();
    }

    protected String validateLevel(String level) {
        if (level == null || level.trim().isEmpty()) {
            throw new IllegalArgumentException("Level cannot be empty.");
        }
        return level.trim().toUpperCase();
    }

    protected double validateAmount(double value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative.");
        }
        return value;
    }

    protected double getHRA() {
        return baseSalary * 0.15;
    }

    protected double getEmployerPF() {
        return baseSalary * 0.12;
    }

    protected double getLoyaltyAllowance() {
        return getYearsOfService() >= 3 ? baseSalary * 0.05 : 0;
    }

    protected double getHierarchyAllowance() {
        switch (level) {
            case "JUNIOR":
                return 20000;
            case "MID":
                return 40000;
            case "SENIOR":
                return 70000;
            case "LEAD":
                return 100000;
            default:
                return 15000;
        }
    }

    @Override
    public double calcCTC() {
        double hra = getHRA();
        double employerPF = getEmployerPF();
        double hierarchyAllowance = getHierarchyAllowance();
        double loyaltyAllowance = getLoyaltyAllowance();

        if (role.equals("SWE")) {
            return baseSalary + perfBonus + hra + employerPF + hierarchyAllowance + loyaltyAllowance;
        } else if (role.equals("HR")) {
            return baseSalary + hiringCommission + hra + employerPF + hierarchyAllowance + loyaltyAllowance;
        } else {
            return baseSalary + hra + employerPF + hierarchyAllowance + loyaltyAllowance;
        }
    }

    public void displayDetails() {
        displayBasicDetails();
        System.out.println("Employee Type     : Full Time");
        System.out.println("Role              : " + role);
        System.out.println("Level             : " + level);
        System.out.println("Base Salary       : " + baseSalary);
        System.out.println("Performance Bonus : " + perfBonus);
        System.out.println("Hiring Commission : " + hiringCommission);
        System.out.println("HRA               : " + getHRA());
        System.out.println("Employer PF       : " + getEmployerPF());
        System.out.println("Hierarchy Allow.  : " + getHierarchyAllowance());
        System.out.println("Loyalty Allow.    : " + getLoyaltyAllowance());
        System.out.println("Total CTC         : " + calcCTC());
    }
}

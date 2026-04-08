public class Manager extends FullTimeEmployee {
    private double travelAllowance;
    private double educationAllowance;
    private int teamSize;

    public Manager(String name, String panNo, String joiningDate, String designation,
                   int empId, String department, String role, String level,
                   double baseSalary, double perfBonus, double hiringCommission,
                   double travelAllowance, double educationAllowance, int teamSize) {
        super(name, panNo, joiningDate, designation, empId, department, role, level,
                baseSalary, perfBonus, hiringCommission);
        this.travelAllowance = validateAmount(travelAllowance, "Travel allowance");
        this.educationAllowance = validateAmount(educationAllowance, "Education allowance");
        if (teamSize < 0) {
            throw new IllegalArgumentException("Team size cannot be negative.");
        }
        this.teamSize = teamSize;
    }

    private double getTeamHandlingAllowance() {
        if (teamSize >= 15) {
            return 75000;
        } else if (teamSize >= 8) {
            return 40000;
        }
        return 15000;
    }

    @Override
    public double calcCTC() {
        return baseSalary
                + perfBonus
                + travelAllowance
                + educationAllowance
                + getHRA()
                + getEmployerPF()
                + getHierarchyAllowance()
                + getLoyaltyAllowance()
                + getTeamHandlingAllowance();
    }

    @Override
    public void displayDetails() {
        displayBasicDetails();
        System.out.println("Employee Type     : Manager (Full Time)");
        System.out.println("Role              : " + role);
        System.out.println("Level             : " + level);
        System.out.println("Team Size         : " + teamSize);
        System.out.println("Base Salary       : " + baseSalary);
        System.out.println("Performance Bonus : " + perfBonus);
        System.out.println("Travel Allowance  : " + travelAllowance);
        System.out.println("Education Allow.  : " + educationAllowance);
        System.out.println("HRA               : " + getHRA());
        System.out.println("Employer PF       : " + getEmployerPF());
        System.out.println("Hierarchy Allow.  : " + getHierarchyAllowance());
        System.out.println("Loyalty Allow.    : " + getLoyaltyAllowance());
        System.out.println("Team Handling All.: " + getTeamHandlingAllowance());
        System.out.println("Total CTC         : " + calcCTC());
    }
}

public class ContractEmployee extends Employee {
    private int noOfHrs;
    private double hourlyRate;
    private boolean weekendSupport;

    public ContractEmployee(String name, String panNo, String joiningDate, String designation,
                            int empId, String department, int noOfHrs,
                            double hourlyRate, boolean weekendSupport) {
        super(name, panNo, joiningDate, designation, empId, department);
        if (noOfHrs < 0) {
            throw new IllegalArgumentException("Number of hours cannot be negative.");
        }
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("Hourly rate cannot be negative.");
        }
        this.noOfHrs = noOfHrs;
        this.hourlyRate = hourlyRate;
        this.weekendSupport = weekendSupport;
    }

    private double getOvertimePay() {
        if (noOfHrs > 160) {
            int overtimeHours = noOfHrs - 160;
            return overtimeHours * hourlyRate * 0.25;
        }
        return 0;
    }

    private double getWeekendSupportAllowance() {
        return weekendSupport ? 5000 : 0;
    }

    @Override
    public double calcCTC() {
        return (noOfHrs * hourlyRate) + getOvertimePay() + getWeekendSupportAllowance();
    }

    public void displayDetails() {
        displayBasicDetails();
        System.out.println("Employee Type     : Contract");
        System.out.println("No. Of Hours      : " + noOfHrs);
        System.out.println("Hourly Rate       : " + hourlyRate);
        System.out.println("Overtime Pay      : " + getOvertimePay());
        System.out.println("Weekend Support   : " + (weekendSupport ? "Yes" : "No"));
        System.out.println("Support Allow.    : " + getWeekendSupportAllowance());
        System.out.println("Total CTC         : " + calcCTC());
    }
}

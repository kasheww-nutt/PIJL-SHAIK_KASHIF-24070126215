public class LoanAccount extends Account {
    private final double sanctionedAmount;
    private final double annualInterestRate;
    private final double emiAmount;

    public LoanAccount(String accountNumber,
                       String customerId,
                       String accountHolderName,
                       String branchName,
                       String ifscCode,
                       double sanctionedAmount,
                       double annualInterestRate,
                       double emiAmount) {

        super(accountNumber, customerId, accountHolderName, branchName, ifscCode, sanctionedAmount);

        if (sanctionedAmount <= 0)
            throw new IllegalArgumentException("Sanctioned amount must be greater than zero.");
        if (annualInterestRate < 0)
            throw new IllegalArgumentException("Annual interest rate cannot be negative.");
        if (emiAmount <= 0)
            throw new IllegalArgumentException("EMI amount must be greater than zero.");

        this.sanctionedAmount   = sanctionedAmount;
        this.annualInterestRate = annualInterestRate;
        this.emiAmount          = emiAmount;
    }

    @Override
    public boolean deposit(double amount) {
        if (!isActive()) {
            System.out.println("Repayment failed: loan account is inactive.");
            return false;
        }
        if (!isValidAmount(amount)) {
            System.out.println("Repayment failed: amount must be greater than zero.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Repayment failed: amount exceeds outstanding loan.");
            return false;
        }

        double before = balance;
        balance -= amount;
        addTransaction("LOAN REPAYMENT", amount, before, balance, "EMI / part-payment received");
        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        System.out.println("Withdrawal not allowed from a loan account after sanction.");
        return false;
    }

    public void applyMonthlyInterest() {
        if (balance <= 0) return;
        double interest = balance * (annualInterestRate / 100.0) / 12.0;
        double before   = balance;
        balance += interest;
        addTransaction("INTEREST CHARGE", interest, before, balance, "Monthly loan interest applied");
    }

    @Override
    public String getAccountType()     { return "Loan Account"; }

    @Override
    protected String getBalanceLabel() { return "Outstanding Loan"; }

    @Override
    public double getNetPosition()     { return -balance; }

    @Override
    public String getExtraDetails() {
        return "Sanctioned Amount = " + formatMoney(sanctionedAmount) +
                ", Interest Rate = " + annualInterestRate + "% p.a., EMI = " +
                formatMoney(emiAmount);
    }

    public double getSanctionedAmount()   { return sanctionedAmount; }
    public double getAnnualInterestRate() { return annualInterestRate; }
    public double getEmiAmount()          { return emiAmount; }
}

import java.time.LocalDate;

public class SavingsAccount extends Account {
    private final double interestRate;
    private final double minimumBalance;
    private final int    dailyWithdrawalLimit;

    private int       withdrawalsToday;
    private LocalDate lastWithdrawalDate;

    public SavingsAccount(String accountNumber,
                          String customerId,
                          String accountHolderName,
                          String branchName,
                          String ifscCode,
                          double openingBalance,
                          double interestRate,
                          double minimumBalance,
                          int    dailyWithdrawalLimit) {

        super(accountNumber, customerId, accountHolderName, branchName, ifscCode, openingBalance);

        if (interestRate < 0)
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        if (minimumBalance < 0)
            throw new IllegalArgumentException("Minimum balance cannot be negative.");
        if (dailyWithdrawalLimit <= 0)
            throw new IllegalArgumentException("Daily withdrawal limit must be at least 1.");
        if (openingBalance < minimumBalance)
            throw new IllegalArgumentException("Opening balance cannot be less than minimum balance.");

        this.interestRate          = interestRate;
        this.minimumBalance        = minimumBalance;
        this.dailyWithdrawalLimit  = dailyWithdrawalLimit;
        this.withdrawalsToday      = 0;
        this.lastWithdrawalDate    = LocalDate.now();
    }

    @Override
    public boolean withdraw(double amount) {
        if (!isActive()) {
            System.out.println("Withdrawal failed: savings account is inactive.");
            return false;
        }
        if (!isValidAmount(amount)) {
            System.out.println("Withdrawal failed: amount must be greater than zero.");
            return false;
        }

        refreshWithdrawalCounter();

        if (withdrawalsToday >= dailyWithdrawalLimit) {
            System.out.println("Withdrawal failed: daily withdrawal count limit reached.");
            return false;
        }
        if ((getBalance() - amount) < minimumBalance) {
            System.out.println("Withdrawal failed: minimum balance of " +
                    formatMoney(minimumBalance) + " must be maintained.");
            return false;
        }

        boolean success = super.withdraw(amount);
        if (success) withdrawalsToday++;
        return success;
    }

    public void applyMonthlyInterest() {
        if (getBalance() <= 0) return;
        double interest = getBalance() * (interestRate / 100.0) / 12.0;
        double before   = balance;
        balance += interest;
        addTransaction("INTEREST CREDIT", interest, before, balance, "Monthly interest credited to savings account");
    }

    private void refreshWithdrawalCounter() {
        LocalDate today = LocalDate.now();
        if (!today.equals(lastWithdrawalDate)) {
            withdrawalsToday    = 0;
            lastWithdrawalDate  = today;
        }
    }

    @Override
    public String getAccountType() { return "Savings Account"; }

    @Override
    public String getExtraDetails() {
        int remaining = dailyWithdrawalLimit - withdrawalsToday;
        return "Interest Rate = " + interestRate + "% p.a., Minimum Balance = " +
                formatMoney(minimumBalance) +
                ", Daily Withdrawal Limit = " + dailyWithdrawalLimit +
                ", Remaining Today = " + remaining;
    }

    public double getInterestRate()       { return interestRate; }
    public double getMinimumBalance()     { return minimumBalance; }
    public int    getDailyWithdrawalLimit(){ return dailyWithdrawalLimit; }
}

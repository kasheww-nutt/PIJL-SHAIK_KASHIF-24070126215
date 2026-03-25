import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
    private final String accountNumber;
    private final String customerId;
    private final String accountHolderName;
    private final String branchName;
    private final String ifscCode;
    private final LocalDate openingDate;

    protected double balance;
    private boolean active;
    private final ArrayList<Transaction> transactions;

    public Account(String accountNumber,
                   String customerId,
                   String accountHolderName,
                   String branchName,
                   String ifscCode,
                   double openingBalance) {

        if (accountNumber == null || accountNumber.isBlank())
            throw new IllegalArgumentException("Account number cannot be empty.");
        if (customerId == null || customerId.isBlank())
            throw new IllegalArgumentException("Customer ID cannot be empty.");
        if (accountHolderName == null || accountHolderName.isBlank())
            throw new IllegalArgumentException("Account holder name cannot be empty.");
        if (branchName == null || branchName.isBlank())
            throw new IllegalArgumentException("Branch name cannot be empty.");
        if (ifscCode == null || ifscCode.isBlank())
            throw new IllegalArgumentException("IFSC code cannot be empty.");
        if (openingBalance < 0)
            throw new IllegalArgumentException("Opening balance cannot be negative.");

        this.accountNumber    = accountNumber;
        this.customerId       = customerId;
        this.accountHolderName = accountHolderName;
        this.branchName       = branchName;
        this.ifscCode         = ifscCode;
        this.balance          = openingBalance;
        this.openingDate      = LocalDate.now();
        this.active           = true;
        this.transactions     = new ArrayList<>();

        addTransaction("ACCOUNT OPENED", openingBalance, openingBalance, openingBalance, "Account created successfully");
    }

    public boolean deposit(double amount) {
        if (!active) { System.out.println("Deposit failed: account is inactive."); return false; }
        if (!isValidAmount(amount)) { System.out.println("Deposit failed: amount must be greater than zero."); return false; }

        double before = balance;
        balance += amount;
        addTransaction("DEPOSIT", amount, before, balance, "Amount deposited");
        return true;
    }

    public boolean withdraw(double amount) {
        if (!active) { System.out.println("Withdrawal failed: account is inactive."); return false; }
        if (!isValidAmount(amount)) { System.out.println("Withdrawal failed: amount must be greater than zero."); return false; }
        if (amount > balance) { System.out.println("Withdrawal failed: insufficient balance."); return false; }

        double before = balance;
        balance -= amount;
        addTransaction("WITHDRAW", amount, before, balance, "Amount withdrawn");
        return true;
    }

    protected boolean isValidAmount(double amount) { return amount > 0; }

    protected void addTransaction(String type, double amount, double beforeValue, double afterValue, String remarks) {
        transactions.add(new Transaction(type, amount, beforeValue, afterValue, remarks));
    }

    public String getAccountType()    { return "Generic Account"; }
    protected String getBalanceLabel(){ return "Balance"; }
    public double getNetPosition()    { return balance; }
    public String getExtraDetails()   { return "No additional rules."; }

    public String getAccountInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Account Type     : ").append(getAccountType()).append("\n");
        sb.append("Account Number   : ").append(accountNumber).append("\n");
        sb.append("Customer ID      : ").append(customerId).append("\n");
        sb.append("Account Holder   : ").append(accountHolderName).append("\n");
        sb.append("Branch           : ").append(branchName).append("\n");
        sb.append("IFSC             : ").append(ifscCode).append("\n");
        sb.append("Opening Date     : ").append(openingDate).append("\n");
        sb.append("Status           : ").append(active ? "ACTIVE" : "INACTIVE").append("\n");
        sb.append(String.format("%-16s: %s%n", getBalanceLabel(), formatMoney(balance)));
        sb.append("Details          : ").append(getExtraDetails()).append("\n");
        return sb.toString();
    }

    public String getMiniStatement(int limit) {
        StringBuilder sb = new StringBuilder();
        int start = Math.max(0, transactions.size() - limit);
        for (int i = start; i < transactions.size(); i++)
            sb.append("   ").append(transactions.get(i)).append("\n");
        return sb.toString();
    }

    public void deactivateAccount() { this.active = false; }
    public void activateAccount()   { this.active = true; }

    public String getAccountNumber()     { return accountNumber; }
    public String getCustomerId()        { return customerId; }
    public String getAccountHolderName(){ return accountHolderName; }
    public String getBranchName()        { return branchName; }
    public String getIfscCode()          { return ifscCode; }
    public LocalDate getOpeningDate()    { return openingDate; }
    public double getBalance()           { return balance; }
    public boolean isActive()            { return active; }
    public List<Transaction> getTransactions() { return Collections.unmodifiableList(transactions); }

    public static String formatMoney(double amount) { return String.format("Rs. %,.2f", amount); }
}

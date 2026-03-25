import java.util.ArrayList;

public class BankApplication {

    public static void main(String[] args) {
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Account>  accounts  = new ArrayList<>();

        // ── Create Customers ──────────────────────────────────────
        Customer c1 = new Customer("C101", "Aarav Mehta",     "9876543210", "aarav.mehta@email.com",  "Baner, Pune");
        Customer c2 = new Customer("C102", "Sneha Kulkarni",  "9765432109", "sneha.k@email.com",       "Kothrud, Pune");
        Customer c3 = new Customer("C103", "Rohan Patil",     "9654321098", "rohan.p@email.com",       "Hinjewadi, Pune");

        customers.add(c1);
        customers.add(c2);
        customers.add(c3);

        // ── Create Accounts ───────────────────────────────────────
        SavingsAccount sa1 = new SavingsAccount(
                "SB1001", c1.getCustomerId(), c1.getFullName(),
                "Pune Main Branch", "BANK0001001",
                50000, 4.50, 10000, 3);

        LoanAccount la1 = new LoanAccount(
                "LN2001", c1.getCustomerId(), c1.getFullName(),
                "Pune Loan Centre", "BANK0002001",
                350000, 11.25, 7860);

        SavingsAccount sa2 = new SavingsAccount(
                "SB1002", c2.getCustomerId(), c2.getFullName(),
                "Shivajinagar Branch", "BANK0001002",
                30000, 4.25, 5000, 2);

        LoanAccount la2 = new LoanAccount(
                "LN2002", c2.getCustomerId(), c2.getFullName(),
                "Shivajinagar Loan Desk", "BANK0002002",
                500000, 10.80, 10450);

        SavingsAccount sa3 = new SavingsAccount(
                "SB1003", c3.getCustomerId(), c3.getFullName(),
                "Hinjewadi Branch", "BANK0001003",
                15000, 4.00, 3000, 2);

        // ── Link Accounts to Customers ────────────────────────────
        linkAccount(c1, sa1, accounts);
        linkAccount(c1, la1, accounts);
        linkAccount(c2, sa2, accounts);
        linkAccount(c2, la2, accounts);
        linkAccount(c3, sa3, accounts);

        // ── Transactions: Customer 1 ──────────────────────────────
        sa1.deposit(7000);
        sa1.withdraw(12000);
        sa1.withdraw(5000);
        sa1.applyMonthlyInterest();

        la1.deposit(10000);
        la1.applyMonthlyInterest();
        la1.withdraw(1000);   // should be rejected

        // ── Transactions: Customer 2 ──────────────────────────────
        sa2.deposit(4500);
        sa2.withdraw(8000);
        sa2.applyMonthlyInterest();

        la2.deposit(15000);
        la2.applyMonthlyInterest();

        // ── Transactions: Customer 3 ──────────────────────────────
        sa3.deposit(5000);
        sa3.withdraw(4000);
        sa3.withdraw(7000);   // will hit min-balance rule
        sa3.applyMonthlyInterest();

        // ── Display All Accounts ──────────────────────────────────
        System.out.println("\n================= ALL ACCOUNTS =================");
        for (Account account : accounts) {
            System.out.println(account.getAccountInfo());
            System.out.println("Recent Transactions:");
            System.out.println(account.getMiniStatement(5));
            System.out.println("------------------------------------------------");
        }

        // ── Display Consolidated Customer View ────────────────────
        System.out.println("\n========== CUSTOMER CONSOLIDATED VIEW ==========");
        for (Customer customer : customers) {
            System.out.println(customer.getConsolidatedInfo());
        }
    }

    private static void linkAccount(Customer customer, Account account, ArrayList<Account> allAccounts) {
        customer.addAccount(account);
        allAccounts.add(account);
    }
}

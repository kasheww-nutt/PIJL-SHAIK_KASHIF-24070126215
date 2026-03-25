import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {
    private final String customerId;
    private final String fullName;
    private final String phoneNumber;
    private final String email;
    private final String address;
    private final ArrayList<Account> accounts;

    public Customer(String customerId,
                    String fullName,
                    String phoneNumber,
                    String email,
                    String address) {

        if (customerId == null || customerId.isBlank())
            throw new IllegalArgumentException("Customer ID cannot be empty.");
        if (fullName == null || fullName.isBlank())
            throw new IllegalArgumentException("Customer name cannot be empty.");
        if (phoneNumber == null || phoneNumber.isBlank())
            throw new IllegalArgumentException("Phone number cannot be empty.");
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email cannot be empty.");
        if (address == null || address.isBlank())
            throw new IllegalArgumentException("Address cannot be empty.");

        this.customerId  = customerId;
        this.fullName    = fullName;
        this.phoneNumber = phoneNumber;
        this.email       = email;
        this.address     = address;
        this.accounts    = new ArrayList<>();
    }

    public void addAccount(Account account) {
        if (account == null)
            throw new IllegalArgumentException("Account cannot be null.");
        if (!customerId.equals(account.getCustomerId()))
            throw new IllegalArgumentException("Customer ID mismatch while linking account.");
        for (Account existing : accounts)
            if (existing.getAccountNumber().equals(account.getAccountNumber()))
                throw new IllegalArgumentException("Duplicate account cannot be added.");
        accounts.add(account);
    }

    public double getTotalAssets() {
        double total = 0.0;
        for (Account a : accounts)
            if (a.getNetPosition() > 0) total += a.getNetPosition();
        return total;
    }

    public double getTotalLiabilities() {
        double total = 0.0;
        for (Account a : accounts)
            if (a.getNetPosition() < 0) total += Math.abs(a.getNetPosition());
        return total;
    }

    public double getNetRelationshipValue() { return getTotalAssets() - getTotalLiabilities(); }

    public String getConsolidatedInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("====================================================\n");
        sb.append("Customer ID     : ").append(customerId).append("\n");
        sb.append("Customer Name   : ").append(fullName).append("\n");
        sb.append("Phone           : ").append(phoneNumber).append("\n");
        sb.append("Email           : ").append(email).append("\n");
        sb.append("Address         : ").append(address).append("\n");
        sb.append("No. of Accounts : ").append(accounts.size()).append("\n");
        sb.append("----------------------------------------------------\n");

        if (accounts.isEmpty()) {
            sb.append("No accounts linked to this customer.\n");
        } else {
            for (Account account : accounts) {
                sb.append(account.getAccountInfo());
                sb.append("Mini Statement  :\n");
                sb.append(account.getMiniStatement(3));
                sb.append("----------------------------------------------------\n");
            }
        }

        sb.append("Total Assets    : ").append(Account.formatMoney(getTotalAssets())).append("\n");
        sb.append("Total Liab.     : ").append(Account.formatMoney(getTotalLiabilities())).append("\n");
        sb.append("Net Position    : ").append(Account.formatMoney(getNetRelationshipValue())).append("\n");
        sb.append("====================================================\n");
        return sb.toString();
    }

    public String getCustomerId()  { return customerId; }
    public String getFullName()    { return fullName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail()       { return email; }
    public String getAddress()     { return address; }
    public List<Account> getAccounts() { return Collections.unmodifiableList(accounts); }
}

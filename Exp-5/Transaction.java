import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final LocalDateTime timestamp;
    private final String type;
    private final double amount;
    private final double beforeValue;
    private final double afterValue;
    private final String remarks;

    public Transaction(String type, double amount, double beforeValue, double afterValue, String remarks) {
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.beforeValue = beforeValue;
        this.afterValue = afterValue;
        this.remarks = remarks;
    }

    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getRemarks() { return remarks; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a");
        return String.format(
                "[%s] %-18s Amount: %-12s Before: %-12s After: %-12s Remark: %s",
                timestamp.format(formatter),
                type,
                Account.formatMoney(amount),
                Account.formatMoney(beforeValue),
                Account.formatMoney(afterValue),
                remarks
        );
    }
}

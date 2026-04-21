public class MenuItemRecord {
    private int id;
    private String name;
    private double price;
    private int resId;

    public MenuItemRecord(int id, String name, double price, int resId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.resId = resId;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getResId() { return resId; }

    @Override
    public String toString() {
        return String.format("%-5d %-18s %-10.2f %-5d", id, name, price, resId);
    }
}
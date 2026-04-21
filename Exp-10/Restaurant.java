public class Restaurant {
    private int id;
    private String name;
    private String address;

    public Restaurant(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }

    @Override
    public String toString() {
        return String.format("%-5d %-20s %-25s", id, name, address);
    }
}
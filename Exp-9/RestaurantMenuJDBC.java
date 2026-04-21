import java.sql.*;

public class RestaurantMenuJDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_exp";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("==============================================================");
            System.out.println("     JDBC EXPERIMENT - RESTAURANT & MENUITEM CRUD OPERATIONS");
            System.out.println("==============================================================\n");

            createTables(con);
            insertRestaurantRecords(con);
            insertMenuItemRecords(con);

            printRestaurantTable(con, "1) RESTAURANT TABLE AFTER INSERT");
            printMenuItemTable(con, "2) MENUITEM TABLE AFTER INSERT");

            printItemsWithPriceLE100(con);

            printItemsAvailableInCafeJava(con);

            updatePriceLE100To200(con);
            printMenuItemTable(con, "5) MENUITEM TABLE AFTER PRICE UPDATE");

            deleteItemsStartingWithP(con);
            printMenuItemTable(con, "7) MENUITEM TABLE AFTER DELETE OPERATION");

            System.out.println("\nAll CRUD operations completed successfully.");
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    private static void createTables(Connection con) throws SQLException {
        String dropMenu = "DROP TABLE IF EXISTS MenuItem";
        String dropRestaurant = "DROP TABLE IF EXISTS Restaurant";

        String createRestaurant = "CREATE TABLE Restaurant (" +
                "id INT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "address VARCHAR(150) NOT NULL" +
                ")";

        String createMenuItem = "CREATE TABLE MenuItem (" +
                "id INT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "price DOUBLE NOT NULL, " +
                "resId INT, " +
                "FOREIGN KEY (resId) REFERENCES Restaurant(id)" +
                ")";

        try (Statement st = con.createStatement()) {
            st.executeUpdate(dropMenu);
            st.executeUpdate(dropRestaurant);
            st.executeUpdate(createRestaurant);
            st.executeUpdate(createMenuItem);
        }

        System.out.println("Tables created successfully.");
    }

    private static void insertRestaurantRecords(Connection con) throws SQLException {
        String sql = "INSERT INTO Restaurant (id, name, address) VALUES (?, ?, ?)";
        String[][] data = {
                {"1", "Cafe Java", "Pune"},
                {"2", "Food Planet", "Mumbai"},
                {"3", "Spice Hub", "Hyderabad"},
                {"4", "Urban Tadka", "Bengaluru"},
                {"5", "Tandoori Treat", "Delhi"},
                {"6", "Ocean Bite", "Chennai"},
                {"7", "Green Bowl", "Pune"},
                {"8", "Desi Corner", "Nagpur"},
                {"9", "Flavour Town", "Nashik"},
                {"10", "Royal Dine", "Ahmedabad"}
        };

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (String[] row : data) {
                ps.setInt(1, Integer.parseInt(row[0]));
                ps.setString(2, row[1]);
                ps.setString(3, row[2]);
                ps.executeUpdate();
            }
        }

        System.out.println("10 records inserted into Restaurant table.");
    }

    private static void insertMenuItemRecords(Connection con) throws SQLException {
        String sql = "INSERT INTO MenuItem (id, name, price, resId) VALUES (?, ?, ?, ?)";
        Object[][] data = {
                {101, "Pasta", 90.0, 1},
                {102, "Burger", 120.0, 1},
                {103, "Pizza", 100.0, 1},
                {104, "Tea", 40.0, 2},
                {105, "Coffee", 80.0, 2},
                {106, "Paneer Tikka", 180.0, 3},
                {107, "Poha", 60.0, 4},
                {108, "Paratha", 95.0, 5},
                {109, "Noodles", 130.0, 6},
                {110, "Pastry", 70.0, 1}
        };

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (Object[] row : data) {
                ps.setInt(1, (int) row[0]);
                ps.setString(2, (String) row[1]);
                ps.setDouble(3, (double) row[2]);
                ps.setInt(4, (int) row[3]);
                ps.executeUpdate();
            }
        }

        System.out.println("10 records inserted into MenuItem table.\n");
    }

    private static void printRestaurantTable(Connection con, String title) throws SQLException {
        String sql = "SELECT * FROM Restaurant ORDER BY id";
        System.out.println(title);
        System.out.println("--------------------------------------------------------------");
        System.out.printf("%-5s %-18s %-20s%n", "ID", "NAME", "ADDRESS");
        System.out.println("--------------------------------------------------------------");

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("%-5d %-18s %-20s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"));
            }
        }
        System.out.println();
    }

    private static void printMenuItemTable(Connection con, String title) throws SQLException {
        String sql = "SELECT * FROM MenuItem ORDER BY id";
        System.out.println(title);
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("%-5s %-18s %-10s %-8s%n", "ID", "NAME", "PRICE", "RES_ID");
        System.out.println("---------------------------------------------------------------------");

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("%-5d %-18s %-10.2f %-8d%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("resId"));
            }
        }
        System.out.println();
    }

    private static void printItemsWithPriceLE100(Connection con) throws SQLException {
        String sql = "SELECT * FROM MenuItem WHERE price <= 100 ORDER BY id";
        System.out.println("3) MENUITEM RECORDS WHERE PRICE <= 100");
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("%-5s %-18s %-10s %-8s%n", "ID", "NAME", "PRICE", "RES_ID");
        System.out.println("---------------------------------------------------------------------");

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("%-5d %-18s %-10.2f %-8d%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("resId"));
            }
        }
        System.out.println();
    }

    private static void printItemsAvailableInCafeJava(Connection con) throws SQLException {
        String sql = "SELECT m.id, m.name, m.price, r.name AS restaurant_name " +
                "FROM MenuItem m JOIN Restaurant r ON m.resId = r.id " +
                "WHERE r.name = ? ORDER BY m.id";

        System.out.println("4) MENUITEMS AVAILABLE IN RESTAURANT 'Cafe Java'");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-5s %-18s %-10s %-18s%n", "ID", "ITEM_NAME", "PRICE", "RESTAURANT");
        System.out.println("--------------------------------------------------------------------------------");

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "Cafe Java");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("%-5d %-18s %-10.2f %-18s%n",
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getString("restaurant_name"));
                }
            }
        }
        System.out.println();
    }

    private static void updatePriceLE100To200(Connection con) throws SQLException {
        String sql = "UPDATE MenuItem SET price = 200 WHERE price <= 100";
        try (Statement st = con.createStatement()) {
            int rows = st.executeUpdate(sql);
            System.out.println("6) UPDATE OPERATION");
            System.out.println(rows + " record(s) updated where price <= 100 to price = 200.\n");
        }
    }

    private static void deleteItemsStartingWithP(Connection con) throws SQLException {
        String sql = "DELETE FROM MenuItem WHERE name LIKE 'P%'";
        try (Statement st = con.createStatement()) {
            int rows = st.executeUpdate(sql);
            System.out.println("8) DELETE OPERATION");
            System.out.println(rows + " record(s) deleted where item name starts with P.\n");
        }
    }
}

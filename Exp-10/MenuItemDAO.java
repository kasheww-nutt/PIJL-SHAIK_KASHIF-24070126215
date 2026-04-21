import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDAO {

    public void insertMenuItem(MenuItemRecord item) {
        String sql = "INSERT INTO MenuItem(id, name, price, resId) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, item.getId());
            ps.setString(2, item.getName());
            ps.setDouble(3, item.getPrice());
            ps.setInt(4, item.getResId());
            ps.executeUpdate();
            System.out.println("Inserted Menu Item: " + item.getName());
        } catch (SQLException e) {
            System.out.println("Error inserting menu item: " + e.getMessage());
        }
    }

    public List<MenuItemRecord> getAllMenuItems() {
        List<MenuItemRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM MenuItem ORDER BY id";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new MenuItemRecord(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("resId")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching menu items: " + e.getMessage());
        }
        return list;
    }

    public void updateMenuItem(int id, String newName, double newPrice, int newResId) {
        String sql = "UPDATE MenuItem SET name = ?, price = ?, resId = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setDouble(2, newPrice);
            ps.setInt(3, newResId);
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Menu item updated successfully." : "Menu item id not found.");
        } catch (SQLException e) {
            System.out.println("Error updating menu item: " + e.getMessage());
        }
    }

    public void deleteMenuItem(int id) {
        String sql = "DELETE FROM MenuItem WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Menu item deleted successfully." : "Menu item id not found.");
        } catch (SQLException e) {
            System.out.println("Error deleting menu item: " + e.getMessage());
        }
    }

    public List<MenuItemRecord> getItemsPriceLessThanEqual100() {
        List<MenuItemRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM MenuItem WHERE price <= 100 ORDER BY id";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new MenuItemRecord(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("resId")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching filtered items: " + e.getMessage());
        }
        return list;
    }
}
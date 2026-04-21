import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDAO {

    public void insertRestaurant(Restaurant restaurant) {
        String sql = "INSERT INTO Restaurant(id, name, address) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, restaurant.getId());
            ps.setString(2, restaurant.getName());
            ps.setString(3, restaurant.getAddress());
            ps.executeUpdate();
            System.out.println("Inserted Restaurant: " + restaurant.getName());
        } catch (SQLException e) {
            System.out.println("Error inserting restaurant: " + e.getMessage());
        }
    }

    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> list = new ArrayList<>();
        String sql = "SELECT * FROM Restaurant ORDER BY id";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Restaurant(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching restaurants: " + e.getMessage());
        }
        return list;
    }

    public void updateRestaurant(int id, String newName, String newAddress) {
        String sql = "UPDATE Restaurant SET name = ?, address = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setString(2, newAddress);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Restaurant updated successfully." : "Restaurant id not found.");
        } catch (SQLException e) {
            System.out.println("Error updating restaurant: " + e.getMessage());
        }
    }

    public void deleteRestaurant(int id) {
        String sql = "DELETE FROM Restaurant WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Restaurant deleted successfully." : "Restaurant id not found.");
        } catch (SQLException e) {
            System.out.println("Error deleting restaurant: " + e.getMessage());
        }
    }
}
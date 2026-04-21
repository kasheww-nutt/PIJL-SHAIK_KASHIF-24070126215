import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RestaurantMenuJavaFXApp extends Application {

    private TextArea outputArea = new TextArea();
    private RestaurantDAO restaurantDAO = new RestaurantDAO();
    private MenuItemDAO menuItemDAO = new MenuItemDAO();

    @Override
    public void start(Stage stage) {
        Label title = new Label("Restaurant and MenuItem CRUD - JavaFX + JDBC");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ComboBox<String> tableBox = new ComboBox<>(FXCollections.observableArrayList("Restaurant", "MenuItem"));
        tableBox.setValue("Restaurant");

        ComboBox<String> operationBox = new ComboBox<>(FXCollections.observableArrayList("Insert", "Select", "Update", "Delete"));
        operationBox.setValue("Insert");

        TextField idField = new TextField();
        idField.setPromptText("Id");

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField addressField = new TextField();
        addressField.setPromptText("Address (Restaurant only)");

        TextField priceField = new TextField();
        priceField.setPromptText("Price (MenuItem only)");

        TextField resIdField = new TextField();
        resIdField.setPromptText("Restaurant Id (MenuItem only)");

        Button executeBtn = new Button("Execute");
        Button showAllBtn = new Button("Show All");
        Button clearBtn = new Button("Clear Output");

        outputArea.setEditable(false);
        outputArea.setPrefRowCount(20);
        outputArea.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 13px;");

        executeBtn.setOnAction(e -> {
            String selectedTable = tableBox.getValue();
            String selectedOperation = operationBox.getValue();

            try {
                if ("Restaurant".equals(selectedTable)) {
                    handleRestaurantOperation(selectedOperation, idField, nameField, addressField);
                } else {
                    handleMenuItemOperation(selectedOperation, idField, nameField, priceField, resIdField);
                }
            } catch (Exception ex) {
                appendOutput("Error: " + ex.getMessage());
            }
        });

        showAllBtn.setOnAction(e -> {
            appendOutput("\n================ CURRENT RESTAURANTS ================");
            appendOutput(String.format("%-5s %-20s %-25s", "ID", "NAME", "ADDRESS"));
            for (Restaurant restaurant : restaurantDAO.getAllRestaurants()) {
                appendOutput(restaurant.toString());
            }

            appendOutput("\n================ CURRENT MENU ITEMS =================");
            appendOutput(String.format("%-5s %-18s %-10s %-5s", "ID", "NAME", "PRICE", "RID"));
            for (MenuItemRecord item : menuItemDAO.getAllMenuItems()) {
                appendOutput(item.toString());
            }
        });

        clearBtn.setOnAction(e -> outputArea.clear());

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.add(new Label("Table"), 0, 0);
        form.add(tableBox, 1, 0);
        form.add(new Label("Operation"), 0, 1);
        form.add(operationBox, 1, 1);
        form.add(new Label("Id"), 0, 2);
        form.add(idField, 1, 2);
        form.add(new Label("Name"), 0, 3);
        form.add(nameField, 1, 3);
        form.add(new Label("Address"), 0, 4);
        form.add(addressField, 1, 4);
        form.add(new Label("Price"), 0, 5);
        form.add(priceField, 1, 5);
        form.add(new Label("ResId"), 0, 6);
        form.add(resIdField, 1, 6);

        HBox buttonBox = new HBox(10, executeBtn, showAllBtn, clearBtn);
        VBox root = new VBox(15, title, form, buttonBox, outputArea);
        root.setPadding(new Insets(16));

        Scene scene = new Scene(root, 780, 650);
        stage.setTitle("JavaFX JDBC CRUD");
        stage.setScene(scene);
        stage.show();
    }

    private void handleRestaurantOperation(String operation, TextField idField, TextField nameField, TextField addressField) {
        int id = parseIntField(idField, "Id");

        switch (operation) {
            case "Insert":
                restaurantDAO.insertRestaurant(new Restaurant(
                        id,
                        nameField.getText().trim(),
                        addressField.getText().trim()
                ));
                appendOutput("Inserted into Restaurant table.");
                break;

            case "Select":
                appendOutput("\nRestaurant Records:");
                appendOutput(String.format("%-5s %-20s %-25s", "ID", "NAME", "ADDRESS"));
                for (Restaurant restaurant : restaurantDAO.getAllRestaurants()) {
                    appendOutput(restaurant.toString());
                }
                break;

            case "Update":
                restaurantDAO.updateRestaurant(id, nameField.getText().trim(), addressField.getText().trim());
                appendOutput("Restaurant update operation completed.");
                break;

            case "Delete":
                restaurantDAO.deleteRestaurant(id);
                appendOutput("Restaurant delete operation completed.");
                break;
        }
    }

    private void handleMenuItemOperation(String operation, TextField idField, TextField nameField, TextField priceField, TextField resIdField) {
        int id = parseIntField(idField, "Id");

        switch (operation) {
            case "Insert":
                double price = parseDoubleField(priceField, "Price");
                int resId = parseIntField(resIdField, "ResId");
                menuItemDAO.insertMenuItem(new MenuItemRecord(
                        id,
                        nameField.getText().trim(),
                        price,
                        resId
                ));
                appendOutput("Inserted into MenuItem table.");
                break;

            case "Select":
                appendOutput("\nMenuItem Records:");
                appendOutput(String.format("%-5s %-18s %-10s %-5s", "ID", "NAME", "PRICE", "RID"));
                for (MenuItemRecord item : menuItemDAO.getAllMenuItems()) {
                    appendOutput(item.toString());
                }
                break;

            case "Update":
                double newPrice = parseDoubleField(priceField, "Price");
                int newResId = parseIntField(resIdField, "ResId");
                menuItemDAO.updateMenuItem(id, nameField.getText().trim(), newPrice, newResId);
                appendOutput("MenuItem update operation completed.");
                break;

            case "Delete":
                menuItemDAO.deleteMenuItem(id);
                appendOutput("MenuItem delete operation completed.");
                break;
        }
    }

    private int parseIntField(TextField field, String label) {
        String value = field.getText().trim();
        if (value.isEmpty()) {
            throw new IllegalArgumentException(label + " cannot be empty.");
        }
        return Integer.parseInt(value);
    }

    private double parseDoubleField(TextField field, String label) {
        String value = field.getText().trim();
        if (value.isEmpty()) {
            throw new IllegalArgumentException(label + " cannot be empty.");
        }
        return Double.parseDouble(value);
    }

    private void appendOutput(String text) {
        outputArea.appendText(text + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
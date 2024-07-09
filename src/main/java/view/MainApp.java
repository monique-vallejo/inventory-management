
package view;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.model.InHouse;
import view.model.Inventory;
import view.model.Outsourced;
import view.model.Product;

import java.io.IOException;


public class MainApp extends Application {
    /**
     * This connects to the main screen which is main-view.fxml and welcomes the users in.
     * @param stage Stage
     * @throws IOException FXMLLoader
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 980, 600);
        stage.setTitle("Welcome to the Inventory Management System!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *  This allows the user to return to the main screen, which is main-view.fxml.
     * @param actionEvent Action Event
     * @throws IOException FXMLLoader
     */
    public static void returnMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main-view.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 980, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This helps generate all the parts IDs from Inventory.
     * @return i
     * RUNTIME ERROR: The parts were not generating. It was due to have the logical operators wrong.
     */
    public static int generatePTsID()
    {
        int i;
        for (i = 0; i <= Inventory.getAllParts().size();)
            i++;
        return i;
    }
    /**
     * This helps generate all the products IDs from Inventory.
     * @return i
     */
    public static int generatePDsID() {
        int i;
        for (i = 0; i <= Inventory.getAllProducts().size(); )
            i++;
        return i;
    }

    /**
     * This launches the main application.
     * @param args String[]
     */

    public static void main(String[] args) {
        InHouse ih1 = new InHouse(generatePTsID(), "Tire", 150.00, 3, 0, 10, 0013);
        Inventory.addPart(ih1);

        Outsourced os1 = new Outsourced(generatePTsID(), "Windshield Wiper", 6.20, 4, 0, 10, "Autozone");
        Inventory.addPart(os1);

        Product pd1 = new Product(generatePDsID(), "Headlight", 64.25, 5, 1, 5);
        Inventory.addProduct(pd1);

        Product pd2 = new Product(generatePDsID(), "AC", 154.23, 20, 1, 30);
        Inventory.addProduct(pd2);

        Product pd3 = new Product(generatePDsID(), "Car Perfume", 3.56, 10, 4, 25);
        Inventory.addProduct(pd3);

        InHouse ih2 = new InHouse(generatePTsID(), "Oil", 19.00, 12, 0, 15, 0045);
        Inventory.addPart(ih2);


        Outsourced os2 = new Outsourced(generatePTsID(), "Rear Mirror", 45.62, 0, 0, 15, "The Pa Shop");
        Inventory.addPart(os2);

        Product pd4 = new Product(generatePDsID(), "Wheel", 132.34, 3, 2, 10);
        Inventory.addProduct(pd4);
        launch();
    }
}

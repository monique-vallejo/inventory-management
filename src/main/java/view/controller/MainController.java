package view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import view.MainApp;
import view.model.Inventory;
import view.model.Part;
import view.model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

    /**
     * FUTURE ENHANCEMENT: For the main controller, providing more than just products and parts would be a nice update. Or, more columns.
     * This is a public class the MainController that is tied to the main-view.fxml file.

     */
public class MainController implements Initializable {
        /**
         * The following is all the items for the parts table that is included inside the main screen.
         * The columns that are included are: id, name, inventory level, and price.
         */
        @FXML
        private TableView<Part> parts_table;
        @FXML
        private TableColumn<Part, Integer> ppt_id;
        @FXML
        private TableColumn<Part, String> ppt_name;
        @FXML
        private TableColumn<Part, Integer> ppt_lvl;
        @FXML
        private TableColumn<Part, Double> ppt_price;
        /**
         * The following is all the items for the products table that is included inside the main screen.
         * The columns that are included are: id, name, inventory level, and price.
         */
        @FXML
        private TableView<Product> products_table;
        @FXML
        private TableColumn<Product, Integer> ppd_id;
        @FXML
        private TableColumn<Product, String> ppd_name;
        @FXML
        private TableColumn<Product, Integer> ppd_lvl;
        @FXML
        private TableColumn<Product, Double> ppd_price;
        /**
         * The two following are text fields.
         * One is inside the products table and the other is in the parts table.
         * They are essentially search bars.
         */
        @FXML
        private TextField searchPTs_txt;
        @FXML
        private TextField searchPDs_txt;
        ObservableList<Part> searchPart = FXCollections.observableArrayList();
        ObservableList<Product> searchProduct = FXCollections.observableArrayList();

        /**
         * This pulls up the addPART-view.fxml and connects to the controller designated for that scene.
         *
         * @param actionEvent addCLICK
         * @throws IOException FXMLLoader
         */

        public void addCLICK(ActionEvent actionEvent) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("addPART-view.fxml"));
            Stage stage = (Stage) ((Button) (actionEvent.getSource())).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 537, 546);
            stage.setScene(scene);
            stage.show();
        }

        /**
         * This pulls up the modifyPART-view.fxml and connects to the controller designated for that scene.
         *
         * @param actionEvent modifyPTCLICK
         * @throws IOException FXMLLoader
         */
        public void modifyPTclick(ActionEvent actionEvent) throws IOException {
            Part selectedPart = parts_table.getSelectionModel().getSelectedItem();
            if (selectedPart == null) {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("Sorry! Not found.");
                warning.setContentText("Oops! Try again. Nothing was selected.");
                warning.showAndWait();
            } else {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("modifyPART-view.fxml"));
                loader.load();
                ModifyPARTController mp_controller = loader.getController();
                mp_controller.recievePT(parts_table.getSelectionModel().getSelectedItem());
                Stage stage = (Stage) ((Button) (actionEvent.getSource())).getScene().getWindow();
                Scene scene = new Scene(loader.getRoot(), 537, 546);
                stage.setScene(scene);
                stage.show();
            }


        }

        /**
         * This pulls up the addPRODUCT-view.fxml and connects to the controller designated for that scene.
         *
         * @param actionEvent addPDclick
         * @throws IOException FXMLLoader
         */
        public void addPDclick(ActionEvent actionEvent) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("addPRODUCT-view.fxml"));
            Stage stage = (Stage) ((Button) (actionEvent.getSource())).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 928, 548);
            stage.setScene(scene);
            stage.show();
        }

        /**
         * This pulls up the modifyPRODUCT-view.fxml and connects to the controller designated for that scene.
         *
         * @param actionEvent modifyPDclick
         * @throws IOException FXMLLoader
         */
        public void modifyPDclick(ActionEvent actionEvent) throws IOException {
            Product selcetedProduct = products_table.getSelectionModel().getSelectedItem();
            if (selcetedProduct == null) {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("Sorry! Not found.");
                warning.setContentText("Oops! Try again. Nothing was selected.");
                warning.showAndWait();
            } else {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("modifyPRODUCT-view.fxml"));
                loader.load();
                ModifyPRODUCTController mpr_controller = loader.getController();
                mpr_controller.receivePD(products_table.getSelectionModel().getSelectedItem());
                Stage stage = (Stage) ((Button) (actionEvent.getSource())).getScene().getWindow();
                Scene scene = new Scene(loader.getRoot(), 928, 548);
                stage.setScene(scene);
                stage.show();
            }
        }

        /**
         * This exits out of the application.
         *
         * @param actionEvent mainexitCLICK
         */
        public void mainexitCLICK(ActionEvent actionEvent) {
            final Button source = (Button) actionEvent.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }

        /**
         * This is the search bar for the parts table.
         * It allows one to search for an item based on their name or ID.
         * It also runs error messages if invalid actions are performed.
         *
         * @param actionEvent ptSearch
         */
        public void ptSearch(ActionEvent actionEvent) {

            try {
                try {
                    int id = Integer.parseInt(searchPTs_txt.getText());
                    searchPart.add(Inventory.lookupPart(id));
                } catch (Exception e) {
                    String name = searchPTs_txt.getText();
                    searchPart = Inventory.lookupPart(name);
                }


                if (searchPart.size() == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setContentText("Item does not exist");
                    alert.showAndWait();

                }
                else {
                    parts_table.setItems(searchPart);
                    ppt_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                    ppt_name.setCellValueFactory(new PropertyValueFactory<>("name"));
                    ppt_lvl.setCellValueFactory(new PropertyValueFactory<>("stock"));
                    ppt_price.setCellValueFactory(new PropertyValueFactory<>("price"));
                }
            }
            finally {
                if (searchPTs_txt.getText().equals("")) {
                    parts_table.setItems(Inventory.getAllParts());
                    parts_table.refresh();
                }
            }
        }


        /**
         * This is the search bar for the products table.
         * It allows one to search for an item based on their name or ID.
         * It also runs error messages if invalid actions are performed.
         *
         * @param keyEvent keyR_PDs
         */
        public void keyR_PDs(KeyEvent keyEvent) {
            String searchedProduct = searchPDs_txt.getText();
            ObservableList<Product> filteredProducts = Inventory.lookupProduct(searchedProduct);
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Sorry! Not found.");
            warning.setContentText("Sorry! No items were found.");

            if (filteredProducts.isEmpty()) {
                try {
                    int id_searchedProduct = Integer.parseInt(searchedProduct);
                    Product product = Inventory.lookupProduct(id_searchedProduct);
                    if (product != null)
                        filteredProducts.add(product);
                } catch (NumberFormatException e) {/*ignore*/}
            }

            if (filteredProducts.isEmpty()) {
                warning.showAndWait();
            }


            products_table.setItems(filteredProducts);
        }

        /**
         * This allows the user to select an item from the parts table and decide whether they want to delete it.
         * Error messages also pop up if invalid actions are performed.
         *
         * @param actionEvent PTdeleteCLICK
         */
        public void PTdeleteCLICK(ActionEvent actionEvent) {


            if ((parts_table.getSelectionModel().getSelectedItem() == null)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Sorry! Nothing was picked!");
                alert.setContentText("Please select something to continue!");
                alert.showAndWait();
            } else {

                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this?");
                Optional<ButtonType> choice = alert1.showAndWait();
                if (choice.isPresent() && choice.get() == ButtonType.OK) {
                    Inventory.deletePart(parts_table.getSelectionModel().getSelectedItem());


                }
            }
        }



    /**
     * This allows the user to select an item from the products table and decide whether they want to delete it.
     * Error messages also pop up if invalid actions are performed.
     * ERROR RUNTIME: The program would crash when nothing was deleted. So, I generated an error message instead.
     * @param actionEvent PDdeleteCLICK
     */
    public void PDdeleteCLICK(ActionEvent actionEvent) {

        if ((products_table.getSelectionModel().getSelectedItem() == null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sorry! Nothing was picked!");
            alert.setContentText("Please select something to continue!");
            alert.showAndWait();
        }

        else {

            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this?");
            Optional<ButtonType> choice = alert1.showAndWait();
            if (choice.isPresent() && choice.get() == ButtonType.OK) {
                Product product = products_table.getSelectionModel().getSelectedItem();

                for (Product products : Inventory.getAllProducts()) {
                    for (int i = 0; i < products.getAllAssociatedParts().size(); i++) {
                        if (!product.getAllAssociatedParts().isEmpty()) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Sorry! We can't delete that!");
                            alert.setHeaderText("Deletion Error!");
                            alert.setContentText("There are one or more parts that are associated to this product!");
                            alert.show();
                            return;
                        }
                    }
                }

                if (!Inventory.deleteProduct(product)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Deletion Error");
                    alert.setContentText("Unable to delete item");
                    alert.show();

                }

            }


        }
    }


        /**
         * This displays two tables -- the products and the parts table; each with their own respective sample data.
         * @param url URL
         * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        System.out.println("main-view has been officially initialized!");

        parts_table.setItems(Inventory.getAllParts());

        ppt_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ppt_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        ppt_lvl.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ppt_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        products_table.setItems(Inventory.getAllProducts());
        ppd_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ppd_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        ppd_lvl.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ppd_price.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}

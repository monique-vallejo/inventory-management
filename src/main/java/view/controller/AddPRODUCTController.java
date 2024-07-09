package view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import view.MainApp;
import view.model.INVALID;
import view.model.Inventory;
import view.model.Part;
import view.model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

    /**
     *FUTURE ENHANCEMENT: A future enhancement would be offering more text fields to give more information about the product added.
     * This is a public class that is a controller class for the addPRODUCT-view.fxml file.
     * This will provide control what products will be added to the main menu.


    */

public class AddPRODUCTController implements Initializable {
        /**
             * The following are all textfields that will contain the data on the product that one wants to add.
             * The fields include the following: id, name, inventory, price, min, and max.
             * The other textfield is the textfield for the search bar that is located in the corner of the scene.
         */
    @FXML
    private TextField apd_id;
    @FXML
    private TextField apd_name;
    @FXML
    private TextField apd_invent;
    @FXML
    private TextField apd_price;
    @FXML
    private TextField apd_max;
    @FXML
    private TextField apd_min;
    @FXML
    private TextField apd_search;
        /**
             * The following is the items for the first table in the scene.
             * The table includes several columns such as id, name, inventory level, and price.
         */
    @FXML
    private TableView<Part> apd_table;
    @FXML
    private TableColumn<Part, Integer> apdt_id;
    @FXML
    private TableColumn<Part, String> apdt_name;
    @FXML
    private TableColumn<Part, Integer> apdt_level;
    @FXML
    private TableColumn<Part, Integer> apdt_price;
        /**
             * The following is the items for the second table in the scene.
             * The table includes several columns such as id, name, inventory level, and price.
             * Unlike the first table, this table removes any associated product that is chosen.
         */
    @FXML
    private TableView<Part> apd_remove;
    @FXML
    private TableColumn<Part, Integer> apdr_id;
    @FXML
    private TableColumn<Part, String> apdr_name;
    @FXML
    private TableColumn<Part, Integer> apdr_invent;
    @FXML
    private TableColumn<Part, Integer> apdr_price;

    private final ObservableList<Part> assocPTlist = FXCollections.observableArrayList();


        /**
             * This is a cancel button.
             * @param actionEvent apd_cancelCLICK.
             * @throws IOException from FXMLLoader.
         */
    public void apd_cancelCLICK(ActionEvent actionEvent) throws IOException
    {
        MainApp.returnMain(actionEvent);
    }

        /**
             * This is a save button. It allows the user to save the information of the added product.
             * The information includes name, price, inventory(stock), min, and max.
             * This also includes error messages depending on the min and max.
             * This reassures that the data inserted is valid.
             * @param actionEvent apd_saveCLICK.
             * @throws IOException from FXMLLoader.
         */
    public void apd_saveCLICK(ActionEvent actionEvent) throws IOException
    {
        try
        {
            String pdName = apd_name.getText();
            double pdPrice = Double.parseDouble(apd_price.getText());
            int pdStock = Integer.parseInt(apd_invent.getText());
            int pdMin = Integer.parseInt(apd_min.getText());
            int pdMax = Integer.parseInt(apd_max.getText());

            if (pdMin >= pdMax)
                throw new INVALID(pdStock, pdMin, pdMax);
            if (pdStock > pdMax || pdStock < pdMin)
                throw new INVALID(pdStock, pdMin, pdMax);
            else {
                Product newProduct = new Product(MainApp.generatePDsID(), pdName, pdPrice, pdStock, pdMin, pdMax);
                for (Part part : assocPTlist)
                    newProduct.addAssociatedPart(part);
                Inventory.addProduct(newProduct);
            }

            MainApp.returnMain(actionEvent);
        }
        catch (NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sorry! That data is invalid.");
            alert.setContentText("Check that all your values are correct.");
            alert.showAndWait();


        }
        catch (INVALID e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sorry! Invalid values!");
            alert.setContentText(e.errorMessage());
            alert.showAndWait();
        }

    }
        /**
             * This is the search button in the corner of the scene.
             * Products/Parts are allowed to be searched by name or ID.
             * @param keyEvent apd_keyRelease
         */

    public void apd_keyRelease(KeyEvent keyEvent)
    {
        String searchedPart = apd_search.getText();
        ObservableList<Part> filteredParts = Inventory.lookupPart(searchedPart);
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Warning! Not found.");
        warning.setContentText("Sorry! No items were found at the moment.");

        if (filteredParts.isEmpty()) {
            try {
                int id_searchedPart = Integer.parseInt(searchedPart);
                Part part = Inventory.lookupPart(id_searchedPart);
                if (part != null)
                    filteredParts.add(part);
            }
            catch (NumberFormatException e)
            {/*ignore*/}
        }

        if (filteredParts.isEmpty())
        {
            warning.showAndWait();
        }
        apd_table.setItems(filteredParts);
    }

        /**
             * This is an add button for the associated table.
             * It adds the piece that is associated to the table.
         * RUNTIME ERROR: The if statement kept coming up as an error until I fixed it with null.
             * @param actionEvent apd_addCLICK.
         */
    public void apd_addCLICK(ActionEvent actionEvent)
    {
        Part selectedPart;
        selectedPart = apd_table.getSelectionModel().getSelectedItem();

        if (selectedPart != null)
            assocPTlist.add(selectedPart);

    }

        /**
             * This is a remove button.
             * This button will remove the select item from the associated table.
             * @param actionEvent apd_removeCLICK.
         */
    public void apd_removeCLICK(ActionEvent actionEvent)
    {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Sorry! Not found.");
        warning.setContentText("Oops! Nothing was selected and deleted!");
        Part selectedPart = apd_remove.getSelectionModel().getSelectedItem();


        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to remove this part from the table?");
        Optional<ButtonType> choice = confirm.showAndWait();

        if (selectedPart == null) {
            warning.showAndWait();
        }
        else if (choice.isPresent() && choice.get() == ButtonType.OK )
        {
            assocPTlist.remove(selectedPart);
        }

    }

        /**
         * This initializes the addPRODUCTController and gives products to the system.
         * @param url URL
         * @param resourceBundle ResourceBundle
         */
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            System.out.println("addPRODUCT-view has been officially initialized!");
            apd_id.setEditable(false);
            apd_id.setDisable(true);

            apd_table.setItems(Inventory.getAllParts());

            apdt_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            apdt_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            apdt_level.setCellValueFactory(new PropertyValueFactory<>("stock"));
            apdt_price.setCellValueFactory(new PropertyValueFactory<>("price"));

            apd_remove.setItems(assocPTlist);
            apdr_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            apdr_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            apdr_invent.setCellValueFactory(new PropertyValueFactory<>("stock"));
            apdr_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        }


}

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
     * FUTURE ENHANCEMENT: The ability to modify more than just one product.
     * This is a public class that is a controller class for the modifyPRODUCT-view.fxml file.
     * This will provide control what products will be added to the main menu.


 */
public class ModifyPRODUCTController implements Initializable
{
    /**
     * The following are all textfields that will contain the data on the product that one wants to add.
     * The fields include the following: id, name, inventory, price, min, and max.
     * The other textfield is the textfield for the search bar that is located in the corner of the scene.
     */
    @FXML
    private TextField mpd_id;
    @FXML
    private TextField mpd_name;
    @FXML
    private TextField mpd_invent;
    @FXML
    private TextField mpd_price;
    @FXML
    private TextField mpd_max;
    @FXML
    private TextField mpd_min;
    @FXML
    private TextField mpd_search;

    /**
         * The following is the items for the first table in the scene.
         * The table includes several columns such as id, name, inventory level, and price.
     */
    @FXML
    private TableView<Part> mpd_table;
    @FXML
    private TableColumn<Part, Integer> mpdt_id;
    @FXML
    private TableColumn<Part, String> mpdt_name;
    @FXML
    private TableColumn<Part, Integer> mpdt_invent;
    @FXML
    private TableColumn<Part, Integer> mpdt_price;

    /**
     * The following is the items for the second table in the scene.
     * The table includes several columns such as id, name, inventory level, and price.
     * Unlike the first table, this table removes any associated product that is chosen.
     */
    @FXML
    private TableView<Part> mpd_removetable;
    @FXML
    private TableColumn<Part, Integer> mpdr_id;
    @FXML
    private TableColumn<Part, String> mpdr_name;
    @FXML
    private TableColumn<Part, Integer> mpdr_invent;
    @FXML
    private TableColumn<Part, Integer> mpdr_price;
    @FXML
    private ObservableList<Part> assocPTlist = FXCollections.observableArrayList();

    /**
         * This is a cancel button.
         * @param actionEvent mpd_cancelCLICK.
         * @throws IOException from FXMLLoader.
     */
    public void mpd_cancelCLICK(ActionEvent actionEvent) throws IOException
    {
        MainApp.returnMain(actionEvent);
    }

    /**
         * This is a save button. It allows the user to save the information of the added product.
         * The information includes name, price, inventory(stock), min, and max.
         * This also includes error messages depending on the min and max.
         * This reassures that the data inserted is valid.
         * @param actionEvent mpd_saveCLICK.
         * @throws IOException from FXMLLoader.
     * ERROR RUNTIME: The alerts weren't showing up, and that was due to missing the showAndWait.
     */
    public void mpd_saveCLICK(ActionEvent actionEvent) throws IOException
    {
        try
        {
            int id = Integer.parseInt(mpd_id.getText());
            String name = mpd_name.getText();
            int pdStock = Integer.parseInt(mpd_invent.getText());
            double price = Double.parseDouble(mpd_price.getText());
            int pdMax = Integer.parseInt(mpd_max.getText());
            int pdMin = Integer.parseInt(mpd_min.getText());

            Product newProduct = new Product(id, name, price, pdStock, pdMin, pdMax);
            for (Part part : assocPTlist)
            {
                newProduct.addAssociatedPart(part);
            }
            Inventory.updateProduct(id, newProduct);
            if (pdMin >= pdMax)
                throw new INVALID(pdStock, pdMin, pdMax);
            if (pdStock > pdMax || pdStock < pdMin)
                throw new INVALID(pdStock, pdMin, pdMax);

            MainApp.returnMain(actionEvent);
        }
        catch (NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sorry! Invalid data!");
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
         * This allows the different products to be received.
         * @param selectedProduct Product
     */
    public void receivePD(Product selectedProduct)
    {
        mpd_id.setText(String.valueOf(selectedProduct.getId()));
        mpd_name.setText(selectedProduct.getName());
        mpd_invent.setText(String.valueOf(selectedProduct.getStock()));
        mpd_price.setText(String.valueOf(selectedProduct.getPrice()));
        mpd_max.setText(String.valueOf(selectedProduct.getMax()));
        mpd_min.setText(String.valueOf(selectedProduct.getMin()));

        assocPTlist = selectedProduct.getAllAssociatedParts();

        mpd_removetable.setItems(assocPTlist);
    }

    /**
         * This is the search button in the corner of the scene.
         * Products/Parts are allowed to be searched by name or ID.
         * @param keyEvent mpd_keyR
     */
    public void mpd_keyR(KeyEvent keyEvent)
    {
        String searchedPart = mpd_search.getText();
        ObservableList<Part> filteredParts = Inventory.lookupPart(searchedPart);
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Sorry! This was not found.");
        warning.setContentText("Oops! Try again. No items were found at the moment.");

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
        mpd_table.setItems(filteredParts);
    }

    /**
         * This is an add button for the associated table.
         * It adds the piece that is associated to the table.
         * @param actionEvent mpd_addCLICK.
     */
    public void mpd_addCLICK(ActionEvent actionEvent)
    {

        Part newProduct;
        newProduct = mpd_table.getSelectionModel().getSelectedItem();

        if (newProduct != null)
            assocPTlist.add(newProduct);

    }

    /**
         * This is a remove button.
         * This button will remove the select item from the associated table.
         * @param actionEvent mpd_removeCLICK.
     */
    public void mpd_removeCLICK(ActionEvent actionEvent)
    {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Sorry! Not found.");
        warning.setContentText("Oops! Try again. Nothing was selected and deleted.");
        Part selectedPart = mpd_removetable.getSelectionModel().getSelectedItem();


        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this from the table?");
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
     * This initializes the modifyPRODUCTController and gives products to the system.
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        System.out.println("modifyPRODUCT-view has been officially initialized!");
        mpd_id.setEditable(false);
        mpd_id.setDisable(true);

        mpd_table.setItems(Inventory.getAllParts());



        mpdt_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        mpdt_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        mpdt_invent.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mpdt_price.setCellValueFactory(new PropertyValueFactory<>("price"));


        mpdr_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        mpdr_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        mpdr_invent.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mpdr_price.setCellValueFactory(new PropertyValueFactory<>("price"));




    }
}

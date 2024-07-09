package view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import view.MainApp;
import view.model.InHouse;
import view.model.INVALID;
import view.model.Inventory;
import view.model.Outsourced;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
    * FUTURE ENHANCEMENT: For future enhancements it would be nice to be able to change the ID. Usually an ID is given as the part is added. Changing that would be more customizable.
     * This is a public class called AddPARTController. It is initialized as well.
     * This is a controller class too. It will be connected it its corresponding fxml file which is addPART-view.fxml
 */

public class AddPARTController implements Initializable
{
    /**
         * The following are all textfields within the addPART-view. Data will be added into these fields.
         * The following includes information such as the id, name, inventory, price, max and min.
         * One of the label changes as well -- either between company name or machine id.
         * This depends on whether the part added is inhouse or outsourced.
     */
    @FXML
    private TextField apt_id;
    @FXML
    private TextField apt_name;
    @FXML
    private TextField apt_invent;
    @FXML
    private TextField apt_price;
    @FXML
    private TextField apt_max;
    @FXML
    private TextField apt_min;
    @FXML
    private TextField apt_changetext;
    /**
     * The following is a save button within the addPART-view. This will save the data that is inserted into the fields.
     */
    @FXML
    private Button apt_save;
    /** This is the label changes. */
    @FXML
    private Label apt_label;
    /**
         * The following are the two radio buttons, one is inhouse and the other is outsourced. Depending on which part;
        * one option will be picked.
     */
    @FXML
    private RadioButton apt_inhouse;
    @FXML
    private RadioButton apt_outsource;


    /**
        * This is the cancel button action.
        * @param actionEvent  apt_cancel
        * @throws IOException from  FXMLlOADER.
     */

    @FXML
    public void apt_cancelCLICK(ActionEvent actionEvent) throws IOException
    {
        MainApp.returnMain(actionEvent);
    }

    /**
        *  This sets the radio button to the inhouse option, which would make the label into 'machine id'.
        * @param actionEvent  apt_inhouseSELECT
     */
    public void apt_inhouseSELECT(ActionEvent actionEvent)
    {
        apt_label.setText("Machine ID");
    }

    /**
         * This sets the radio button to the outsource option, which would make the label into 'company name'.
         * @param actionEvent apt_outsourceSELECT
     */
    public void apt_outsourceSELECT(ActionEvent actionEvent)
    {
        apt_label.setText("Company Name");

    }

    /**
         *The following is the save button action. This saves the details from the data inserted back to the main.
         *The information that is saved is the name, price,inventory(stock), min, max, along with the machineID/company name.
     * RUNTIME ERROR: For a while, there were issues with comparing the values of the inventory. Instead, both statements were placed together with a ||.
         * @param actionEvent apt_saveCLICK
         * @throws IOException FROM FXMLLoader

         */
    public void apt_saveCLICK(ActionEvent actionEvent) throws IOException
    {
        try {
            String ptName = apt_name.getText();
            double ptPrice = Double.parseDouble(apt_price.getText());
            int ptStock = Integer.parseInt(apt_invent.getText());
            int ptMin = Integer.parseInt(apt_min.getText());
            int ptMax = Integer.parseInt(apt_max.getText());
            if (ptMin >= ptMax) {
                throw new INVALID(ptStock, ptMin, ptMax);
            } else if (ptStock < ptMin || ptStock > ptMax) {
                throw new INVALID(ptStock, ptMin, ptMax);
            } else if (apt_inhouse.isSelected()) {
                int partMachineId = Integer.parseInt(apt_changetext.getText());
                Inventory.addPart(new InHouse(MainApp.generatePTsID(), ptName, ptPrice, ptStock, ptMin, ptMax, partMachineId));
            } else if (apt_outsource.isSelected()) {
                String ptCompName = apt_changetext.getText();
                Inventory.addPart(new Outsourced(MainApp.generatePTsID(), ptName, ptPrice, ptStock, ptMin, ptMax, ptCompName));
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
            alert.setTitle("Sorry! Invalid values.");
            alert.setContentText(e.errorMessage());
            alert.showAndWait();
        }



    }
    /**
     * Initializes the controller and sets the inhouse radio button as true.
     * @param url  url
     * @param resourceBundle  resourceBundle.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        System.out.println("addPART-view has been officially initialized!");
        apt_id.setEditable(false);
        apt_id.setDisable(true);

    }
}

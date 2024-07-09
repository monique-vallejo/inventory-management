package view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import view.MainApp;
import view.model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

    /**
     * FUTURE ENHANCEMENT: For a future enhancement, maybe the option to see a preview of the modification.
     * This is a public class called ModifyPartController that is tied with the modifyPART-view.fxml file.

     */
public class ModifyPARTController implements Initializable {
        /**
         * This is the label changes.
         */
        private Part selectedPart;
    @FXML
    private Label mpt_changelabel;
        /**
             * The following are all textfields within the modifyPART-view. Data will be added into these fields.
             * The following includes information such as the id, name, inventory, price, max and min.
             * One of the label changes as well -- either between company name or machine id.
             * This depends on whether the part added is inhouse or outsourced.
         */
    @FXML
    private TextField mpt_id;
    @FXML
    private TextField mpt_name;
    @FXML
    private TextField mpt_invent;
    @FXML
    private TextField mpt_price;
    @FXML
    private TextField mpt_max;
    @FXML
    private TextField mpt_min;
    @FXML
    private TextField mpt_changetext;
        /**
         * The following is a cancel button within the modifyPART-view. This will cancel the data that is inserted into the fields.
         */
    @FXML
    private Button mp_cancelBtn;
        /**
             * The following are the two radio buttons, one is inhouse and the other is outsourced. Depending on which part;
             * one option will be picked.
         */
    @FXML
    private RadioButton mpt_inhouse;
    @FXML
    private RadioButton mpt_outsource;


        /**
             * This is the cancel button action.
             * @param actionEvent  mpt_cancelCLICK
             * @throws IOException from  FXMLlOADER.
         */
    @FXML
    public void mpt_cancelCLICK(ActionEvent actionEvent) throws IOException
    {
        MainApp.returnMain(actionEvent);
    }

        /**
         *The following is the save button action. This saves the details from the data inserted back to the main.
         *The information that is saved is the name, price,inventory(stock), min, max, along with the machineID/company name.
         * RUNTIME ERRROR: Again, the if and else if statements were giving me trouble until I split them up with ||.
         * @param actionEvent mpt_saveCLICK
         * @throws IOException FROM FXMLLoader

         */
    public void mpt_saveCLICK(ActionEvent actionEvent) throws IOException
    {
        try {
            int ptIndex = Integer.parseInt(mpt_id.getText());
            String ptName = mpt_name.getText();
            int ptStock = Integer.parseInt(mpt_invent.getText());
            double ptPrice = Double.parseDouble(mpt_price.getText());
            int ptMax = Integer.parseInt(mpt_max.getText());
            int ptMin = Integer.parseInt(mpt_min.getText());
            if (ptMin >= ptMax)
                throw new INVALID(ptStock, ptMin, ptMax);
            else if (ptStock > ptMax || ptStock < ptMin)
                throw new INVALID(ptStock, ptMin, ptMax);
            else if (mpt_inhouse.isSelected()) {
                int machinedId = Integer.parseInt(mpt_changetext.getText());
                Inventory.updatePart(ptIndex, new InHouse(ptIndex, ptName, ptPrice, ptStock, ptMax, ptMin, machinedId));
            } else if (mpt_outsource.isSelected()) {
                String companyName = mpt_changetext.getText();
                Inventory.updatePart(ptIndex, new Outsourced(ptIndex, ptName, ptPrice, ptStock, ptMax, ptMin, companyName));
            }

            MainApp.returnMain(actionEvent);
        }
        /**
         * The following include error messages in the situation of invalid data.
         */
        catch (NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sorry! That was invalid data!");
            alert.setContentText("Check that all date is correct, and try again.");
            alert.showAndWait();
        }
        catch (INVALID e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sorry! That was invalid values!");
            alert.setContentText(e.errorMessage());
            alert.showAndWait();
        }
    }

        /**
             *  This sets the radio button to the inhouse option, which would make the label into 'machine id'.
             * @param actionEvent  mpt_inhouseSELECT
         */
    public void mpt_inhouseSELECT(ActionEvent actionEvent)
    {
        mpt_changelabel.setText("Machine ID");
    }

        /**
             * This sets the radio button to the outsource option, which would make the label into 'company name'.
             * @param actionEvent mpt_outsourceSELECT
         */
    public void mpt_outsourceSELECT(ActionEvent actionEvent)
    {
        mpt_changelabel.setText("Company Name");

    }

        /**
             * This allows the different parts to be received.
             * @param selectedPart Part
         */
    public void recievePT(Part selectedPart)
    {
        mpt_id.setText(String.valueOf(selectedPart.getId()));
        mpt_name.setText(selectedPart.getName());
        mpt_invent.setText(String.valueOf(selectedPart.getStock()));
        mpt_price.setText(String.valueOf(selectedPart.getPrice()));
        mpt_max.setText(String.valueOf(selectedPart.getMax()));
        mpt_min.setText(String.valueOf(selectedPart.getMin()));
        if (selectedPart instanceof InHouse)
        {
            mpt_inhouse.fire();
            mpt_inhouse.setSelected(true);
            mpt_changetext.setText(String.valueOf(((InHouse)selectedPart).getMachineId()));
        }
        else if (selectedPart instanceof Outsourced)
        {
            mpt_outsource.fire();
            mpt_outsource.setSelected(true);
            mpt_changetext.setText(((Outsourced)selectedPart).getCompanyName());
        }

    }

        /**
         *
         * @param url URL
         * @param resourceBundle ResourceBundle
         */
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            System.out.println("modifyPART-view has been officially initialized!");
            mpt_id.setDisable(true);
            mpt_id.setEditable(false);

        }


}

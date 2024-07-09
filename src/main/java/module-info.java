/**
 * This is a list of what opens and what exports.
 */
module view.applicationc482 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens view to javafx.fxml;
    exports view;
    exports view.controller;
    opens view.controller to javafx.fxml;
    exports view.model;
    opens view.model to javafx.fml;
}
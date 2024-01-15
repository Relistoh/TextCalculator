module com.github.relistoh.text_calculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;


    opens com.github.relistoh.text_calculator to javafx.fxml;
    exports com.github.relistoh.text_calculator;
}
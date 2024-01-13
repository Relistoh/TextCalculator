module com.github.relistoh.text_calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.github.relistoh.text_calculator to javafx.fxml;
    exports com.github.relistoh.text_calculator;
}
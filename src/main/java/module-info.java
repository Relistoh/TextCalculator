module com.github.relistoh.text_calculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.xml;


    opens com.github.relistoh.text_calculator to javafx.fxml;
    exports com.github.relistoh.text_calculator;
    exports com.github.relistoh.text_calculator.expression;
    opens com.github.relistoh.text_calculator.expression to javafx.fxml;
    exports com.github.relistoh.text_calculator.ui;
    opens com.github.relistoh.text_calculator.ui to javafx.fxml;
    exports com.github.relistoh.text_calculator.additional_classes;
    opens com.github.relistoh.text_calculator.additional_classes to javafx.fxml;
    exports com.github.relistoh.text_calculator.archivation_encryption;
    opens com.github.relistoh.text_calculator.archivation_encryption to javafx.fxml;
    exports com.github.relistoh.text_calculator.reader_writer;
    opens com.github.relistoh.text_calculator.reader_writer to javafx.fxml;
}
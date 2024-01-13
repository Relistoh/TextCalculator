package com.github.relistoh.text_calculator;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class UIController {
    @FXML
    private ChoiceBox<String> choiceBoxInput1;

    @FXML
    private ChoiceBox<String> choiceBoxInput2;

    @FXML
    private ChoiceBox<String> choiceBoxOutput1;

    @FXML
    private ChoiceBox<String> choiceBoxOutput2;

    @FXML
    private void initialize()
    {
        ObservableList<String> listOfTypes = FXCollections.observableArrayList("plane text", "json", "xml");
        ObservableList<String> listOfActions = FXCollections.observableArrayList("archived", "encrypted", "archived, then encrypted", "encrypted, then archived");
        choiceBoxInput1.setItems(listOfTypes);
        choiceBoxInput2.setItems(listOfActions);
        choiceBoxOutput1.setItems(listOfTypes);
        choiceBoxOutput2.setItems(listOfActions);
    }
}
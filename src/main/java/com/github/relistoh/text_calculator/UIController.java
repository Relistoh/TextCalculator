package com.github.relistoh.text_calculator;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class UIController {
    InputFileInfo inputFileInfo = new InputFileInfo();
    SwitchManager switchManager = new SwitchManager();

    @FXML
    private ChoiceBox<String> choiceBoxInput1;

    @FXML
    private ChoiceBox<String> choiceBoxInput2;

    @FXML
    private ChoiceBox<String> choiceBoxOutput1;

    @FXML
    private ChoiceBox<String> choiceBoxOutput2;

    @FXML
    private Button showOutput;

    @FXML
    private TextField textFieldInput;

    @FXML
    private TextArea textAreaInput, textAreaOutput;

    @FXML
    private void initialize()
    {
        ObservableList<String> listOfTypes = FXCollections.observableArrayList("plain text", "json", "xml");
        ObservableList<String> listOfActions = FXCollections.observableArrayList("nothing", "archived", "encrypted", "archived, then encrypted", "encrypted, then archived");
        choiceBoxInput1.setItems(listOfTypes);
        choiceBoxInput2.setItems(listOfActions);
        choiceBoxOutput1.setItems(listOfTypes);
        choiceBoxOutput2.setItems(listOfActions);
    }

    @FXML
    public void showOutputAction() throws IOException {
        inputFileInfo.fileName = textFieldInput.getText();
        inputFileInfo.fileExtencion = choiceBoxInput1.getValue();
        inputFileInfo.fileType = choiceBoxInput2.getValue();

        switchManager.inputSwitch(inputFileInfo);
        textAreaInput.setText(switchManager.data);
        textAreaOutput.setText(switchManager.calculatedData);


        System.out.println(inputFileInfo.toString());
    }
}
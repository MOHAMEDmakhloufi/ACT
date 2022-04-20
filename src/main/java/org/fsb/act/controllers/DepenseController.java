package org.fsb.act.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class DepenseController {
	@FXML
    private RadioButton radioFamille;

    @FXML
    private RadioButton radioPersonne;

    @FXML
    private RadioButton radioAutre;

    @FXML
    private GridPane gridPersonne;

    @FXML
    private GridPane gridFamille;

    @FXML
    private TableView<?> tableDepenses;

    @FXML
    private TableColumn<?, ?> colDirigeant;

    @FXML
    private TableColumn<?, ?> colMontant;
    
    @FXML
    private TableColumn<?, ?> colDerniereMiseJour;

    @FXML
    private TableColumn<?, ?> colDestination;

    @FXML
    private Label labelMontant;
    
    @FXML
    private Label labelNecessiteuse;

    @FXML
    private TextField textFieldMontant;
    
    @FXML
    private TextField textFieldPere1;
    
    @FXML
    private TextField textFieldDescription;

    @FXML
    private TextField textFieldPere;

    

    @FXML
    void handleRadioPersonne(ActionEvent event) {

    }

    @FXML
    void handleRadioFamille(ActionEvent event) {

    }

    @FXML
    void handleRadioAutre(ActionEvent event) {

    }

    @FXML
    void clear(ActionEvent event) {

    }

    @FXML
    void supprimerDepense(ActionEvent event) {

    }

    @FXML
    void updateDepense(ActionEvent event) {

    }

    @FXML
    void addDepense(ActionEvent event) {

    }

    @FXML
    void searchDepenses(ActionEvent event) {

    }
}

package org.fsb.act.controllers;

import org.fsb.act.entities.Membre;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class CompteAssociationControler {
	
	@FXML
    private TableView<Membre> tableComptes;
    
    @FXML
    private TableColumn<?, ?> colUsename;

    @FXML
    private TableColumn<?, ?> colPassword;
    
    @FXML
    private TableColumn<?, ?> colActivation;
    @FXML
    private TextField username;
    
    @FXML
    private PasswordField pass;
    
    @FXML
    private PasswordField pass2;
    
    @FXML
    private ToggleButton desactiver;
    
    @FXML
    private Button reset;
    
    @FXML
    private Button update;
    
    @FXML
    private Button creer;
    
    @FXML
    private TextField search;
    
    
    
}

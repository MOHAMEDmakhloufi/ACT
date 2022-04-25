package org.fsb.act.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.fsb.act.App;
import org.fsb.act.entities.Association;
import org.fsb.act.services.ModifierAssociationService;
import org.fsb.act.validation.InputValidation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CreerAssociationController implements Initializable{
	@FXML
    private PasswordField textFieldPassword;

    @FXML
    private PasswordField textFieldConfirmer;
	    
    @FXML
    private TextField textFieldTelephone;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private TextField textFieldTitre;
    
    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldSiege;
    
    @FXML
    private Label labelConfirmer;
    
    @FXML
    private Label labelTelephone;
    
    @FXML
    private Label labelSiege;
    
    @FXML
    private Label labelEmail;

    @FXML
    private Label labelUsername;
    
    @FXML
    private Label labelPassword;
    
    @FXML
    private Label labelTitre;
    
    @FXML
    private ImageView ImageLogo;
  
    @FXML
    private Button btnBrowser;
    
    @FXML
    private AnchorPane anchorpane;
    
    private FileChooser fileChooser;
    private File file;
    private Stage stage;
    private Association association= new Association();
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	
    	fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All files", "* *"),
				new FileChooser.ExtensionFilter("images", "*png", "*jpg", "*gif"),
				new FileChooser.ExtensionFilter("Text File", "*txt")
		);
	}
    
    /**
     * if you are clicked open browser and select image 
     * @param event
     */
    @FXML
    void handleBrowser(ActionEvent event) {
    	stage = (Stage)anchorpane.getScene().getWindow();
    	file= fileChooser.showOpenDialog(stage);
    	
    	if(file !=null) {
    		
    		Image image= new Image(file.getAbsoluteFile().toURI().toString(), ImageLogo.getFitWidth(), ImageLogo.getFitHeight(), true, true);
    		ImageLogo.setImage(image);
    		ImageLogo.setPreserveRatio(true);
    	}
    }
    /**
     * if you are clicked creer association 
     * @param event
     */
    @FXML
    void creerAssociation(ActionEvent event) {
    	if(validationChamps()) {
    		
    		association.setTitre(textFieldTitre.getText());
    		association.setSiege(textFieldSiege.getText());
    		association.setEmail(textFieldEmail.getText());
    		association.setTelephone(textFieldTelephone.getText());
    		association.setUsername(textFieldUsername.getText());
    		association.setPassword(textFieldPassword.getText());
    		FileInputStream fis;
			try {
				if(file != null) {
					fis = new FileInputStream(file);
					association.setLogo(fis.readAllBytes());
				}
				
				
			}  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		int i= ModifierAssociationService.creerAssocition(association);
    		if(i==1) {
    			InputValidation.showAlertInfoWithoutHeaderText("Record create successfully!");
    			try {
					App.setRoot("sideBarAssociation", 800, 500);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}else
    			InputValidation.showAlertErrorWithoutHeaderText("Record create Failure!");
    	}
    }
    /**
     * test the textFields One by one
     * @return
     */
    public boolean validationChamps() {
    	boolean test = true;
    	labelTitre.setStyle("-fx-text-fill:#3F2B63");
    	labelSiege.setStyle("-fx-text-fill:#3F2B63");
    	labelEmail.setStyle("-fx-text-fill:#3F2B63");
    	labelTelephone.setStyle("-fx-text-fill:#3F2B63");
    	labelUsername.setStyle("-fx-text-fill:#3F2B63");
    	labelPassword.setStyle("-fx-text-fill:#3F2B63");
    	labelConfirmer.setStyle("-fx-text-fill:#3F2B63");
    	btnBrowser.setStyle("-fx-text-fill:#3F2B63");
    	
    	if(!InputValidation.isFieldNotEmpty(textFieldTitre) ) {
    		labelTitre.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(textFieldSiege)) {
    		labelSiege.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(textFieldEmail) || !InputValidation.isFieldEmail(textFieldEmail)) {
    		labelEmail.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(textFieldTelephone)) {
    		labelTelephone.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(textFieldUsername)) {
    		labelUsername.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(textFieldPassword)) {
    		labelPassword.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(textFieldConfirmer) | !InputValidation.areFieldsIdendical(textFieldPassword, textFieldConfirmer)) {
    		labelConfirmer.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(ImageLogo.getImage() == null)
    		btnBrowser.setStyle("-fx-text-fill:red");
    	return test;
    }
}

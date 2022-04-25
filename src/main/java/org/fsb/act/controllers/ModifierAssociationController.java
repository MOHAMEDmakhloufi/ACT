package org.fsb.act.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class ModifierAssociationController implements Initializable{

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
    private Association association;
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	loadDataAssociation();
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
     * if you are clicked update association 
     * @param event
     */
    @FXML
    void updateAssociation(ActionEvent event) {
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
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		int i= ModifierAssociationService.updateAssocition(association);
    		if(i==1) {
    			InputValidation.showAlertInfoWithoutHeaderText("Record updated successfully!");
    		}else
    			InputValidation.showAlertErrorWithoutHeaderText("Record Update Failure!");
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
    /**
     * initialement load data from service
     */
	public void loadDataAssociation() {
		association = ModifierAssociationService.getDataAssociation();
		textFieldTitre.setText(association.getTitre());
		textFieldSiege.setText(association.getSiege());
		textFieldEmail.setText(association.getEmail());
		textFieldTelephone.setText(association.getTelephone());
		textFieldUsername.setText(association.getUsername());
		textFieldPassword.setText(association.getPassword());
		
		try {
			if(association.getLogo() != null) {
				FileOutputStream fos = new FileOutputStream(".//src//main//resources//org//fsb//act//images//logoModifier.jpg");
				fos.write(association.getLogo());
				
				Image image = new Image("file:.//src//main//resources//org//fsb//act//images//logoModifier.jpg", ImageLogo.getFitWidth(), ImageLogo.getFitHeight(), true, true);
				ImageLogo.setImage(image);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

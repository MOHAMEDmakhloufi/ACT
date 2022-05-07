package org.fsb.act.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.fsb.act.App;
import org.fsb.act.entities.Association;
import org.fsb.act.entities.Don;
import org.fsb.act.entities.Donneur;
import org.fsb.act.entities.Membre;
import org.fsb.act.models.Vide;
import org.fsb.act.services.DonService;
import org.fsb.act.services.ModifierAssociationService;
import org.fsb.act.services.ServiceMembre;
import org.fsb.act.validation.InputValidation;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class DonController implements Initializable{
	@FXML
    private ComboBox<String> comBoBox;

    @FXML
    private Label labelTypeDon;

    @FXML
    private TableColumn<?, ?> colDirigeant;

    @FXML
    private VBox vBoxArgent;

    @FXML
    private RadioButton radioArgent;

    @FXML
    private Label labelMontant;

    @FXML
    private VBox vBoxMateriel;

    @FXML
    private Label labelPiece;

    @FXML
    private Label labelNom;

    @FXML
    private TableColumn<Don, String> colNpiece;

    @FXML
    private TextField textFieldPrenom;

    @FXML
    private TableColumn<Don, String> colNom;

    @FXML
    private TextField textFieldPiece;

    @FXML
    private TextField textFieldSearch;

    @FXML
    private TextField textFieldNom;

    @FXML
    private RadioButton radioObjet;

    @FXML
    private TableColumn<Don, String> colPrenom;

    @FXML
    private TableColumn<?, ?> colMontant;

    @FXML
    private TextField textFieldMontant;

    @FXML
    private TableView<Don> tableView;

    @FXML
    private Label labelPrenom;

    @FXML
    private TableColumn<?, ?> colDerniereMiseJour;

    @FXML
    private TableColumn<Don, String> colNationalite;

    @FXML
    private Label labelObjetMateriel;

    @FXML
    private TextField textFieldObjet;

    @FXML
    private TableColumn<?, ?> colTypeDon;

    @FXML
    private TableColumn<?, ?> colObjet;
    
    @FXML
    private TableColumn<?, ?> colDonneur;

    @FXML
    private Label labelNationalite;
    
    //map pour stocker parametres de fichier recu
  	private Map<String, Object> parametres= new HashMap<>();
  	
    private Don donSelected;
    private ObservableList<Don> data= FXCollections.observableArrayList();
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	//Start get data association and add in map parametres for print recu
		//exemple data association
    	Association association= ModifierAssociationService.getDataAssociation();
		parametres.put("titre", association.getTitre());
		parametres.put("email", association.getEmail() );
		parametres.put("telephone", association.getTelephone());
		parametres.put("siege", association.getSiege());
		//fin exemple
		parametres.put("nomDirigeant", App.dirigeant);
		File logo= new File(".//src//main//resources//org//fsb//act//images//logo.jpg");
		parametres.put("logo", logo.getPath());
		String date= LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		parametres.put("date", date);
		//End 
		
		initialiserComBoxTypePiece();
		setCellTable();
		getAll();
	}
    void getAll() {
    	data.clear();
    	data.addAll(DonService.getAll());
    	tableView.setItems(data);
    }
    @FXML
    void refresh() {
		//etape1
		clear(null);
    	//etape2
    	getAll();
    }
    @FXML
    void handleRadioArgent(ActionEvent event) {
    	//select radio
    	radioArgent.setSelected(true);
    	radioObjet.setSelected(false);
    	
    	//select correct node
    	vBoxArgent.setVisible(true);
    	vBoxMateriel.setVisible(false);
    	
    	//clear
    	textFieldMontant.clear();
    	textFieldObjet.clear();
    }

    @FXML
    void handleRadioObjet(ActionEvent event) {
    	//select radio
    	radioArgent.setSelected(false);
    	radioObjet.setSelected(true);
    	
    	//select correct node
    	vBoxArgent.setVisible(false);
    	vBoxMateriel.setVisible(true);
    	
    	//clear
    	textFieldMontant.clear();
    	textFieldObjet.clear();
    }

    @FXML
    void handlePrintRecu(ActionEvent event) {
    	if(donSelected !=null) {
			parametres.put("prenom", donSelected.getDonneur().getPrenom());
			parametres.put("nom", donSelected.getDonneur().getNom());
			parametres.put("montant", donSelected.getMontant()+"DT");
			parametres.put("objetMateriel", donSelected.getObjetMateriel()+"");
			 printRecu("recuDon", parametres);
		}
    }
    void printRecu(String fileName, Map<String, Object> parametres) {
		 
		
		 try {
			 String  sourceFile= App.class.getResource(fileName + ".jrxml").getPath();
			 JasperReport jr = JasperCompileManager.compileReport(sourceFile);
			 	
			 List<Vide> plist = new ArrayList<>();
			 plist.add(new Vide(""));
			 JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(plist);
			 JasperPrint jp = JasperFillManager.fillReport(jr, parametres, jcs);
			 
			 JasperViewer viewer= new JasperViewer(jp, false);
			 viewer.setVisible(true);
				
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
    @FXML
    void clear(ActionEvent event) {
    	initialColorLabel();
    	textFieldPrenom.clear();
    	textFieldNom.clear();
    	textFieldPiece.clear();
    	comBoBox.getSelectionModel().select(0);
    	handleRadioArgent(null);
    }

    @FXML
    void delete(ActionEvent event) {
    	if(donSelected !=null) {
    		if(InputValidation.showConfirmation("Are you sure to Delete this don ?")
    				.get()== ButtonType.OK) {
    			int i= DonService.supprimerDon(donSelected.getId(), donSelected.getDonneur().getId());
    			
    	    	if(i==1) {
	    			InputValidation.showAlertInfoWithoutHeaderText("Delete Successfully!");
	    			data.remove(donSelected);
	    			clear(null);
    	    	}else
        			InputValidation.showAlertErrorWithoutHeaderText("Delete Failure :(");
    		}
    	}else 
    		InputValidation.showAlertErrorWithoutHeaderText("IMPOSSIBLE");
    }

    @FXML
    void update(ActionEvent event) {
    	if(donSelected != null && validationChamps()) {
    		
    			Don don= createDonFromIhm();
				don.setId(donSelected.getId());
				don.getDonneur().setId(donSelected.getDonneur().getId());
				int i=DonService.updateDon(don);
				if(i==1) {
					InputValidation.showAlertInfoWithoutHeaderText("Record updated successfully!");
					clear(null);
					data.set(data.indexOf(donSelected), don);
					donSelected=don;
					handlePrintRecu(null);
				}else
					InputValidation.showAlertErrorWithoutHeaderText("Record Update Failure!");
					
	
    	}
    }

    @FXML
    void add(ActionEvent event) {
    	
		if(validationChamps()) {
			 
			Don don= createDonFromIhm();
			//call service
			Don newDon=DonService.ajouterDon(don);
			if(newDon!=null) {
				InputValidation.showAlertInfoWithoutHeaderText("Addition has been registered Successfully!");
				
				clear(null);
				data.add(0, newDon);
				donSelected=don;
				handlePrintRecu(null);
			}
			else
				InputValidation.showAlertErrorWithoutHeaderText("Registration Failed!");
		}else
			InputValidation.showAlertErrorWithoutHeaderText("ERREUR!");
		
    }

    @FXML
    void search() {
    	String text= textFieldSearch.getText();
        
    	if(!InputValidation.isFieldNotEmpty(textFieldSearch)) {
    		
    		tableView.setItems(data);
    	}else {

    		tableView.setItems(DonService.searchDons(data, text));
    		
    	}
    }
    Don createDonFromIhm() {
    	Don don= new Don();
    	//create donneur
    	Donneur donneur = new Donneur();
    	donneur.setPrenom(textFieldPrenom.getText());
    	donneur.setNom(textFieldNom.getText());
    	donneur.setPieceIdentite(Long.parseLong(textFieldPiece.getText()));
    	donneur.setNationalite(comBoBox.getSelectionModel().getSelectedItem());
    	//fin donneur
    	don.setDonneur(donneur);
    	if(radioArgent.isSelected()) {
    		don.setTypeDon(radioArgent.getText());
    		don.setMontant(Double.parseDouble(textFieldMontant.getText()));
    	}else {
    		don.setTypeDon(radioObjet.getText());
    		don.setObjetMateriel(textFieldObjet.getText());
    	}
    	don.setDirigeant(App.dirigeant);
    	
    	return don;
    }
    @FXML
    void displayDon(MouseEvent event) {
    	if(event.getClickCount() > 1) {
    		if (tableView.getSelectionModel().getSelectedItem() != null) {
				
				clear(null);
				donSelected= tableView.getSelectionModel().getSelectedItem();
				textFieldPrenom.setText(donSelected.getDonneur().getPrenom());
				textFieldNom.setText(donSelected.getDonneur().getNom());
				textFieldPiece.setText(""+donSelected.getDonneur().getPieceIdentite());
				int index= comBoBox.getItems().indexOf(donSelected.getDonneur().getNationalite());
				comBoBox.getSelectionModel().select(0);
				if(donSelected.getTypeDon().equals("Argent Liquide")) {
					handleRadioArgent(null);
					textFieldMontant.setText(""+donSelected.getMontant());
				}else {
					handleRadioObjet(null);
					textFieldObjet.setText(donSelected.getObjetMateriel());
				}
			}
    	}
    }
    /**
     * test the textFields One by one
     * call initialColorLabel()
     * @return
     */
    public boolean validationChamps() {
    	boolean test = true;
    	
    	initialColorLabel();
    	
    	if(!InputValidation.isFieldNotEmpty(textFieldPrenom) ) {
    		labelPrenom.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(textFieldNom)) {
    		labelNom.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(textFieldPiece)|| !InputValidation.isFieldNumber(textFieldPiece)) {
    		labelPiece.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	
    	if((!InputValidation.isFieldNotEmpty(textFieldMontant) || !InputValidation.isFieldNumber(textFieldMontant))&& radioArgent.isSelected()) {
    		labelMontant.setStyle("-fx-text-fill:red");
    		test=false;
    	}
    	if(!InputValidation.isFieldNotEmpty(textFieldObjet) && radioObjet.isSelected()) {
    		labelObjetMateriel.setStyle("-fx-text-fill:red");
    		test=false;
    		
    	}
    	
    	return test;
    }
    /**
     * initial the color label 
     */
    public void initialColorLabel() {
    	labelPrenom.setStyle("-fx-text-fill:#3F2B63");
    	labelNom.setStyle("-fx-text-fill:#3F2B63");
    	labelPiece.setStyle("-fx-text-fill:#3F2B63");
    	labelNationalite.setStyle("-fx-text-fill:#3F2B63");
    	labelTypeDon.setStyle("-fx-text-fill:#3F2B63");
    	labelMontant.setStyle("-fx-text-fill:#3F2B63");
    	
    	
    }
    private void initialiserComBoxTypePiece(){
    	ObservableList<String>  list= FXCollections.observableArrayList(Arrays.asList("Tunisie", "France","italie"));
    	comBoBox.setItems(list);
    	comBoBox.getSelectionModel().select(0);
    }

    /**
     * correspondence between a table column and a attribute
     */
    private void setCellTable() {
    	colDonneur.setCellValueFactory(new PropertyValueFactory<>("donneur"));
    	colNom.setCellValueFactory(new Callback<CellDataFeatures<Don, String>, ObservableValue<String>>() {
    	     public ObservableValue<String> call(CellDataFeatures<Don, String> d) {
    	         // p.getValue() returns the Person instance for a particular TableView row
    	         return new SimpleStringProperty(d.getValue().getDonneur().getNom());
    	     }
    	  });
    	colPrenom.setCellValueFactory(new Callback<CellDataFeatures<Don, String>, ObservableValue<String>>() {
   	     public ObservableValue<String> call(CellDataFeatures<Don, String> d) {
   	         // p.getValue() returns the Person instance for a particular TableView row
   	         return new SimpleStringProperty(d.getValue().getDonneur().getPrenom());
   	     }
   	  });
    	colNpiece.setCellValueFactory(new Callback<CellDataFeatures<Don, String>, ObservableValue<String>>() {
   	     public ObservableValue<String> call(CellDataFeatures<Don, String> d) {
   	         // p.getValue() returns the Person instance for a particular TableView row
   	         return new SimpleStringProperty(""+d.getValue().getDonneur().getPieceIdentite());
   	     }
   	  });
    	colNationalite.setCellValueFactory(new Callback<CellDataFeatures<Don, String>, ObservableValue<String>>() {
      	     public ObservableValue<String> call(CellDataFeatures<Don, String> d) {
      	         // p.getValue() returns the Person instance for a particular TableView row
      	         return new SimpleStringProperty(d.getValue().getDonneur().getNationalite());
      	     }
      	  });

    	colMontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
    	colTypeDon.setCellValueFactory(new PropertyValueFactory<>("typeDon"));
    	colObjet.setCellValueFactory(new PropertyValueFactory<>("objetMateriel"));
    	colDerniereMiseJour.setCellValueFactory(new PropertyValueFactory<>("leDerniermisejour"));
    	colDirigeant.setCellValueFactory(new PropertyValueFactory<>("dirigeant"));
    }
}

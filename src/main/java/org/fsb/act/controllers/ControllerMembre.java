package org.fsb.act.controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.fsb.act.entities.Membre;
import org.fsb.act.services.ServiceMembre;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ControllerMembre implements Initializable{
	
    @FXML
    private TableView<Membre> tableMembres;
    
    @FXML
    private TableColumn<?, ?> colProfession;

    @FXML
    private TableColumn<?, ?> colIdentite;
    
    @FXML
    private TableColumn<?, ?> colPrenom;

    @FXML
    private TableColumn<?, ?> colTelephone;
    
    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colNom;

    @FXML
    private TableColumn<?, ?> colD_N;
    
    @FXML
    private Button btnChoisirCopie;
    
    @FXML
    private Button btnUpdate;
    
    @FXML
    private Button btnUpImage;
    
    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSupprimer;
    
    @FXML
    private Button btnPrintMembres;
    
    @FXML
    private TextField searchField;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField email;
    
    @FXML
    private TextField profession;

    @FXML
    private TextField pieceIdentiteNum;

    @FXML
    private TextField telephone;
    
    @FXML
    private DatePicker dateNaissance;

    @FXML
    private ComboBox<String> typePiece;

    @FXML
    private ImageView image;
    
    //Start my own attributes
	private ObservableList<Membre>  data= FXCollections.observableArrayList();
    //End my own attributes

    

    
    /**
     * If you click btn upload image, 
     * we open the browser to upload the image member
     * @param event
     */
    @FXML
    void uploadImage(ActionEvent event) {

    }
    
    /**
     * If you click btn ChoisirCopie ,
     *  we open the browser to upload the copy
     * @param event
     */
    @FXML
    void choisirCopie(ActionEvent event) {

    }
    /**
     * If you click btn reset,
     * empty all fields
     * @param event
     */
    @FXML
    void clear(ActionEvent event) {

    }
    
    /**
     * If you click btn Supprimer
     * we start to delete the member
     * @param event
     */
    @FXML
    void supprimerMembre(ActionEvent event) {

    }
    
    /**
     * If you click btn Update
     * we start to update the member
     * @param event
     */
    @FXML
    void updateMembre(ActionEvent event) {

    }
    /**
     * If you click btn Ajouter
     * we start to add the member
     * @param event
     */
    @FXML
    void ajouterMembre(ActionEvent event) {

    }
    
    /**
     * If you write in the search field  
     * then we find for matches members
     */
    @FXML
    void searchMembres() {

    }
    
    /**
     * if you click on a table row
     * then display the row in these fields
     */
    @FXML
    void displayMembre() {

    }
    /**
     * if you click btn printMembers
     * then print a membership document
     * @param event
     */
    @FXML
    void printListMembres(ActionEvent event) {

    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	
    	//etape1
    	initialiserComBoxTypePiece();
    	//etape2
    	setCellTable();
    	//etape3
    	tableMembres.setItems(data);
    	//etape4
    	getAll();
		
	}
    /**
     * correspondence between a table column and a attribute
     */
    private void setCellTable() {
    	colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    	colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    	colD_N.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
    	colProfession.setCellValueFactory(new PropertyValueFactory<>("profession"));
    	colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    	colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
    	colIdentite.setCellValueFactory(new PropertyValueFactory<>("numeroIdentite"));
    }
    /**
     * 
     */
    private void initialiserComBoxTypePiece(){
    	ObservableList<String>  list= FXCollections.observableArrayList(Arrays.asList("C.I.N", "carte de sejour"));
    	typePiece.setItems(list);
    	typePiece.getSelectionModel().select(0);
    }
    /**
     * this function calls serviceMembre to receive all members
     */
	private void getAll() {
		//data.addAll(ServiceMembre.getAll());
	}
}

package org.fsb.act.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.fsb.act.App;
import org.fsb.act.entities.FamilleNecessiteuse;
import org.fsb.act.entities.NotificationEvenement;
import org.fsb.act.services.FamilleNecessiteuseService;
import org.fsb.act.services.NotificationEventService;
import org.fsb.act.validation.InputValidation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class NotificationEventController implements Initializable {
	 @FXML
	 private VBox vBox;
	 
	 private Map<AnchorPane, NotificationEvenement> map= new HashMap<>();
	 
	 public static List<NotificationEventController> listControllersNotif= new ArrayList<>();
	 /*
	  * the destructor
	  */
	  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listControllersNotif.add(this); 
		getAll();
		
	}
	 /**
     * this function calls serviceMembre to receive all notifications
     */
	 public  void getAll() {
		 map.clear();
		 vBox.getChildren().clear();
		List<NotificationEvenement> notifications= NotificationEventService.getAllFromService();
		notifications.forEach(ne -> {
			AnchorPane ap;
			if(ne.getValidation() == null)
				ap= createNotification(ne, false, true, true);
			else if(ne.getValidation().equals("accepter"))
				ap= createNotification(ne, true, false, true);
			else 
				ap= createNotification(ne, true, true, false);
			
				
			map.put(ap, ne);
			vBox.getChildren().add(ap);
			
		});
	}
	 
	 /**
	  * if you clicked accepter then update validation
	  */
	 @FXML
	 public void handleBtn(ActionEvent event) {
		 
		 Button btn=(Button) event.getSource();
		 AnchorPane anchorPane =(AnchorPane) btn.getParent();
		  
		 NotificationEvenement ne=map.get((AnchorPane)((GridPane)anchorPane.getParent()).getParent());
		 
		 if(btn.getText().equals("ACCEPTER"))
			 ne.setValidation("accepter");
		 else
			 ne.setValidation("refuser");
		 ne.setDirigeant(App.dirigeant);
		 int i= NotificationEventService.updateValidation(ne);
		 if(i==1) {
				InputValidation.showAlertInfoWithoutHeaderText("Record updated successfully!");
				anchorPane.setDisable(true);
				 if((Button)anchorPane.getChildren().get(0) != btn)
					 ((Button)anchorPane.getChildren().get(0)).setVisible(false);
				 else
					 ((Button)anchorPane.getChildren().get(1)).setVisible(false);
			}else
				InputValidation.showAlertErrorWithoutHeaderText("Record Update Failure!");
	 }
	 /**
	  * this methode create new anchorPane complet
	  * @return
	  */
	 public AnchorPane createNotification(NotificationEvenement ne, Boolean anchorPaneBtnIsDisable, Boolean btnRefuserIsVisible, Boolean btnAccepterIsVisible) {
		 AnchorPane anchorPane= new AnchorPane();
		 anchorPane.getStyleClass().add("anchor-pane");
		//create object gridPane
		GridPane gridPane= new GridPane();
		AnchorPane.setTopAnchor(gridPane, 0.0);
	    AnchorPane.setLeftAnchor(gridPane, 0.0);
	    AnchorPane.setRightAnchor(gridPane, 0.0);
	    AnchorPane.setBottomAnchor(gridPane, 0.0);
		//create three column with Percentage Sizing
		ColumnConstraints column1 = new ColumnConstraints();
	    column1.setPercentWidth(62);
	    ColumnConstraints column2 = new ColumnConstraints();
	    column2.setPercentWidth(38);
	    //add this columns a gridPane
	    gridPane.getColumnConstraints().addAll(column1, column2);
	    RowConstraints row1 = new RowConstraints();
	    row1.setPercentHeight(25);
	    RowConstraints row2 = new RowConstraints();
	    row2.setPercentHeight(25);
	    RowConstraints row3 = new RowConstraints();
	    row3.setPercentHeight(25);
	    RowConstraints row4 = new RowConstraints();
	    row4.setPercentHeight(25);
	    gridPane.getRowConstraints().addAll(row1, row2, row3, row4);
	    //add the grid in the anchorpane
	    anchorPane.getChildren().add(gridPane);
	    
	    //label1
	    Label label1= new Label("DEMANDE D'APPROBTION D'UN MONTANT :");
	    //label2
	    Label label2= new Label("dirigeant qui demande : "+ne.getEvenement().getDirigeant());
	    label2.getStyleClass().add("sousLabel");
	    //label3
	    Label label3= new Label("Budget en DT  : " +ne.getEvenement().getBudget()+" DT");
	    label3.getStyleClass().add("sousLabel");
	    //label4
	    Label label4= new Label("Raison : "+ne.getEvenement().getTitre() +", Le "+ne.getEvenement().getDateDebut());
	    label4.getStyleClass().add("sousLabel");
	    
	    gridPane.add(label1, 0, 0);
	    gridPane.add(label2, 0, 1);
	    gridPane.add(label3, 0, 2);
	    gridPane.add(label4, 0, 3);
	    
	    //anchorpane buttons
	    AnchorPane anchorPaneBtns= new AnchorPane();
	    anchorPaneBtns.setDisable(anchorPaneBtnIsDisable);
	    gridPane.add(anchorPaneBtns, 1, 3);
	    //button refusser
	    Button btnRefuser= new Button("REFUSER");
	    btnRefuser.setVisible(btnRefuserIsVisible);
	    btnRefuser.setOnAction(e -> handleBtn(e));
	    btnRefuser.getStyleClass().add("refuser");
	    AnchorPane.setTopAnchor(btnRefuser, 5.0);
	    AnchorPane.setLeftAnchor(btnRefuser, 15.0);
	    //button accepter
	    Button btnAccepter= new Button("ACCEPTER");
	    btnAccepter.setVisible(btnAccepterIsVisible);
	    btnAccepter.setOnAction(e -> handleBtn(e));
	    btnAccepter.getStyleClass().add("accepter");
	    AnchorPane.setTopAnchor(btnAccepter, 5.0);
	    AnchorPane.setRightAnchor(btnAccepter, 25.0);
	    
	    anchorPaneBtns.getChildren().addAll(btnRefuser, btnAccepter);
	    
	    //anchorPane date notif
	    AnchorPane anchorPaneDate= new AnchorPane();
	    gridPane.add(anchorPaneDate, 1, 0);
	    //label1
	    Label labelDate= new Label(ne.getDate());
	    anchorPaneDate.getChildren().add(labelDate);
	    AnchorPane.setRightAnchor(labelDate, 25.0);
		 return anchorPane;
	 }

	
}

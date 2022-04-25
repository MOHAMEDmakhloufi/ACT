package org.fsb.act.validation;



import java.text.SimpleDateFormat;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class InputValidation {
	
	public static boolean isFieldNotEmpty(TextField textField) {
		return (textField.getText().length() != 0 || !textField.getText().isEmpty() );
	}
	public static boolean isFieldNumber(TextField textField) {
		return (textField.getText().matches("([0-9]+(\\.[0-9]+)?)?"));
	}
	public static boolean areFieldsIdendical(TextField textField1, TextField textField2) {
		return textField1.getText().equals(textField2.getText());
	}
	public static boolean isFieldEmail(TextField textField) {
		return (textField.getText().matches("^[A-Za-z0-9]+\\.[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z]{2,6}$"));
	}
	/**
	 * Show a INFORMAITON Alert with header Text
	 * @param msg
	 */
	public static void showAlertInfoWithoutHeaderText(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText(msg);
		
		alert.showAndWait();
	}
	/**
	 * Show a ERROR Alert with header Text
	 * @param msg
	 */
	public static void showAlertErrorWithoutHeaderText(String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText(msg);

		alert.showAndWait();
	}
	
	public static Optional<ButtonType> showConfirmation(String msg){
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle("CONFIRMATION");
	    alert.setHeaderText(msg);
	    return alert.showAndWait();
	}
}

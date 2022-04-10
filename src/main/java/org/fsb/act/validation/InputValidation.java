package org.fsb.act.validation;



import java.text.SimpleDateFormat;

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
	
}

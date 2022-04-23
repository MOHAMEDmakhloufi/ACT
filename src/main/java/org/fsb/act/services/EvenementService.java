package org.fsb.act.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fsb.act.dao.EvenementDao;
import org.fsb.act.entities.Evenement;
import org.fsb.act.entities.Membre;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class EvenementService {

	public static List<Evenement> getAll() {
		Evenement evenement = new Evenement(1, "moh", "bizerte", "2022-12-07", "2022-12-25", "15:30", "17:45", 123.98, "en attente", "med.mak123");
		Evenement evenement1 = new Evenement(2, "mo", "mestir", "2022-12-11", "2022-12-26", "12:30", "15:45", 100.98, "en attente", "mariam");
		Evenement evenement2 = new Evenement(3, "m", "bizerte", "2022-12-07", "2022-12-25", "15:30", "17:45", 123.98, "en attente", "med.mak123");
		
		List<Evenement> liste = new ArrayList<>(Arrays.asList(evenement,evenement1,evenement2));
		
		
		return liste;
	}

	public static int supprimerEvent(long id) {
		// TODO Auto-generated method stub
		return 1;
	}

	public static long ajouterEvent(Evenement evenement) {
		// TODO Auto-generated method stub
		return 1;
	}

	public static int modifierEvent(Evenement e) {
		// TODO Auto-generated method stub
		return 1;
	}
	public static Evenement getOneById(long id) {
		return EvenementDao.getOneById(id);
	}
	public static ObservableList<Evenement> searchEvents(ObservableList<Evenement> data, String text) {
		ObservableList<Evenement>  localData= FXCollections.observableArrayList();
		for(Evenement e: data) {
			if(e.getTitre().matches(text+".*") ||
		       e.getLieu().matches(text+".*") ||
		       e.getDateDebut().matches(".*"+text+".*")||
		       e.getDateFin().matches(".*"+text+".*")||
		       e.getTimeDebut().matches(text+".*") ||
		       e.getTimeFin().matches(text+".*") ||
		       
		       e.getDirigeant().matches(text+".*") ||
		       (""+e.getBudget()).matches(text+".*") ||
		      e.getValidation().matches(text+".*")) {
				
				localData.add(e);
			}
		}
		return localData;
	}

}

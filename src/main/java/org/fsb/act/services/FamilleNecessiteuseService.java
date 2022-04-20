package org.fsb.act.services;

import java.util.ArrayList;
import java.util.List;

import org.fsb.act.controllers.FamilleNecessiteuseController;
import org.fsb.act.dao.FamilleNecessiteuseDao;
import org.fsb.act.entities.FamilleNecessiteuse;
import org.fsb.act.entities.Membre;
import org.fsb.act.entities.PersonneNecessiteuse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class FamilleNecessiteuseService {

	public static List<FamilleNecessiteuse> getAll() {
		/*
		List<FamilleNecessiteuse> list= FamilleNecessiteuseDao.getAll();
		for(FamilleNecessiteuse fn: list) {
			//pere
			fn.setPere(PersonneNecessiteuseService.getOneById(fn.getPere().getId()));
			//mere
			fn.setMere(PersonneNecessiteuseService.getOneById(fn.getMere().getId()));
			//fils
			for(PersonneNecessiteuse pn: fn.getListFils())
				fn.getListFils().set(fn.getListFils().indexOf(pn), PersonneNecessiteuseService.getOneById(pn.getId()));
		}
		return list;
		*/
		FamilleNecessiteuse f1= new FamilleNecessiteuse();
		f1.setId(1);
		f1.setPere(new PersonneNecessiteuse(1, "imed", "mak", "bizerte"));
		f1.setMere(new PersonneNecessiteuse(2, "sonia", "mak"));
		f1.getListFils().add(new PersonneNecessiteuse(1, "mohamed", "mak"));
		f1.getListFils().add(new PersonneNecessiteuse(3, "sami", "mak"));
		f1.getListFils().add(new PersonneNecessiteuse(2, "ahmed", "mak"));
		List<FamilleNecessiteuse> list =new ArrayList<>();
		list.add(f1);
		return list;
	}

	public static int supprimerFamille(long id) {
		
		return  FamilleNecessiteuseDao.deleteFamilleFromDB(id);
	}

	public static int modifierFamille(FamilleNecessiteuse fn) {
		
		return FamilleNecessiteuseDao.updateFamilleInDb(fn);
	}

	public static long ajouterFamille(FamilleNecessiteuse fn) {
		
		return FamilleNecessiteuseDao.addFamilleInDb(fn);
	}

	public static ObservableList<FamilleNecessiteuse> searchFamilles(ObservableList<FamilleNecessiteuse> data,
			String text) {
		ObservableList<FamilleNecessiteuse>  localData= FXCollections.observableArrayList();
		for(FamilleNecessiteuse famille: data) {
			if(famille.getPere().getPrenom().matches(text+".*") ||
		       famille.getPere().getNom().matches(text+".*") ||
		       (famille.getPere().getPrenom()+" "+famille.getPere().getNom()).matches(text+".*")||
		       famille.getMere().getPrenom().matches(text+".*") ||
		       famille.getMere().getNom().matches(text+".*") ||
		       (famille.getMere().getPrenom()+" "+famille.getMere().getNom()).matches(text+".*")||
		       (""+famille.getNbFils()).matches(text+".*") ||
		       famille.getAdresse().matches(".*"+text+".*")) {
				
				localData.add(famille);
			}
		}
		return localData;
	}
	
}

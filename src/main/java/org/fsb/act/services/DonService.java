package org.fsb.act.services;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import org.fsb.act.dao.DonDao;
import org.fsb.act.entities.Don;
import org.fsb.act.entities.Donneur;
import org.fsb.act.entities.Membre;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DonService {

	public static List<Don> getAll() {
		List<Don> list= DonDao.getAllFromDb();
		list.forEach(d ->{
			d.setDonneur(getDonneurById(d.getDonneur().getId()));
		});
		list.sort(new Comparator<Don>() {

			@Override
			public int compare(Don o1, Don o2) {
				// TODO Auto-generated method stub
				return (int) (o2.getId() - o1.getId());
			}
		});
		return list;
	}
	public static Donneur getDonneurById(long id) {
		return DonDao.getDonneurById(id);
	}
	public static Don ajouterDon(Don don) {
		try {
		long idDonneur= DonDao.ajouterDonneur(don.getDonneur());
		if(idDonneur!=0) {
			don.getDonneur().setId(idDonneur);
			long idDon= DonDao.ajouterDon(don);
			if(idDon!=0	) {
				don.setId(idDon);
				return don;
			}
		}
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static int updateDon(Don don) {
		try {
			int iDonneur= DonDao.updateDonneur(don.getDonneur());
			if(iDonneur ==1) {
				return DonDao.updateDon(don);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public static int supprimerDon(long idDon, long idDonneur) {
		
		try {
			int i= DonDao.supprimerDon(idDon);
			
			if(i==1)
				return DonDao.supprimerDonneur(idDonneur);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public static ObservableList<Don> searchDons(ObservableList<Don> data, String text) {
		ObservableList<Don>  localData= FXCollections.observableArrayList();
		for(Don don: data) {
			if(don.getDonneur().getPrenom().matches(text+".*") ||
					don.getDonneur().getNom().matches(text+".*") ||
		       (don.getDonneur().getPrenom()+" "+don.getDonneur().getNom()).matches(text+".*")||
		       don.getLeDerniermisejour().matches(".*"+text+".*")||
		       don.getTypeDon().matches(text+".*") ||
		       don.getDonneur().getNationalite().matches(text+".*") ||
		       (""+don.getMontant()).matches(text+".*") ||
		       don.getObjetMateriel()!=null &&don.getObjetMateriel().matches(text+".*") ||
		       (""+don.getDonneur().getPieceIdentite()).matches(text+".*") ||
		       don.getDirigeant().matches(text+".*")) {
				
				localData.add(don);
			}
		}
		return localData;
	}

}

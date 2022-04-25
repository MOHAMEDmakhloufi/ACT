package org.fsb.act.services;

import java.util.List;

import org.fsb.act.dao.CompteAssociationDao;


import org.fsb.act.entities.Compte;

import javafx.collections.ObservableList;

public class CompteAssociationService {
	
	
	public static List<Compte> getDataCompteAssociation() {
		return CompteAssociationDao.getDataAssociation();
	}
	

	public static int updateCompteAssocition(Compte compte) {
		
		return CompteAssociationDao.update(compte);
	}
	
	
	public static int addCompteAssocition(Compte compte) {
		
		return CompteAssociationDao.add(compte);
	}
	
	
	public static List<Compte> searchDataCompteAssociation(String key) {
		return CompteAssociationDao.search(key);
	}
}

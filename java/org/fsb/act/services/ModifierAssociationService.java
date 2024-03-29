package org.fsb.act.services;

import org.fsb.act.dao.CompteAssociationDao;
import org.fsb.act.dao.ModifierAssociationDao;
import org.fsb.act.entities.Association;

public class ModifierAssociationService {
	
	public static Association getDataAssociation() {
		return ModifierAssociationDao.getDataAssociation();
	}

	public static int updateAssocition(Association association) {
		
		return ModifierAssociationDao.update(association);
	}

	public static int creerAssocition(Association association) {
		
		return ModifierAssociationDao.creer(association);
	}
	public static String getAnneeCreation() {
		return ModifierAssociationDao.getAnneeCreation();
	}
}

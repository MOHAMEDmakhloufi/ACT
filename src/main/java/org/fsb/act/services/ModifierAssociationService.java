package org.fsb.act.services;

import org.fsb.act.dao.ModifierAssociationDao;
import org.fsb.act.entities.Association;

public class ModifierAssociationService {
	
	public static Association getDataAssociation() {
		return ModifierAssociationDao.getDataAssociation();
	}

	public static int updateAssocition(Association association) {
		
		return ModifierAssociationDao.update(association);
	}
}

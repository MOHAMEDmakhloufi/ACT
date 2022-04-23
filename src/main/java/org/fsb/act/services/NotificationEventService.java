package org.fsb.act.services;

import java.util.List;

import org.fsb.act.dao.NotificationEventDao;
import org.fsb.act.entities.NotificationEvenement;

public class NotificationEventService {

	public static List<NotificationEvenement> getAllFromService() {
		List<NotificationEvenement> events= NotificationEventDao.getAllFromDb();
		events.forEach(ne -> {
			ne.setEvenement(EvenementService.getOneById(ne.getEvenement().getId()));
		});
		return events;
	}

	public static int updateValidation(NotificationEvenement ne) {
		
		return NotificationEventDao.updateValidationFromDb(ne);
	}

}

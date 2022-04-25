package org.fsb.act.services;

import java.util.List;

import org.fsb.act.dao.NotificationValidationDao;
import org.fsb.act.entities.NotificationValidation;

public class NotificationValidationService {

	public static List<NotificationValidation> getAllFromService() {
		List<NotificationValidation> events=NotificationValidationDao.getAllFromDb();
		events.forEach(nv -> {
			nv.setEvenement(EvenementService.getOneById(nv.getEvenement().getId()));
			nv.setNotifEvent(NotificationEventService.getOneById(nv.getNotifEvent().getId()));
		});
		return events;
	}

}

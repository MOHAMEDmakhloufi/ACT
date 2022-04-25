package org.fsb.act.entities;

public class NotificationValidation {
	private long id;
	private NotificationEvenement notifEvent;
	private Evenement evenement;
	private String date;
	
	public NotificationValidation() {
	}

	public NotificationValidation(long id, NotificationEvenement notifEvent, Evenement evenement, String date) {
		this.id = id;
		this.notifEvent = notifEvent;
		this.evenement = evenement;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public NotificationEvenement getNotifEvent() {
		return notifEvent;
	}

	public void setNotifEvent(NotificationEvenement notifEvent) {
		this.notifEvent = notifEvent;
	}

	public Evenement getEvenement() {
		return evenement;
	}

	public void setEvenement(Evenement evenement) {
		this.evenement = evenement;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}

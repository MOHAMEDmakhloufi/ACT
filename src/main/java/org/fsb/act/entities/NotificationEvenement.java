package org.fsb.act.entities;

public class NotificationEvenement {
	private long id;
	private Evenement evenement;
	private String validation;
	private String dirigeant;
	private String date;
	
	public NotificationEvenement(long id, Evenement evenement, String validation, String dirigeant, String date) {
		this.id = id;
		this.evenement = evenement;
		this.validation = validation;
		this.dirigeant = dirigeant;
		this.date = date;
	}
	
	public NotificationEvenement(long id) {
		this.id = id;
	}

	public NotificationEvenement() {
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Evenement getEvenement() {
		return evenement;
	}
	public void setEvenement(Evenement evenement) {
		this.evenement = evenement;
	}
	public String getValidation() {
		return validation;
	}
	public void setValidation(String validation) {
		this.validation = validation;
	}
	public String getDirigeant() {
		return dirigeant;
	}
	public void setDirigeant(String dirigeant) {
		this.dirigeant = dirigeant;
	}
	
}

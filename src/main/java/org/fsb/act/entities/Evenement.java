package org.fsb.act.entities;

import java.sql.Date;

public class Evenement {
	private long id;
	private String titre ;
	private String lieu ; 
	private String dateDebut ; 
	private String dateFin ;
	private String timeDebut ;
	private String timeFin ;
	private Double budget ;
	private String validation;
	private String dirigeant;
	public Evenement() {
		
	}

	

	public Evenement(long id) {
		this.id = id;
	}



	public Evenement(long id, String titre, String lieu, String dateDebut, String dateFin, String timeDebut,
			String timeFin, Double budget, String validation, String dirigeant) {
		super();
		this.id = id;
		this.titre = titre;
		this.lieu = lieu;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.timeDebut = timeDebut;
		this.timeFin = timeFin;
		this.budget = budget;
		this.validation = validation;
		this.dirigeant = dirigeant;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getTimeDebut() {
		return timeDebut;
	}

	public void setTimeDebut(String timeDebut) {
		this.timeDebut = timeDebut;
	}

	public String getTimeFin() {
		return timeFin;
	}

	public void setTimeFin(String timeFin) {
		this.timeFin = timeFin;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evenement other = (Evenement) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
}

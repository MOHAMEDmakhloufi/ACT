package org.fsb.act.entities;

public class Depense {
	private long id;
	private long famille;
	private long personneNecessiteuse;
	private String description;
	private double montant;
	private String derniereMiseJour;
	private String dirigeant;
	private String destination;
	
	
	public Depense() {
	}

	
	
	public Depense(long id, long famille, long personneNecessiteuse, String description, double montant,
			String derniereMiseJour, String dirigeant, String destination) {
		this.id = id;
		this.famille = famille;
		this.personneNecessiteuse = personneNecessiteuse;
		this.description = description;
		this.montant = montant;
		this.derniereMiseJour = derniereMiseJour;
		this.dirigeant = dirigeant;
		this.destination = destination;
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getFamille() {
		return famille;
	}
	public void setFamille(long famille) {
		this.famille = famille;
	}
	public long getPersonneNecessiteuse() {
		return personneNecessiteuse;
	}
	public void setPersonneNecessiteuse(long personneNecessiteuse) {
		this.personneNecessiteuse = personneNecessiteuse;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getDerniereMiseJour() {
		return derniereMiseJour;
	}
	public void setDerniereMiseJour(String derniereMiseJour) {
		this.derniereMiseJour = derniereMiseJour;
	}
	public String getDirigeant() {
		return dirigeant;
	}
	public void setDirigeant(String dirigeant) {
		this.dirigeant = dirigeant;
	}



	public String getDestination() {
		return destination;
	}



	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
}

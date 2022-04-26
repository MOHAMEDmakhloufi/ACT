package org.fsb.act.entities;

public class CotisationMembre {
	private long id;
	private long membre;
	private String prenom;//  ziina ou bara (n'est pas enregestrier dans le database)
	private String nom; // ziina ou bara (n'est pas enregestrier dans le database)
	private double montant;
	private String derniereMiseJour;
	private String dirigeant;
	public CotisationMembre(long id, long membre, double montant, String derniereMiseJour, String dirigeant) {
		this.id = id;
		this.membre = membre;
		this.montant = montant;
		this.derniereMiseJour = derniereMiseJour;
		this.dirigeant = dirigeant;
	}
	
	public CotisationMembre(long id, long membre, String prenom, String nom, double montant, String derniereMiseJour,
			String dirigeant) {
		this.id = id;
		this.membre = membre;
		this.prenom = prenom;
		this.nom = nom;
		this.montant = montant;
		this.derniereMiseJour = derniereMiseJour;
		this.dirigeant = dirigeant;
	}
	public CotisationMembre() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getMembre() {
		return membre;
	}
	public void setMembre(long membre) {
		this.membre = membre;
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}

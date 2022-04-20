package org.fsb.act.entities;

public class PersonneNecessiteuse {
	private long id;
	private String prenom;
	private String nom;
	private String adresse;
	
	public PersonneNecessiteuse(long id, String prenom, String nom) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		
	}
	public PersonneNecessiteuse(long id) {
		this.id = id;
		
	}
	public PersonneNecessiteuse(long id, String prenom, String nom, String adresse) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
	}

	public PersonneNecessiteuse() {
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return  prenom + " " + nom ;
	}
	
}

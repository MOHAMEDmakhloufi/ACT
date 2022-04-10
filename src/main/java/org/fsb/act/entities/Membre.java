package org.fsb.act.entities;

import java.util.Date;

public class Membre {
	private String prenom;
	private String nom;
	private Date dateNaissance;
	private String profession;
	private String email;
	private String telephone;
	private long numeroIdentite;
	
	
	public Membre() {
	}


	public Membre(String prenom, String nom, Date dateNaissance, String profession, String email, String telephone,
			long numeroIdentite) {
		this.prenom = prenom;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.profession = profession;
		this.email = email;
		this.telephone = telephone;
		this.numeroIdentite = numeroIdentite;
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


	public Date getDateNaissance() {
		return dateNaissance;
	}


	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	public String getProfession() {
		return profession;
	}


	public void setProfession(String profession) {
		this.profession = profession;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public long getNumeroIdentite() {
		return numeroIdentite;
	}


	public void setNumeroIdentite(long numeroIdentite) {
		this.numeroIdentite = numeroIdentite;
	}
	
	
	
}

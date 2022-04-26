package org.fsb.act.entities;

import java.util.Arrays;
import java.util.Date;

public class Membre {
	private long id;
	private String prenom;
	private String nom;
	private String dateNaissance;
	private String profession;
	private String email;
	private String telephone;
	private String typePiece;
	private String dirigeant;
	private long numeroIdentite;
	private byte[] image;
	private byte[] copiePiece;
	
	public Membre() {
	}

	public Membre(long id) {
		this.id = id;
	}

	public Membre(long id, String prenom, String nom) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
	}

	public Membre(long id, String prenom, String nom, String dateNaissance, String profession, String email,
			String telephone, String typePiece, String dirigeant, long numeroIdentite, byte[] image,
			byte[] copiePiece) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.profession = profession;
		this.email = email;
		this.telephone = telephone;
		this.typePiece = typePiece;
		this.dirigeant = dirigeant;
		this.numeroIdentite = numeroIdentite;
		this.image = image;
		this.copiePiece = copiePiece;
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

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
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

	public String getTypePiece() {
		return typePiece;
	}

	public void setTypePiece(String typePiece) {
		this.typePiece = typePiece;
	}

	public String getDirigeant() {
		return dirigeant;
	}

	public void setDirigeant(String dirigeant) {
		this.dirigeant = dirigeant;
	}

	public long getNumeroIdentite() {
		return numeroIdentite;
	}

	public void setNumeroIdentite(long numeroIdentite) {
		this.numeroIdentite = numeroIdentite;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public byte[] getCopiePiece() {
		return copiePiece;
	}

	public void setCopiePiece(byte[] copiePiece) {
		this.copiePiece = copiePiece;
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
		Membre other = (Membre) obj;
		if (id != other.id)
			return false;
		if (numeroIdentite != other.numeroIdentite)
			return false;
		return true;
	}

	

}

package org.fsb.act.entities;

public class Donneur {
	
	private long id;
	private String prenom;
	private String nom;
	private long pieceIdentite;
	private String nationalite;
	public Donneur(long id, String prenom, String nom, long pieceIdentite, String nationalite) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.pieceIdentite = pieceIdentite;
		this.nationalite = nationalite;
	}
	public Donneur() {
	}
	public Donneur(long id) {
		this.id= id;
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
	public long getPieceIdentite() {
		return pieceIdentite;
	}
	public void setPieceIdentite(long pieceIdentite) {
		this.pieceIdentite = pieceIdentite;
	}
	public String getNationalite() {
		return nationalite;
	}
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}
	
}

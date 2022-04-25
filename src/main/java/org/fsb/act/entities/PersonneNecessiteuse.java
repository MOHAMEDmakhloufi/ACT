package org.fsb.act.entities;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;


public class PersonneNecessiteuse {
	SimpleLongProperty id;
	SimpleStringProperty prenom;
	SimpleStringProperty nom;
	SimpleStringProperty prenomPere;
	SimpleStringProperty prenomGPere;
	SimpleStringProperty prenomMere;
	SimpleStringProperty nomMere;
	SimpleStringProperty dateN;
	SimpleStringProperty lieuN;
	SimpleStringProperty nationalite;
	SimpleStringProperty gouvernorat;
	SimpleStringProperty profession;
	SimpleStringProperty etatCivil;
	SimpleIntegerProperty identite;
	SimpleStringProperty typeIdentite;
	SimpleStringProperty email;
	SimpleStringProperty telephone;
	SimpleStringProperty adresse;
	SimpleIntegerProperty codePostal;
	SimpleStringProperty dirigeant;
	public PersonneNecessiteuse(String prenom, String nom, String prenomPere,
			String prenomGPere, String prenomMere, String nomMere,
			String dateN, String lieuN, String nationalite,
			String gouvernorat, String etatCivil, String profession,
			Integer identite, String typeIdentite, String email,
			String telephone, String adresse, Integer codePostal,String dirigeant) {
		super();
		this.prenom = new SimpleStringProperty(prenom);
		this.nom = new SimpleStringProperty(nom);
		this.prenomPere = new SimpleStringProperty(prenomPere);
		this.prenomGPere = new SimpleStringProperty(prenomGPere);
		this.prenomMere = new SimpleStringProperty(prenomMere);
		this.nomMere = new SimpleStringProperty(nomMere);
		this.dateN = new SimpleStringProperty(dateN);
		this.lieuN = new SimpleStringProperty(lieuN);
		this.nationalite = new SimpleStringProperty(nationalite);
		this.gouvernorat = new SimpleStringProperty(gouvernorat);
		this.profession = new SimpleStringProperty(profession);
		this.etatCivil = new SimpleStringProperty(etatCivil);
		this.identite = new SimpleIntegerProperty(identite);
		this.typeIdentite = new SimpleStringProperty(typeIdentite);
		this.email = new SimpleStringProperty(email);
		this.telephone = new SimpleStringProperty(telephone);
		this.adresse = new SimpleStringProperty(adresse);
		this.codePostal = new SimpleIntegerProperty(codePostal);
		this.dirigeant = new SimpleStringProperty(dirigeant);
	}
	
	public PersonneNecessiteuse(Long id,String prenom, String nom, String prenomPere,
			String prenomGPere, String prenomMere, String nomMere,
			String dateN, String lieuN, String nationalite,
			String gouvernorat, String etatCivil,String profession, 
			Integer identite, String typeIdentite, String email,
			String telephone, String adresse, Integer codePostal, String dirigeant) {
		super();
		this.id=new SimpleLongProperty(id);
		this.prenom = new SimpleStringProperty(prenom);
		this.nom = new SimpleStringProperty(nom);
		this.prenomPere = new SimpleStringProperty(prenomPere);
		this.prenomGPere = new SimpleStringProperty(prenomGPere);
		this.prenomMere = new SimpleStringProperty(prenomMere);
		this.nomMere = new SimpleStringProperty(nomMere);
		this.dateN = new SimpleStringProperty(dateN);
		this.lieuN = new SimpleStringProperty(lieuN);
		this.nationalite = new SimpleStringProperty(nationalite);
		this.gouvernorat = new SimpleStringProperty(gouvernorat);
		this.profession = new SimpleStringProperty(profession);
		this.etatCivil = new SimpleStringProperty(etatCivil);
		this.identite = new SimpleIntegerProperty(identite);
		this.typeIdentite = new SimpleStringProperty(typeIdentite);
		this.email = new SimpleStringProperty(email);
		this.telephone = new SimpleStringProperty(telephone);
		this.adresse = new SimpleStringProperty(adresse);
		this.codePostal = new SimpleIntegerProperty(codePostal);
		this.dirigeant = new SimpleStringProperty(dirigeant);
	}
	
	public PersonneNecessiteuse() {
		super();
		this.id=new SimpleLongProperty();
		this.prenom = new SimpleStringProperty(" ");
		this.nom = new SimpleStringProperty(" ");
		this.prenomPere = new SimpleStringProperty(" ");
		this.prenomGPere = new SimpleStringProperty(" ");
		this.prenomMere = new SimpleStringProperty(" ");
		this.nomMere = new SimpleStringProperty(" ");
		this.dateN = new SimpleStringProperty("1111-11-11");
		this.lieuN = new SimpleStringProperty(" ");
		this.nationalite = new SimpleStringProperty(" ");
		this.gouvernorat = new SimpleStringProperty(" ");
		this.profession = new SimpleStringProperty(" ");
		this.etatCivil = new SimpleStringProperty(" ");
		this.identite = new SimpleIntegerProperty(0000);
		this.typeIdentite = new SimpleStringProperty(" ");
		this.email = new SimpleStringProperty(" ");
		this.telephone = new SimpleStringProperty(" ");
		this.adresse = new SimpleStringProperty(" ");
		this.codePostal = new SimpleIntegerProperty(0000);
		this.dirigeant = new SimpleStringProperty();
	}
	public Long getId() {
		return id.get();
	}
	public void setId(Long id) {
		this.id.set(id);
	}
	public String getPrenom() {
		return prenom.get();
	}
	public void setPrenom(String prenom) {
		this.prenom.set(prenom);
	}
	public String getNom() {
		return nom.get();
	}
	public void setNom(String nom) {
		this.nom.set(nom);
	}
	public String getPrenomPere() {
		return prenomPere.get();
	}
	public void setPrenomPere(String prenomPere) {
		this.prenomPere.set(prenomPere);
	}
	public String getPrenomGPere() {
		return prenomGPere.get();
	}
	public void setPrenomGPere(String prenomGPere) {
		this.prenomGPere.set(prenomGPere);
	}
	public String getPrenomMere() {
		return prenomMere.get();
	}
	public void setPrenomMere(String prenomMere) {
		this.prenomMere.set(prenomMere);
	}
	public String getNomMere() {
		return nomMere.get();
	}
	public void setNomMere(String nomMere) {
		this.nomMere.set(nomMere);
	}	
	public String getDateN() {
		return dateN.get();
	}
	public void setDateN(String dateN) {
		this.dateN.set(dateN);
	}	
	public String getLieuN() {
		return lieuN.get();
	}
	public void setLieuN(String lieuN) {
		this.lieuN.set(lieuN);
	}
	public String getNationalite() {
		return nationalite.get();
	}
	public void setNationalite(String nationalite) {
		this.nationalite.set(nationalite);
	}
	public String getGouvernorat() {
		return gouvernorat.get();
	}
	public void setGouvernorat(String gouvernorat) {
		this.gouvernorat.set(gouvernorat);
	}
	public String getProfession() {
		return profession.get();
	}
	public void setProfession(String profession) {
		this.profession.set(profession);
	}
	public String getEtatCivil() {
		return etatCivil.get();
	}
	public void setEtatCivil(String etatCivil) {
		this.etatCivil.set(etatCivil);
	}
	public Integer getIdentite() {
		return identite.get();
	}
	public void setIdentite(Integer identite) {
		this.identite.set(identite);
	}
	public String getTypeIdentite() {
		return typeIdentite.get();
	}
	public void setTypeIdentite(String typeIdentite) {
		this.typeIdentite.set(typeIdentite);
	}
	public String getEmail() {
		return email.get();
	}
	public void setEmail(String email) {
		this.email.set(email);
	}
	public String getTelephone() {
		return telephone.get();
	}
	public void setTelephone(String telephone) {
		this.telephone.set(telephone);
	}
	public String getAdresse() {
		return adresse.get();
	}
	public void setAdresse(String adresse) {
		this.adresse.set(adresse);
	}
	public Integer getCodePostal() {
		return codePostal.get();
	}
	public void setCodePostal(Integer codePostal) {
		this.codePostal.set(codePostal);
	}
	public String getDirigeant() {
		return dirigeant.get();
	}
	public void setDirigeant(String dirigeant) {
		this.dirigeant.set(dirigeant);
	}
}
package org.fsb.act.entities;

import java.util.ArrayList;
import java.util.List;

public class FamilleNecessiteuse {
	private long id;
	private PersonneNecessiteuse pere;
	private PersonneNecessiteuse mere;
	private List<PersonneNecessiteuse> listFils= new ArrayList<>();
	private String adresse;
	private int nbFils;
	public FamilleNecessiteuse() {
	}

	public FamilleNecessiteuse(long id, PersonneNecessiteuse pere, PersonneNecessiteuse mere,
			List<PersonneNecessiteuse> listFils, String adresse, int nbFils) {
		this.id = id;
		this.pere = pere;
		this.mere = mere;
		this.listFils = listFils;
		this.adresse = adresse;
		this.nbFils = nbFils;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNbFils(int nbFils) {
		this.nbFils = nbFils;
	}

	public PersonneNecessiteuse getPere() {
		return pere;
	}
	public void setPere(PersonneNecessiteuse pere) {
		this.pere = pere;
	}
	public PersonneNecessiteuse getMere() {
		return mere;
	}
	public void setMere(PersonneNecessiteuse mere) {
		this.mere = mere;
	}
	public List<PersonneNecessiteuse> getListFils() {
		return listFils;
	}
	public void setListFils(List<PersonneNecessiteuse> listFils) {
		this.listFils = listFils;
	}
	public String getAdresse() {
		return pere.getAdresse();
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getNbFils() {
		return listFils.size();
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
		FamilleNecessiteuse other = (FamilleNecessiteuse) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}

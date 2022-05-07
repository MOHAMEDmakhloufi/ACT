package org.fsb.act.entities;

public class Don {
	private long id;
	private	Donneur donneur;
	private String typeDon;
	private double montant;
	private String objetMateriel;
	private String leDerniermisejour;
	private String dirigeant;
	public Don(long id, Donneur donneur, String typeDon, double montant, String objetMateriel, String leDerniermisejour,
			String dirigeant) {
		this.id = id;
		this.donneur = donneur;
		this.typeDon = typeDon;
		this.montant = montant;
		this.objetMateriel = objetMateriel;
		this.leDerniermisejour = leDerniermisejour;
		this.dirigeant = dirigeant;
	}
	public Don() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Donneur getDonneur() {
		return donneur;
	}
	public void setDonneur(Donneur donneur) {
		this.donneur = donneur;
	}
	public String getTypeDon() {
		return typeDon;
	}
	public void setTypeDon(String typeDon) {
		this.typeDon = typeDon;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getObjetMateriel() {
		return objetMateriel;
	}
	public void setObjetMateriel(String objetMateriel) {
		this.objetMateriel = objetMateriel;
	}
	public String getLeDerniermisejour() {
		return leDerniermisejour;
	}
	public void setLeDerniermisejour(String leDerniermisejour) {
		this.leDerniermisejour = leDerniermisejour;
	}
	public String getDirigeant() {
		return dirigeant;
	}
	public void setDirigeant(String dirigeant) {
		this.dirigeant = dirigeant;
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
		Don other = (Don) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}

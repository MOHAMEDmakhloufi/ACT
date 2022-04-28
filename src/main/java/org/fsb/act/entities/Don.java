package org.fsb.act.entities;

import java.util.Date;

public class Don {
	private long id ; 
	private long iddonneur ;
	private String typeDon;
	private float montant ;
	private String objetMateriel ;
	private Date dernieMiseAJour ;
	private String Dirigeant ;
	
	public Don() {
		
	}

	public Don(long id, long iddonneur, String typeDon, float montant, String objetMateriel, Date dernieMiseAJour,
			String dirigeant) {
		super();
		this.id = id;
		this.iddonneur = iddonneur;
		this.typeDon = typeDon;
		this.montant = montant;
		this.objetMateriel = objetMateriel;
		this.dernieMiseAJour = dernieMiseAJour;
		Dirigeant = dirigeant;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIddonneur() {
		return iddonneur;
	}

	public void setIddonneur(long iddonneur) {
		this.iddonneur = iddonneur;
	}

	public String getTypeDon() {
		return typeDon;
	}

	public void setTypeDon(String typeDon) {
		this.typeDon = typeDon;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public String getObjetMateriel() {
		return objetMateriel;
	}

	public void setObjetMateriel(String objetMateriel) {
		this.objetMateriel = objetMateriel;
	}

	public Date getDernieMiseAJour() {
		return dernieMiseAJour;
	}

	public void setDernieMiseAJour(Date dernieMiseAJour) {
		this.dernieMiseAJour = dernieMiseAJour;
	}

	public String getDirigeant() {
		return Dirigeant;
	}

	public void setDirigeant(String dirigeant) {
		Dirigeant = dirigeant;
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

package org.fsb.act.entities;

public class Association {
	private String titre;
	private String siege;
	private String email;
	private String telephone;
	private byte[] logo;
	private String username;
	private String password;
	
	public Association(String titre, String siege, String email, String telephone, byte[] logo, String username,
			String password) {
		this.titre = titre;
		this.siege = siege;
		this.email = email;
		this.telephone = telephone;
		this.logo = logo;
		this.username = username;
		this.password = password;
	}
	public Association() {
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getSiege() {
		return siege;
	}
	public void setSiege(String siege) {
		this.siege = siege;
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
	public byte[] getLogo() {
		return logo;
	}
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

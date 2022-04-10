package models;

public class LogoTitre {
	private byte[] logo;
	private String titre;
	public LogoTitre(byte[] logo, String titre) {
		this.logo = logo;
		this.titre = titre;
	}
	public LogoTitre() {
	}
	public byte[] getLogo() {
		return logo;
	}
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
}

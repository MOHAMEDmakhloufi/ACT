package org.fsb.act.entities;

public class Compte {
	private String username;
	private String password;
	private String activation;
	public Compte(String usernam, String password, String activation) {
		this.username = usernam;
		this.password = password;
		this.activation = activation;
	}
	public Compte() {
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String usernam) {
		this.username = usernam;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getActivation() {
		return activation;
	}
	public void setActivation(String activation) {
		this.activation = activation;
	}
	
}

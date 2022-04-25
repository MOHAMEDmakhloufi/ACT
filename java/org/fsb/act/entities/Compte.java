package org.fsb.act.entities;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Compte {
	SimpleStringProperty username;
	SimpleStringProperty password;
	SimpleIntegerProperty active;
	
	public Compte(String username, String password,int active) {
		super();
		this.username = new SimpleStringProperty(username);
		this.password = new SimpleStringProperty(password);
		this.active=new SimpleIntegerProperty(active);
	}
	public Compte() {	
		this.username = new SimpleStringProperty();
		this.password = new SimpleStringProperty();
		this.active=new SimpleIntegerProperty(1);
	}
	
	public String getUsername() {
		return username.get();
	}
	public void setUsername(String username) {
		this.username.set(username);
	}
	public String getPassword() {
		return password.get();
	}
	public void setPassword(String password) {
		this.password.set(password);
	}
	public void setActive(int active) {
		this.active.set(active);
	}
	public int getActive() {
		return active.get();
	}

}

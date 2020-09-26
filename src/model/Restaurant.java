package model;

import java.io.Serializable;

public class Restaurant implements Serializable {
	private static final long serialVersionUID = 1;
	
	private String name;
	private	int nit;
	private String adminName;
	
	public Restaurant(int nit, String name, String adminName) {
		this.name = name;
		this.nit = nit;
		this.adminName = adminName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNit() {
		return nit;
	}

	public void setNit(int nit) {
		this.nit = nit;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	@Override
	public String toString() {
		return "\nName: " + name + "\nNIT: " + nit + "\nAdministrator's name: " + adminName;
	}
}

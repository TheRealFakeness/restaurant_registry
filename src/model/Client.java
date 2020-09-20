package model;

import java.io.Serializable;

public class Client implements Serializable {
	private static final long serialVersionUID = 1;
	
	private String idType;
	private int id;
	private String name;
	private int phone;
	private String address;
	
	public Client(String idType, int id, String name, int phone, String address) {
		this.idType = idType;
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
	
	public String getIdType() {
		return idType;
	}
	
	public void setIdType(String idType) {
		this.idType = idType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Name: " + name + "\nID Type: " + idType + "\nID: " + id + "\nPhone number" +
	phone + "\nAddress: " + address;
	}
}
package model;

import java.io.Serializable;
import java.lang.Comparable;

public class Client implements Serializable, Comparable<Client>{
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
	
	/**
	 Compares by client's name, taking the last name as first criterion, and first name as second <br>
	 <b>pre</b> Both clients must have a named comprised of at least two names (only the first two will
	 affect the comparison <br>
	 <b>post</b> A positive integer will be returned if the first name is greater than the second
	 one, a negative integer if it is lesser and 0 if they're equal <br>
	 @param otherClient The client to be compared to <br>
	 */
	@Override
	public int compareTo(Client otherClient) {
		String ln1 = this.name.split(" ")[1];
		String ln2 = otherClient.getName().split(" ")[1];
		
		int comp = 0;
		
		if(ln1.equals(ln2)) {
			String fn1 = this.name;
			String fn2 = otherClient.getName();
			
			comp = fn1.compareTo(fn2);
		}else {
			comp = ln1.compareTo(ln2);
		}
		
		return comp;
	}
}
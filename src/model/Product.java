package model;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1;
	
	private int id;
	private String name;
	private String description;
	private int price;
	private int nit;
	
	public Product(int id, String name, String description, int price, int nit) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.nit = nit;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNit() {
		return nit;
	}

	public void setNit(int nit) {
		this.nit = nit;
	}
	
	
}

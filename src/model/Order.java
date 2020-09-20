package model;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;

public class Order implements Serializable{
	private static final long serialVersionUID = 1;
	
	private int id;
	private Date time;
	private int clientId;
	private int nit;
	private ArrayList<Integer> productIds;
	private ArrayList<Integer> productAmount;
	
	public Order(int id, Date time, int clientId, int nit, ArrayList<Integer> productIds,
			ArrayList<Integer> productAmount) {
		this.id = id;
		this.time = time;
		this.clientId = clientId;
		this.nit = nit;
		this.productIds = productIds;
		this.productAmount = productAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getNit() {
		return nit;
	}

	public void setNit(int nit) {
		this.nit = nit;
	}

	public ArrayList<Integer> getProductIds() {
		return productIds;
	}

	public void setProductIds(ArrayList<Integer> productIds) {
		this.productIds = productIds;
	}

	public ArrayList<Integer> getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(ArrayList<Integer> productAmount) {
		this.productAmount = productAmount;
	}
}

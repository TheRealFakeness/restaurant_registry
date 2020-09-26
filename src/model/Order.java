package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import exceptions.InvalidStatusUpdateException;
import java.util.Collections;

public class Order implements Serializable, Comparable<Order>{
	private static final long serialVersionUID = 1;
	
	private String id;
	private Calendar time;
	private int clientId;
	private int nit;
	private ArrayList<Integer> productIds;
	private ArrayList<Integer> productAmount;
	private OrderStatus orderStatus;
	
	/**
	 * Creates an order object with the current time an a generated ID based on the current time.
	 */
	public Order(int clientId, int nit, ArrayList<Integer> productIds,
			ArrayList<Integer> productAmount) {
		this.clientId = clientId;
		time = Calendar.getInstance();
		this.nit = nit;
		this.productIds = productIds;
		this.productAmount = productAmount;
		
		this.id = Integer.toString(time.get(Calendar.YEAR)) + 
				time.get(Calendar.MONTH) + time.get(Calendar.DAY_OF_MONTH) + 
				time.get(Calendar.HOUR) + time.get(Calendar.MINUTE) +
				time.get(Calendar.SECOND) + time.get(Calendar.MILLISECOND);
		
		orderStatus = OrderStatus.R;
	}
	
	/**
	 * Creates an order object with all the given attributes.
	 */
	public Order(String id, int nit, int clientId, ArrayList<Integer> productIds,
			ArrayList<Integer> productAmount) {
		this.id = id;
		this.clientId = clientId;
		time = Calendar.getInstance();
		this.nit = nit;
		this.productIds = productIds;
		this.productAmount = productAmount;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
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
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	
	/**
	 * Changes the status of the order. <br>
	 * @param newStatus New status
	 * @throws InvalidStatusUpdateException Thrown if the status requested is behind the current one
	 */
	public void updateStatus(OrderStatus newStatus) throws InvalidStatusUpdateException{
		if(newStatus.compareTo(orderStatus) < 0) {
			throw new InvalidStatusUpdateException(newStatus);
		}
		
		orderStatus = newStatus;
	}
	
	/**
	 Compares orders by the following criteria: ascending nit, descending client id, 
	 ascending date.
	 @param o2 Order to be compared to.
	 */
	@Override
	public int compareTo(Order o2) {
		int comp = 0;
		if(nit == o2.nit) {
			if(clientId == o2.clientId) {
				if(time == o2.time) {
					comp = 0;
				}else if(time.compareTo(o2.time) > 0) {
					comp = 1;
				}else {
					comp = -1;
				}
			}else if(clientId < o2.clientId) {
				comp = 1;
			}else {
				comp = -1;
			}
		}else if(nit > o2.nit) {
			comp = 1;
		}else {
			comp = -1;
		}
		
		return comp;
	}
	
	/**
	 * The products are sorted by ID using selection sort.<br>
	 * <b>pre:</b><br>
	 * <b>post:</b>Both productIds and productAmount will be sorted.<br>
	 */
	public void sortProducts() {
		int min = 0;
		
		for(int i=0; i<productIds.size(); i++) {
			min = productIds.get(i);
			
			for(int j=i; j<productIds.size(); j++) {
				if(productIds.get(j) < min) {
					min = productIds.get(j);
				}
			}
			
			if(productIds.get(i) != min) {
				boolean swapped = false;
				
				for(int k=0; !swapped; k++) {
					if(productIds.get(k) == min) {
						Collections.swap(productIds, i, k);
						Collections.swap(productAmount, i, k);
						
						swapped = true;
					}
				}
			}
		}
	}
}
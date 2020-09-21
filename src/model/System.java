package model;

import java.util.LinkedList;

public class System {
	private LinkedList<Client> clients;
	private LinkedList<Order> orders;
	private LinkedList<Product> products;
	private LinkedList<Restaurant> restaurants;
	
	/**
	 Adds a restaurant with the given attributes to the object restaurants. <br>
	 <b>pre:</b> <br>
	 <b>post:</b> The restaurant will be added to the object restaurants.<br>
	 */
	public void addRestaurant(String name, int nit, String adminName) {
		restaurants.add(new Restaurant(name, nit, adminName));
	}
	
	/**
	 Adds a product with the given attributes to the object products. <br>
	 <b>pre:</b> <br>
	 <b>post:</b> The product will be added to the object products <br>
	 */
	public void addProduct(int id, String name, String description, int price, int nit) {
		products.add(new Product(id, name, description, price, nit));
	}
	
	/**
	 Adds a client with the given attributes to the object clients. Then, the client is sorted 
	 alphabetically automatically <br>
	 <b>pre:</b> <br>
	 <b>post:</b> The client will be added sorted to the object clients <br>
	 */
	public void addClient(String idType, int id, String name, int phone, String address) {
		boolean noElements = false;
		int index = 0;
		Client c = new Client(idType, id, name, phone, address);
		
		if(clients.size() == 0) {
			noElements = true;
		}else if(c.compareTo(clients.get(0)) > 0) {
			index = 0;
		}else if(c.compareTo(clients.get((clients.size() - 1))) < 0) {
			index = clients.size();
		}else {
			int i = 0;
			while(c.compareTo(clients.get(i)) < 0) {
				i++;
			}
			index = i;
		}
		
		if(!noElements) {
			clients.add(index, c);
		}else{
			clients.add(c);
		}
	}
}
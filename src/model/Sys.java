package model;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;
import java.io.BufferedReader;

import exceptions.IDNotFoundException;
import exceptions.InvalidStatusUpdateException;
import exceptions.ClientNotFoundException;
import comparators.ClientNameComparator;
import exceptions.OrderNotFoundException;
import exceptions.PersistenceException;
import comparators.ClientPhoneComparator;

public class Sys {
	private LinkedList<Client> clients;
	private LinkedList<Order> orders;
	private LinkedList<Product> products;
	private LinkedList<Restaurant> restaurants;
	
	public Sys() {
		this.clients = new LinkedList<>();
		this.orders = new LinkedList<>();
		this.products = new LinkedList<>();
		this.restaurants = new LinkedList<>();
	}
	
	public LinkedList<Client> getClients() {
		return clients;
	}

	public void setClients(LinkedList<Client> clients) {
		this.clients = clients;
	}

	public LinkedList<Order> getOrders() {
		return orders;
	}

	public void setOrders(LinkedList<Order> orders) {
		this.orders = orders;
	}

	public LinkedList<Product> getProducts() {
		return products;
	}

	public void setProducts(LinkedList<Product> products) {
		this.products = products;
	}

	public LinkedList<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(LinkedList<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	/**
	 Adds a restaurant with the given attributes to the object restaurants. <br>
	 <b>pre:</b> <br>
	 <b>post:</b> The restaurant will be added to the object restaurants.<br>
	 * @throws IOException 
	 */
	public void addRestaurant(int nit, String name, String adminName) throws IOException {
		restaurants.add(new Restaurant(nit, name, adminName));
		saveRestaurants();
	}
	
	/**
	 * Adds a product with the given attributes to the object products. <br>
	 * <b>pre:</b> <br>
	 * <b>post:</b> The product will be added to the object products <br>
	 * @throws IOException 
	 */
	public void addProduct(int id, String name, String description, int price, int nit) throws IOException {
		products.add(new Product(id, name, description, price, nit));
		saveProducts();
	}
	
	/**
	 * Adds a client with the given attributes to the object clients. Then, the client is sorted 
	 in descending order alphabetically automatically <br>
	 * <b>pre:</b> <br>
	 * <b>post:</b> The client will be added sorted in descending order to the object clients <br>
	 * @throws IOException 
	 */
	public void addClient(String idType, int id, String name, int phone, String address) throws IOException {
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
		
		saveClients();
	}
	
	/**
	 * Adds an order with the given attributes to the object orders. <br>
	 * <b>pre:</b> productsIds and productAmount must have the same size.<br>
	 * <b>post:</b> The order will be added to the object orders <br>
	 * @throws IOException 
	 */
	public void addOrder(int clientId, int nit, ArrayList<Integer> productIds, 
			ArrayList<Integer> productAmount) throws IOException {
		orders.add(new Order(clientId, nit, productIds, productAmount));
		
		saveOrders();
	}
	
	public void addOrder(String id, int nit, int clientId, ArrayList<Integer> productIds,
			ArrayList<Integer> productAmount) throws IOException {
		orders.add(new Order(id, nit, clientId, productIds, productAmount));
		
		saveOrders();
	}
	
	/**
	 * Looks for a given client by ID and returns their index or throws an exception
	 if they're not found. <br>
	 * <b>pre</b>
	 * <b>post</b>
	 * @param id The ID of the client that will be looked for <br>
	 * @return index Index of the client found in the object clients. <br>
	 * @throws IDNotFoundException Thrown if the element is not found. <br>	
	 */
	public int findClient(int id) throws IDNotFoundException{
		boolean found = false;
		int index = 0;
		for(int i=0; i<clients.size() && found == false; i++) {
			if(clients.get(i).getId() == id) {
				found = true;
				index = i;
			}
		}
		
		if(!found) {
			throw new IDNotFoundException(id);
		}
		
		return index;
	}
	
	/**
	 * Looks for a given restaurant by NIT and returns its index or throws an exception
	 if it's not found. <br>
	 * <b>pre</b>
	 * <b>post</b>
	 * @param nit The NIT of the restaurant that will be looked for <br>
	 * @return index Index of the restaurant found in the object restaurants
	 * @throws IDNotFoundException Thrown if the element is not found. <br>	
	 */
	public int findRestaurant(int nit) throws IDNotFoundException{
		boolean found = false;
		int index = 0;
		for(int i=0; i<restaurants.size() && found == false; i++) {
			if(restaurants.get(i).getNit() == nit) {
				found = true;
				index = i;
			}
		}
		
		if(!found) {
			throw new IDNotFoundException(nit);
		}
		
		return index;
	}
	
	/**
	 * Looks for a given product by ID and returns its index or throws an exception
	 if it's not found. <br>
	 * <b>pre</b>
	 * <b>post</b>
	 * @param id The ID of the product that will be looked for. <br>
	 * @return index Index of the product found in the object products.
	 * @throws IDNotFoundException Thrown if the element is not found.
	 */
	public int findProduct(int id) throws IDNotFoundException{
		boolean found = false;
		int index = 0;
		for(int i=0; i<products.size() && found == false; i++) {
			if(products.get(i).getId() == id) {
				found = true;
				index = i;
			}
		}
		
		if(!found) {
			throw new IDNotFoundException(id);
		}
		
		return index;
	}
	
	/**
	 Looks for a given order by ID and returns its index or throws an exception
	 if it's not found. <br>
	 * <b>pre</b>
	 * <b>post</b>
	 * @param id The ID of the product that will be looked for. <br>
	 * @return index Index of the found order.
	 * @throws OrderNotFoundException
	 */
	public int findOrder(String id) throws OrderNotFoundException{
		boolean found = false;
		int index = 0;
		for(int i=0; i<products.size() && found == false; i++) {
			if(orders.get(i).getId().equals(id)) {
				found = true;
				index = i;
			}
		}
		
		if(!found) {
			throw new OrderNotFoundException(id);
		}
		
		return index;
	}
	
	/**
	 * Updates a restaurant's name and administrator's name given its index. <br>
	 * <b>pre</b>The given index has to exist<br>
	 * <b>post</b><br>
	 * @param index The index of the restaurant that is going to be updated.
	 * @param name New name for the restaurant.
	 * @param adminName New administrator's name for the restaurant.
	 * @throws IOException 
	 */
	public void updateRestaurant(int index, String name, String adminName) throws IOException{
		restaurants.get(index).setName(name);
		restaurants.get(index).setAdminName(adminName);
		
		saveRestaurants();
	}
	
	/**
	 * Updates a client's data given its index. <br>
	 * <b>pre</b>The given index has to exist<br>
	 * <b>post</b><br>
	 * @param index The index of the client that is going to be updated.
	 * @param idType ID Type
	 * @param id ID
	 * @param name Name
	 * @param phone Phone
	 * @param address Address
	 * @throws IOException 
	 */
	public void updateClient(int index, String idType, String name, 
			int phone, String address) throws IOException {
		clients.get(index).setIdType(idType);
		clients.get(index).setName(name);
		clients.get(index).setPhone(phone);
		clients.get(index).setAddress(address);
		
		saveClients();
	}
	
	/**
	 * Updates a product's data given its index.<br>
	 * <b>pre</b>The given index has to exist<br>
	 * <b>post</b><br>
	 * @param index The index of the product that is going to be updated.
	 * @param id ID
	 * @param name Name
	 * @param description Description
	 * @param price Price
	 * @param nit Restaurant's NIT
	 * @throws IOException 
	 */
	public void updateProduct(int index, String name, String description, 
			int price) throws IOException {
		products.get(index).setName(name);
		products.get(index).setDescription(description);
		products.get(index).setPrice(price);
		
		saveProducts();
	}
	
	/**
	 Updates an order's data given its index.<br>
	 * <b>pre</b>The given index has to exist. productIds and productAmount must
	 have the same size.<br>
	 * <b>post</b><br>
	 * @param index The index of the order that is going to be updated.
	 * @param clientId ID of the client that ordered
	 * @param nit NIT of the restaurant the client ordered at.
	 * @param productIds IDs of the products that will be bought.
	 * @param productAmount Amount of each product.
	 * @throws IOException 
	 */
	public void updateOrder(int index, int clientId, int nit, ArrayList<Integer> productIds,
			ArrayList<Integer> productAmount) throws IOException {
		orders.get(index).setClientId(clientId);
		orders.get(index).setNit(nit);
		orders.get(index).setProductIds(productIds);
		orders.get(index).setProductAmount(productAmount);
		
		saveOrders();
	}
	
	/**
	 * Looks for the client with the given name and returns it. Throws an exception if it isn't
	 found <br>
	 * @param name Full name of the client to be looked for.
	 * @return Client searched.
	 * @throws ClientNotFoundException Thrown if the client isn't found
	 */
	public Client searchClient(String name) throws ClientNotFoundException{
		int bot = 0;
		int top = clients.size()-1;
		int m = (top+bot)/2;
		boolean found = false;
		
		ClientNameComparator cnc = new ClientNameComparator();
		
		while(bot <= top && !found) {
			if(cnc.compare(name, clients.get(m).getName()) == 0) {
				found = true;
			}else if (cnc.compare(name, clients.get(m).getName()) > 0) {
				bot = m+1;
				m = (top+bot)/2;
			}else {
				top = m-1;
				m = (top+bot)/2;
			}
		}
		
		if(!found) {
			throw new ClientNotFoundException(name);
		}
		
		return clients.get(m);
	}
	
	/**
	 * Updates the status of a the order in the given index <br>
	 * <b>pre:</b> The given index must exist in orders<br>
	 * <b>post:</b><br>
	 * @param index Index of the order to be updated.
	 * @param newStatus New status.
	 * @throws InvalidStatusUpdateException Thrown if the new status is behind the current one.
	 * @throws IOException 
	 */
	public void updateOrderStatus(int index, OrderStatus newStatus) throws InvalidStatusUpdateException, IOException{
		orders.get(index).updateStatus(newStatus);
		
		saveOrders();
	}
	
	
	/**
	 * Saves the object restaurants in the folder data with the name Restaurants.rgs <br>
	 * @throws IOException
	 */
	public void saveRestaurants() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Restaurants.rgs"));
		oos.writeObject(restaurants);
		oos.close();
	}
	
	/**
	 * Saves the object products in the folder data with the name Products.rgs <br>
	 * @throws IOException
	 */
	public void saveProducts() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Products.rgs"));
		oos.writeObject(products);
		oos.close();
	}
	
	/**
	 * Saves the object clients in the folder data with the name Clients.rgs <br>
	 * @throws IOException
	 */
	public void saveClients() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Clients.rgs"));
		oos.writeObject(clients);
		oos.close();
	}
	
	/**
	 * Saves the object orders in the folder data with the name Orders.rgs <br>
	 * @throws IOException
	 */
	public void saveOrders() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Orders.rgs"));
		oos.writeObject(orders);
		oos.close();
	}
	
	/**
	 * Loads the object restaurants from the file data/Restaurants.rgs <br>
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void loadRestaurants() throws ClassNotFoundException, IOException, PersistenceException{
		File file = new File ("data/Restaurants.rgs");
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Restaurants.rgs"));
			restaurants = (LinkedList<Restaurant>)ois.readObject();
			ois.close();
		}else {
			throw new PersistenceException();
		}
	}
	
	/**
	 * Loads the object clients from the file data/Restaurants.rgs <br>
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void loadClients() throws ClassNotFoundException, IOException, PersistenceException{
		File file = new File ("data/Clients.rgs");
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Clients.rgs"));
			clients = (LinkedList<Client>)ois.readObject();
			ois.close();
		}else {
			throw new PersistenceException();
		}
	}
	
	/**
	 * Loads the object products from the file data/Products.rgs <br>
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void loadProducts() throws ClassNotFoundException, IOException, PersistenceException{
		File file = new File ("data/Products.rgs");
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Products.rgs"));
			products = (LinkedList<Product>)ois.readObject();
			ois.close();
		}else {
			throw new PersistenceException();
		}
	}
	
	/**
	 * Loads the object orders from the file data/Orders.rgs <br>
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void loadOrders() throws ClassNotFoundException, IOException, PersistenceException{
		File file = new File ("data/Orders.rgs");
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Orders.rgs"));
			orders = (LinkedList<Order>)ois.readObject();
			ois.close();
		}else {
			throw new PersistenceException();
		}
	}
	
	/**
	 * Sorts restaurants by name using bubble sort.
	 * <b>pre:</b>
	 * <b>post:</b>
	 * @throws IOException 
	 */
	public void sortRestaurants() throws IOException {
		for(int i=0; i<restaurants.size()-1; i++){
			for(int j=0; j<restaurants.size()-(i+1); j++){
		        if(restaurants.get(j).getName().compareTo(restaurants.get(j+1).getName()) > 0){
		          Collections.swap(restaurants, j, j+1);
		        }
		    }
		}

		saveRestaurants();
	}
	
	
	/**
	 * Returns an array with all clients sorted in descending order by phone number. 
	 * Clients are sorted with the Arrays sort and the ClientPhoneComparator.<br>
	 * @return array
	 */
	public Client[] getSortedClientArray(){
		Client[] array = new Client[clients.size()];
		
		for(int i=0; i<array.length; i++) {
			array[i] = clients.get(i);
		}
		
		Arrays.sort(array, new ClientPhoneComparator());
		
		return array;
	}
	
	/**
	 * Creates a file with data from orders. The data
	 * is sorted prior to the creation of the file. <br>
	 * <b>pre</b> The separator must not be a letter or a number <br>
	 * <b>post</b> The file will be located in data/Orders.csv <br>
	 * @param separator The separator that will be used in the .csv file.
	 * @throws FileNotFoundException Thrown if the file can't be created.
	 */
	public void exportOrders(String separator) throws FileNotFoundException, InterruptedException{
		Collections.sort(orders);
		for(int i=0; i<orders.size(); i++) {
			orders.get(i).sortProducts();
		}
		
		PrintWriter pw = new PrintWriter("data/Orders.csv");
		
		pw.println("OrderID"+separator+"RestaurantID"+separator+"ClientID"+
				separator+"ProductID"+separator+"ProductAmount");
		
		for(int i=0; i<orders.size(); i++) {
			for(int j=0; j<orders.get(i).getProductIds().size(); j++) {
				pw.println(orders.get(i).getId() + separator + 
						orders.get(i).getNit() + separator +
						orders.get(i).getClientId() + separator +
						orders.get(i).getProductIds().get(j) + separator + 
						orders.get(i).getProductAmount().get(j));
			}
		}
		
		pw.close();
	}
	
	/**
	 * Imports restaurants from the given file. First line will not be read. Splitter is ";"<br>
	 * <b>pre:</b> Data must have the right format.<br>
	 * <b>pre:</b><br>
	 * @param filename Name of the file read.
	 * @throws FileNotFoundException Thrown if the file isn't found.
	 * @throws IOException
	 */
	public void importRestaurants(String filename) throws FileNotFoundException, IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		String line = br.readLine();
		line = br.readLine();
		
		while(line != null) {
			String[] parts = line.split(";");
			
			int nit = Integer.parseInt(parts[0]);
			String name = parts[1];
			String adminName = parts[2];
			
			addRestaurant(nit, name, adminName);
			line = br.readLine();
		}
		
		br.close();
		saveRestaurants();
	}
	
	
	public void importClients(String filename) throws FileNotFoundException, IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		String line = br.readLine();
		line = br.readLine();
		
		while(line != null) {
			String[] parts = line.split(";");
			
			String idType = parts[0];
			int id = Integer.parseInt(parts[1]);
			String name = parts[2];
			int phone = Integer.parseInt(parts[3]);
			String address = parts[4];
			
			addClient(idType, id, name, phone, address);
			line = br.readLine();
		}
		
		br.close();
		saveClients();
	}
	
	/**
	 * Imports products from the given file. First line will not be read. Splitter is ";"<br>
	 * <b>pre:</b> Data must have the right format.<br>
	 * <b>pre:</b><br>
	 * @param filename Name of the file read.
	 * @throws FileNotFoundException Thrown if the file isn't found.
	 * @throws IOException
	 */
	public void importProducts(String filename) throws FileNotFoundException, IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		String line = br.readLine();
		line = br.readLine();
		
		while(line != null) {
			String[] parts = line.split(";");
			
			int id = Integer.parseInt(parts[0]);
			String name = parts[1];
			String description = parts[2];
			int price = Integer.parseInt(parts[3]);
			int nit = Integer.parseInt(parts[4]);
			
			addProduct(id, name, description, price, nit);
			line = br.readLine();
		}
		
		br.close();
		saveProducts();
	}
	
	/**
	 * Imports orders from the given file. First line will not be read. Splitter is ";"<br>
	 * <b>pre:</b> Data must have the right format, as exported with exportOrders()<br>
	 * <b>pre:</b><br>
	 * @param filename Name of the file read.
	 * @throws FileNotFoundException Thrown if the file isn't found.
	 * @throws IOException
	 */
	public void importOrders(String filename) throws FileNotFoundException, IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		String line = br.readLine();
		line = br.readLine();
		ArrayList<Integer> productIds = new ArrayList<Integer>();
		ArrayList<Integer> productAmount = new ArrayList<Integer>();
		
		while(line != null) {
			String[] parts = line.split(";");
			String id = parts[0];
			int nit = Integer.parseInt(parts[1]);
			int clientId = Integer.parseInt(parts[2]);
			productIds.add(Integer.parseInt(parts[3]));
			productIds.add(Integer.parseInt(parts[4]));
			
			if(br.readLine() != id) {
				addOrder(id, nit, clientId, productIds, productAmount);
				productIds.clear();
				productAmount.clear();
			}
		}
		
		br.close();
		saveOrders();
	}
}
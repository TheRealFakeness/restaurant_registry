package ui;

import model.Client;
import model.Sys;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import exceptions.ClientNotFoundException;
import exceptions.IDNotFoundException;
import exceptions.OrderNotFoundException;

public class Menu {
	private Sys sys;
	private final static int EXIT = 0;
	boolean exit;
	Scanner sc = new Scanner(System.in);
	
	public Menu() {
		sys = new Sys();
		exit = false;
	}
	
	public void start() {
		try {
			sys.loadRestaurants();
			sys.loadProducts();
			sys.loadClients();
			sys.loadProducts();
		}catch (Exception e) {
			// Some files not found
		}
	}
	
	/**
	 * Displays a menu with the options specified in the requirements.
	 */
	public void menu() {
		while(!exit) {
			System.out.println("\nSelect an option:"
					+ "\n1. Register something new."
					+ "\n2. Update something."
					+ "\n3. Display something"
					+ "\n4. Import something"
					+ "\n5. Export orders"
					+ "\n6. Search for a client"
					+ "\n0. Exit");
			
			switch(sc.nextInt()) {
			case 1:
				System.out.println("\nSelect an option:"
						+ "\n1. Register restaurants."
						+ "\n2. Register clients"
						+ "\n3. Register products"
						+ "\n4. Register orders");
				
				switch(sc.nextInt()) {
				case 1:
					sc.nextLine();
					registerRestaurant();
					break;
					
				case 2:
					sc.nextLine();
					registerClient();
					break;
					
				case 3:
					sc.nextLine();
					registerProduct();
					break;
					
				case 4:
					sc.nextLine();
					registerOrder();
					break;
					
				default:
					System.err.println("Invalid option.");
					break;
				}
				break;
				
			case 2:
				System.out.println("\nSelect an option:"
						+ "\n1. Update restaurants."
						+ "\n2. Update clients"
						+ "\n3. Update products"
						+ "\n4. Update orders");
				
				switch(sc.nextInt()) {
				case 1:
					updateRestaurant();
					break;
					
				case 2:
					updateClient();
					break;
					
				case 3:
					updateProduct();
					break;
					
				case 4:
					updateOrder();
					break;
					
				default:
					System.err.println("Invalid option.");
					break;
				}
				
				break;
				
			case 3:
				System.out.println("\nSelect an option:"
						+ "\n1. Display restaurants."
						+ "\n2. Display clients");
				
				switch(sc.nextInt()) {
				case 1:
					displayRestaurants();
					break;
					
				case 2:
					displayClients();
					break;
					
				default:
					System.err.println("Invalid option.");
					break;
				}
				break;
				
			case 4:
				System.out.println("\nSelect an option:"
						+ "\n1. Import restaurants."
						+ "\n2. Import clients"
						+ "\n3. Import products"
						+ "\n4. Import orders");
				
				switch(sc.nextInt()) {
				case 1:
					importRestaurants();
					break;
					
				case 2:
					importClients();
					break;
					
				case 3:
					importProducts();
					break;
					
				case 4:
					importOrders();
					break;
					
				default:
					System.err.println("Invalid option.");
					break;
				
				}
				break;
				
			case 5:
				exportOrders();
				break;
				
			case 6:
				searchClient();
				break;
				
			case EXIT:
				exit = true;
				break;
				
			default:
				System.err.println("Invalid option.");
				break;
			}
		}
		sc.close();
	}
	
	/**
	 * Registers a new restaurant through user input.
	 */
	public void registerRestaurant() { 
		boolean exit = false;
		
		while(!exit) {
			System.out.print("\nEnter restaurant's name: ");
			String name = sc.nextLine();
			
			System.out.print("\nEnter restaurant's administrator's name: ");
			String adminName = sc.nextLine();
			
			System.out.print("\nEnter restaurant's NIT: ");
			int nit = sc.nextInt();
			sc.nextLine();
			
			try {
				sys.addRestaurant(nit, name, adminName);
			}catch (IOException ioe) {
				System.err.println(ioe.getMessage());
			}
			
			String exitOption = "";
			
			System.out.print("\nDo you want to register another restaurant? (Y/N): ");
			while(!exitOption.equals("Y") && !exitOption.equals("N")) {
				exitOption = sc.nextLine();
			}
			
			if(exitOption.equals("N")) {
				exit = true;
			}
		}
	}
	
	/**
	 * Registers a new client through user input.
	 */
	public void registerClient() {
		boolean exit = false;
		
		while(!exit) {
			System.out.print("\nEnter client's ID type: ");
			String idType = sc.nextLine();
			
			System.out.print("\nEnter client's ID: ");
			int id = sc.nextInt();
			sc.nextLine();
			
			System.out.print("\nEnter client's name: ");
			String name = sc.nextLine();
			
			System.out.print("\nEnter client's phone number: ");
			int phone = sc.nextInt();
			sc.nextLine();
			
			System.out.print("\nEnter client's address: ");
			String address = sc.nextLine();
			
			try {
				sys.addClient(idType, id, name, phone, address);
			}catch (IOException ioe) {
				System.err.println(ioe.getMessage());
			}
			
			String exitOption = "";
			
			System.out.print("\nDo you want to register another client? (Y/N): ");
			while(!exitOption.equalsIgnoreCase("Y") && !exitOption.equalsIgnoreCase("N")) {
				exitOption = sc.nextLine();
			}
			
			if(exitOption.equals("N")) {
				exit = true;
			}
		}
	}
	
	/**
	 * Registers a new product through user input.
	 */
	public void registerProduct() { 
		boolean exit = false;
		
		while(!exit) {
			System.out.print("\nEnter product's ID: ");
			int id = sc.nextInt();
			sc.nextLine();
			
			System.out.print("\nEnter product's name: ");
			String name = sc.nextLine();
			
			System.out.print("\nEnter product's description: ");
			String description = sc.nextLine();
			
			System.out.print("\nEnter product's price: ");
			int price = sc.nextInt();
			sc.nextLine();
			
			System.out.print("\nEnter the NIT of the restaurant the product"
					+ "belongs to: ");
			int nit = sc.nextInt();
			sc.nextLine();
			
			try {
				sys.findRestaurant(nit);
				sys.addProduct(id, name, description, price, nit);
			}catch (IOException ioe) {
				System.err.println(ioe.getMessage());
			}catch (IDNotFoundException infe){
				System.err.println(infe.getMessage());
				System.err.println("Product was not added");
			}
			
			String exitOption = "";
			
			System.out.print("\nDo you want to register another product? (Y/N): ");
			while(!exitOption.equalsIgnoreCase("Y") && !exitOption.equalsIgnoreCase("N")) {
				exitOption = sc.nextLine();
			}
			
			if(exitOption.equalsIgnoreCase("N")) {
				exit = true;
			}
		}
	}
	
	/**
	 * Registers a new order through user input.
	 */
	public void registerOrder() { 
		boolean exit = false;
		
		while(!exit) {
			System.out.print("\nEnter the client's ID: ");
			int clientId = sc.nextInt();
			sc.nextLine();
			
			System.out.print("\nEnter the restaurant's NIT: ");
			int nit = sc.nextInt();
			sc.nextLine();
			
			ArrayList<Integer> productIds = new ArrayList<Integer>();
			ArrayList<Integer> productAmount = new ArrayList<Integer>();
			
			boolean noMoreProducts = false;
			while(!noMoreProducts) {
				
				try {
					System.out.print("\nEnter the product's ID: ");
					boolean belongsToRest = false;
					int productId = 0;
					while(!belongsToRest) {
						productId = sc.nextInt();
						sc.nextLine();
						int productIndex = sys.findProduct(productId);
						
						if(sys.getProducts().get(productIndex).getNit() == nit) {
							belongsToRest = true;
						}
					}
					productIds.add(productId);
					
					System.out.print("\nEnter the product's amount: ");
					productAmount.add(sc.nextInt());
				}catch(IDNotFoundException infe) {
					System.err.println("The product ID wasn't found");
				}
				
				String exitOption = "";
				
				System.out.print("\nDo you want to register another order? (Y/N): ");
				while(!exitOption.equalsIgnoreCase("Y") && !exitOption.equalsIgnoreCase("N")) {
					exitOption = sc.nextLine();
				}
				
				if(exitOption.equalsIgnoreCase("N")) {
					noMoreProducts = true;
				}
			}
			
			try {
				sys.findRestaurant(nit);
				sys.findClient(clientId);
				sys.addOrder(clientId, nit, productIds, productAmount);
			}catch (IOException ioe) {
				System.err.println(ioe.getMessage());
			}catch (IDNotFoundException infe){
				System.err.println(infe.getMessage());
				System.err.println("Order was not added as either restaurant"
						+ "or client weren't found");
			}
			
			String exitOption = "";
			
			while(!exitOption.equalsIgnoreCase("Y") && !exitOption.equalsIgnoreCase("N")) {
				System.out.print("\nDo you want to register another order? (Y/N): ");
				exitOption = sc.nextLine();
			}
			
			if(exitOption.equalsIgnoreCase("N")) {
				exit = true;
			}
		}
	}
	
	/**
	 * Updates the data of a restaurant from user input. The restaurant is found
	 * through its NIT.
	 */
	public void updateRestaurant() {
		int restaurantIndex = 0;
		try {
			System.out.print("\nEnter restaurant's NIT: ");
			restaurantIndex = sys.findRestaurant(sc.nextInt());
			sc.nextLine();
			System.out.print("\nEnter new name (Leave blank for it to remain the same.");
			String name = sc.nextLine();
			
			if(name != ""){
				sys.getRestaurants().get(restaurantIndex).setName(name);
			}
			
			System.out.print("\nEnter new admin name (Leave blank for it to remain the same.");
			String adminName = sc.nextLine();
			
			if(name != ""){
				sys.getRestaurants().get(restaurantIndex).setAdminName(adminName);
			}
		}catch(IDNotFoundException infe) {
			System.err.println(infe.getMessage());
		}
		
	}
	
	/**
	 * Updates the data of a client from user input. The client is found
	 * through their ID.
	 */
	public void updateClient() {
		int clientIndex = 0;
		try {
			System.out.print("\nEnter client's ID: ");
			clientIndex = sys.findClient(sc.nextInt());
			sc.nextLine();
			
			System.out.print("\nEnter new ID Type (Leave blank for it to remain the same.");
			String idType = sc.nextLine();
			
			if(idType != ""){
				sys.getClients().get(clientIndex).setIdType(idType);;
			}
			
			System.out.print("\nEnter new name (Leave blank for it to remain the same.");
			String name = sc.nextLine();
			
			if(name != ""){
				sys.getClients().get(clientIndex).setName(name);
			}
			
			System.out.print("\nEnter new phone number (Leave blank for it to remain the same.");
			int phone = sc.nextInt();
			sc.nextLine();
			
			if(name != ""){
				sys.getClients().get(clientIndex).setPhone(phone);
			}
			
			System.out.print("\nEnter new address (Leave blank for it to remain the same.");
			String address = sc.nextLine();
			
			if(address != ""){
				sys.getClients().get(clientIndex).setAddress(address);
			}
		}catch(IDNotFoundException infe) {
			System.err.println(infe.getMessage());
		}
	}
	
	/**
	 * Updates the data of a product from user input. The product is found
	 * through its ID.
	 */
	public void updateProduct() {
		int productIndex = 0;
		try {
			System.out.print("\nEnter product's ID: ");
			productIndex = sys.findProduct(sc.nextInt());
			
			System.out.print("\nEnter new name (Leave blank for it to remain the same.");
			String name = sc.nextLine();
			
			if(name != ""){
				sys.getProducts().get(productIndex).setName(name);;
			}
			
			System.out.print("\nEnter new description (Leave blank for it to remain the same.");
			String description = sc.nextLine();
			
			if(name != ""){
				sys.getProducts().get(productIndex).setDescription(description);
			}
			
			System.out.print("\nEnter new price (Leave blank for it to remain the same.");
			int price = sc.nextInt();
			sc.nextLine();
			
			if(name != ""){
				sys.getProducts().get(productIndex).setPrice(price);
			}
		}catch(IDNotFoundException infe) {
			System.err.println(infe.getMessage());
		}
		
	}
	
	/**
	 * Updates the data of an order from user input. The order is found
	 * through its ID.
	 */
	public void updateOrder() {
		int orderIndex = 0;
		try {
			System.out.print("\nEnter order's ID: ");
			orderIndex = sys.findOrder(sc.nextLine());
			
			System.out.print("\nDo you want to remove products?"
					+ "\n0. No"
					+ "\n1. Yes");
			int option = -1;
			while (option != 0 && option != 1) {
				option = sc.nextInt();
				sc.nextLine();
			}
			
			if(option == 1) {
				boolean keepRemoving = true;
				while (keepRemoving) {
					option = -1;
					for(int i=0; i<sys.getOrders().get(orderIndex).getProductIds().size();
							i++) {
						System.out.print("\n"+(i+1) + ". " +  sys.getOrders().get(orderIndex).getProductIds().get(i));
					}
					
					System.out.print("\nSelect product to delete: ");
					int delete = -1;
					while(delete < 0 || delete > (sys.getOrders().get(orderIndex).getProductIds().size() + 1)) {
						delete = sc.nextInt() - 1;
					}
					sys.getOrders().get(orderIndex).getProductIds().remove(delete);
					sys.getOrders().get(orderIndex).getProductAmount().remove(delete);
				}
				
				System.out.print("\nKeep removing? (Y/N)");
				String optionS = "";
				while(optionS.equalsIgnoreCase("Y") && optionS.equalsIgnoreCase("N")) {
					optionS = sc.nextLine();
					sc.nextLine();
				}
				
				if(optionS.equalsIgnoreCase("N")) {
					keepRemoving = false;
				}
				
				System.out.print("\nDo you want to add products?"
						+ "\n0. No"
						+ "\n1. Yes");
				option = -1;
				while (option != 0 && option != 1) {
					option = sc.nextInt();
					sc.nextLine();
				}
				
				if(option == 1) {
					ArrayList<Integer> productIds = new ArrayList<Integer>();
					ArrayList<Integer> productAmount = new ArrayList<Integer>();
					
					boolean noMoreProducts = false;
					while(!noMoreProducts) {
						try {
							System.out.print("\nEnter the product's ID: ");
							boolean belongsToRest = false;
							int productId = 0;
							while(!belongsToRest) {
								productId = sc.nextInt();
								sc.nextLine();
								int productIndex = sys.findProduct(productId);
								
								if(sys.getProducts().get(productIndex).getNit() == sys.getOrders().get(orderIndex).getNit()) {
									belongsToRest = true;
								}
							}
							productIds.add(productId);
							
							System.out.print("\nEnter the product's amount: ");
							productAmount.add(sc.nextInt());
							sc.nextLine();
						}catch(IDNotFoundException infe) {
							System.err.println("The product ID wasn't found");
						}
						
						String exitOption = "";
						
						while(!exitOption.equalsIgnoreCase("Y") && !exitOption.equalsIgnoreCase("N")) {
							System.out.print("\nDo you want to register another product? (Y/N): ");
							exitOption = sc.nextLine();
						}
						
						if(exitOption.equalsIgnoreCase("N")) {
							noMoreProducts = true;
						}
					}
					
					sys.getOrders().get(orderIndex).getProductIds().addAll(productIds);
					sys.getOrders().get(orderIndex).getProductAmount().addAll(productAmount);
				}
			}
		}catch(OrderNotFoundException onfe) {
			System.err.println(onfe.getMessage());
		}
	}
	
	/**
	 * Displays the info of all restaurants.
	 */
	public void displayRestaurants() {
		try {
			sys.sortRestaurants();
		}catch(IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		
		for(int i = 0; i < sys.getRestaurants().size(); i++) {
			System.out.println(sys.getRestaurants().toString()  + "\n");
		}
	}
	
	/**
	 * Displays the info of all clients.
	 */
	public void displayClients() {
		Client clients[] = sys.getSortedClientArray();
		
		for(int i = 0; i < clients.length; i++) {
			System.out.println(clients[i].toString() + "\n");
		}
	}
	
	/**
	 * Imports restaurants from the specified file.
	 */
	public void importRestaurants() {
		System.out.print("\nEnter file name: ");
		String filename = sc.nextLine();
		try {
			sys.importRestaurants(filename);
		}catch(FileNotFoundException fnfe) {
			System.err.println("File was not found");
		}catch(IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}
	
	/**
	 * Imports clients from the specified file.
	 */
	public void importClients() {
		System.out.print("\nEnter file name: ");
		String filename = sc.nextLine();
		try {
			sys.importClients(filename);
		}catch(FileNotFoundException fnfe) {
			System.err.println("File was not found");
		}catch(IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}
	
	/**
	 * Imports products from the specified file.
	 */
	public void importProducts() {
		System.out.print("\nEnter file name: ");
		String filename = sc.nextLine();
		try {
			sys.importProducts(filename);
		}catch(FileNotFoundException fnfe) {
			System.err.println("File was not found");
		}catch(IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}
	
	/**
	 * Imports orders from the specified file.
	 */
	public void importOrders() {
		System.out.print("\nEnter file name: ");
		String filename = sc.nextLine();
		try {
			sys.importOrders(filename);
		}catch(FileNotFoundException fnfe) {
			System.err.println("File was not found");
		}catch(IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}
	
	/**
	 * Exports order to the route /data/Orders.csv
	 */
	public void exportOrders() {
		System.out.print("\nEnter separator (non alphanumeric): ");
		char separator = 0;
		boolean validSeparator = false;
		
		while(!validSeparator) {
			separator = sc.nextLine().charAt(0);
			String s = String.valueOf(separator);
			if(s != null && s.matches("^[a-zA-Z0-9]*$")) {
				System.out.print("\nSeparator can't be alphanumeric.");
			}else {
				validSeparator = true;
			}
		}
		
		try {
			sys.exportOrders(separator);
		}catch(InterruptedException ie) {
			System.err.println(ie.getMessage());
		}catch(FileNotFoundException fnfe) {
			System.err.println(fnfe.getMessage());
		}
	}
	
	/**
	 * Looks for and displays a client using binary search.
	 */
	public void searchClient() {
		Calendar cal = Calendar.getInstance();
		System.out.println("\nEnter client's full name:");
		sc.nextLine();
		String name = sc.nextLine();
		
		long start = cal.getTimeInMillis();
		try {
			Client c = sys.searchClient(name);
			System.out.println(c.toString());
		}catch(ClientNotFoundException cnfe) {
			System.err.println(cnfe.getMessage());
		}
		long end = cal.getTimeInMillis();
		System.out.println("\nSearch time:" + (end-start) + "ms");
	}
}
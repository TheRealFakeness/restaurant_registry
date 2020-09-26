package ui;

import model.Sys;
import java.util.Scanner;

public class Menu {
	private Sys sys;
	private final static int EXIT = 0;
	boolean exit;
	
	public Menu() {
		sys = new Sys();
		exit = false;
	}
	
	public void menu() {
		try {
			sys.loadRestaurants();
			sys.loadProducts();
			sys.loadClients();
			sys.loadProducts();
		}catch (Exception e) {
			System.err.println("Some files weren't found for loading.");
		}
		
		Scanner sc = new Scanner(System.in);
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
					registerRestaurant();
					break;
					
				case 2:
					registerClient();
					break;
					
				case 3:
					registerProduct();
					break;
					
				case 4:
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
}
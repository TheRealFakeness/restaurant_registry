package ui;

import model.Sys;

public class Menu {
	Sys sys;
	
	public Menu() {
		sys = new Sys();
	}
	
	public void menu() {
		try {
			sys.loadRestaurants();
			sys.loadProducts();
			sys.loadClients();
			sys.loadProducts();
		}catch (Exception e) {
			//
		}
		
		
	}
}
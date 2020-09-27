package model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import exceptions.ClientNotFoundException;

public class SysTest {
	private LinkedList<Restaurant> targetRestaurants;
	private Sys sys;
	
	public void setupStage1() {
		sys = new Sys();
		targetRestaurants = new LinkedList<Restaurant>();
		
		sys.getRestaurants().add(new Restaurant(1234, "Bca", "Inky"));
		sys.getRestaurants().add(new Restaurant(1235, "Abc", "Blinky"));
		sys.getRestaurants().add(new Restaurant(1236, "Bac", "Pinky"));
		sys.getRestaurants().add(new Restaurant(1237, "Aaz", "Clyde"));
		
		targetRestaurants.add(new Restaurant(1237, "Aaz", "Clyde"));
		targetRestaurants.add(new Restaurant(1235, "Abc", "Blinky"));
		targetRestaurants.add(new Restaurant(1236, "Bac", "Pinky"));
		targetRestaurants.add(new Restaurant(1234, "Bca", "Inky"));
	}
	
	public void setupStage2() throws InterruptedException, IOException{
		sys = new Sys();
		ArrayList<Integer> productIds = new ArrayList<Integer>();
		ArrayList<Integer> productAmount = new ArrayList<Integer>();
		
		productIds.add(123);
		productIds.add(124);
		productIds.add(125);
		productAmount.add(2);
		productAmount.add(3);
		productAmount.add(4);
		
		sys.addOrder(1234, 1234, productIds, productAmount);
		TimeUnit.MILLISECONDS.sleep(1);
		sys.addOrder(1235, 1235, productIds, productAmount);
		TimeUnit.MILLISECONDS.sleep(1);
		sys.addOrder(1236, 1236, productIds, productAmount);
	}
	
	public void setupStage3() throws IOException{
		sys = new Sys();
		sys.addClient("Cedula", 3049123, "Jolyne Cujoh", 4123951, "Green Dolphin Street 7331");
		sys.addClient("Tarjeta de identidad", 4562094, "Tom Hanks", 3984246, "Elmore Street 19");
		sys.addClient("Cedula", 3049123, "Joseph Joestar Joestar", 4123951, "Green Dolphin Street 7331");
		sys.addClient("Tarjeta de identidad", 4562094, "Jonathan Joestar", 3984246, "Elmore Street 19");
	}
	
	@Test
	public void testSortRestaurants() throws IOException {
		setupStage1();
		
		sys.sortRestaurants();

		for(int i=0; i<sys.getRestaurants().size(); i++) {
			assertEquals(sys.getRestaurants().get(i).getName(), 
					targetRestaurants.get(i).getName());
		}
	}
	
	@Test
	public void testExportOrders() throws InterruptedException, IOException{
		setupStage2();
		
		sys.exportOrders(';');
		
		if(new File("data/orders.csv").exists() == false) {
			fail("File wasn't created.");
		}
	}
	
	@Test
	public void testSearchClient() throws IOException, ClientNotFoundException{
		setupStage3();
		
		assertEquals(sys.searchClient("Tom Hanks").getName(), "Tom Hanks");
		assertEquals(sys.searchClient("Jonathan Joestar").getName(), "Jonathan Joestar");
		assertEquals(sys.searchClient("Joseph Joestar Joestar").getName(), "Joseph Joestar Joestar");
		
		try {
			sys.searchClient("Wes Bluemarine");
			fail("Should have thrown ClientNotFoundException");
		}catch(ClientNotFoundException cnfe) {
			// Failed successfully!!
		}
	}
}
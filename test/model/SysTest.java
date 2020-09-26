package model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

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
		
		sys.exportOrders(";");
		
		if(new File("data/orders.csv").exists() == false) {
			fail("File wasn't created.");
		}
	}
}
package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ClientTest {
	private Client client1;
	private Client client2;
	
	public void setupStage1() {
		client1 = new Client("Cedula", 3049123, "Jolyne Cujoh", 4123951, "Green Dolphin Street 7331");
		client2 = new Client("Tarjeta de identidad", 4562094, "Tom Hanks", 3984246, "Elmore Street 19");
	}
	
	public void setupStage2() {
		client1 = new Client("Cedula", 3049123, "Joseph Joestar Joestar", 4123951, "Green Dolphin Street 7331");
		client2 = new Client("Tarjeta de identidad", 4562094, "Jonathan Joestar", 3984246, "Elmore Street 19");
	}
	
	@Test
	public void testCompareTo1() {
		setupStage1();
		boolean negative = false;
		
		if(client1.compareTo(client2) < 0) {
			negative = true;
		}
		
		assertTrue(negative, "Value returned is not negative");
	}
	
	@Test
	public void testCompareTo() {
		setupStage2();
		boolean positive = false;
		
		if(client1.compareTo(client2) > 0) {
			positive = true;
		}
		
		assertTrue(positive, "Value returned is not positive");
	}
}
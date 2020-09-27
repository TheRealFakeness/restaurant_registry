package comparators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CNCTest {
	private ClientNameComparator cnc;
	private String name1;
	private String name2;
	private String name3;
	private String name4;
	
	public void setupStage1() {
		cnc = new ClientNameComparator();
		name1 = "Jolyne Cujoh";
		name2 = "Tom Hanks";
		name3 = "Joseph Joestar Joestar";
		name4 = "Jonathan Joestar";
	}
	
	@Test
	void test1() {
		setupStage1();
		
		boolean right = false;
		
		if(cnc.compare(name1, name2) < 0) {
			right = true;
		}
		assertTrue(right);
		right = false;
		
		if(cnc.compare(name2, name1) > 0) {
			right = true;
		}
		assertTrue(right);
		right = true;
		right = false;
		
		if(cnc.compare(name3, name2) > 0) {
			right = true;
		}
		assertTrue(right);
		right = true;
		right = false;
		
		if(cnc.compare(name4, name3) < 0) {
			right = true;
		}
		assertTrue(right);
		right = true;
		right = false;
	}
}

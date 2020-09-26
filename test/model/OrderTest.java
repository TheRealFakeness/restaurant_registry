package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import exceptions.InvalidStatusUpdateException;

class OrderTest {
	private Order order;
	
	public void setupStage1(){
		order = new Order(123456, 123456, null, null);
	}
	
	@Test
	public void updateStatusTest1() {
		setupStage1();
		try {
			order.updateStatus(OrderStatus.S);
		}catch(InvalidStatusUpdateException isue) {
			System.out.print(isue.getMessage());
		}
		
		try {
			order.updateStatus(OrderStatus.R);
			fail("ISUE not thrown");
		}catch(InvalidStatusUpdateException isue) {
			System.out.print(isue.getMessage());
		}
	}
	
	@Test
	public void updateStatusTest2(){
		setupStage1();
		try {
			order.updateStatus(OrderStatus.D);
		}catch(InvalidStatusUpdateException isue) {
			System.out.print(isue.getMessage());
		}
		
		assertEquals(order.getOrderStatus(), OrderStatus.D);
	}
}

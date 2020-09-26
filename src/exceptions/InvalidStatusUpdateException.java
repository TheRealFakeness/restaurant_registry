package exceptions;

import model.OrderStatus;

public class InvalidStatusUpdateException extends Exception {
	private static final long serialVersionUID = 1;
	OrderStatus orderStatus;
	
	public InvalidStatusUpdateException(OrderStatus orderStatus){
		super("The status of the order can only be updated forwards.");
		this.orderStatus = orderStatus;
	}
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
}

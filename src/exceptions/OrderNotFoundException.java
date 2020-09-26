package exceptions;

public class OrderNotFoundException extends Exception{
	private static final long serialVersionUID = 1;
	private String id;
	
	public OrderNotFoundException(String id) {
		super("Given ID was not found.");
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
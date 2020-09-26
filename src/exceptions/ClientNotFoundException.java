package exceptions;

public class ClientNotFoundException extends Exception {
	private static final long serialVersionUID = 1;
	private String name;
	
	public ClientNotFoundException(String name) {
		super("Client with given name was not found.");
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}

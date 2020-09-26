package exceptions;

public class IDNotFoundException extends Exception{
	private static final long serialVersionUID = 1;
	private int id;
	
	public IDNotFoundException(int id) {
		super("Given ID was not found.");
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
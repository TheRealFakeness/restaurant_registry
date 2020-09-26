package exceptions;

public class PersistenceException extends Exception{
	static final long serialVersionUID = 1;
	
	public PersistenceException() {
		super("File not found.");
	}
}

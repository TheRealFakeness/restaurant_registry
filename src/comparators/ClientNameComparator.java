package comparators;

import java.util.Comparator;

public class ClientNameComparator implements Comparator<String> {
	
	/**
	 Compares clients' names, taking the last name as first criterion, and first name as second <br>
	 <b>pre</b> Both clients must have a named comprised of at least two names (only the first two will
	 affect the comparison <br>
	 <b>post</b> A positive integer will be returned if the first name is greater than the second
	 one, a negative integer if it is lesser and 0 if they're equal <br>
	 @param otherClient The client to be compared to <br>
	 */
	@Override
	public int compare(String name1, String name2) {
		String ln1 = name1.split(" ")[1];
		String ln2 = name2.split(" ")[1];
		
		int comp = 0;
		
		if(ln1.equals(ln2)) {
			comp = name1.compareTo(name2);
		}else {
			comp = ln1.compareTo(ln2);
		}
		
		return comp;
	}
}
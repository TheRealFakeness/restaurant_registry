package comparators;

import java.util.Comparator;
import model.Client;

public class ClientPhoneComparator implements Comparator<Client> {
	@Override
	public int compare(Client c1, Client c2) {
		return c2.getPhone() - c1.getPhone();
	}
}
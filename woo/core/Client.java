package woo.core;

import java.util.List;
import java.util.LinkedList;


enum ClientStatus {
  NORMAL, ELITE, SELECTION;
}


public class Client {
	private String _id;
	private String _name;
	private String _address;
	private ClientStatus _status;
	private int _points;
	private List<Notification> _notifications;
	private List<Transaction> _sales;

	public Client(String id, String name, String address) {
		_id = id;
		_name = name;
		_address = address;
		_status = ClientStatus.NORMAL;
		_points = 0;
		_notifications = new LinkedList<Notification>();
		_sales = new LinkedList<Transaction>();
	}

	public String getId() {
		return _id;
	}

	public List<Transaction> getTransactions() {
		return _sales;
	}

	public String toString() {
		int amount;
		int amountPaid;
		Sale s;

		for (Transaction t : _sales) {
			s = (Sale)t;
			amount += s.getCost();
			amountPaid += s.getPaid();
		}

		return String.join("|", _id, _name, _address,
			Integer.toString(amount), Integer.toString(amountPaid));
	}

	public int compareTo(Client other) {
		return _id.toUpperCase().compareTo(other.getId().toUpperCase());
	}
}
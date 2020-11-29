package woo.core;

import java.util.List;
import java.util.LinkedList;


enum ClientStatus {
  NORMAL, ELITE, SELECTION;
}


public class Client implements Observer {
	private String _id;
	private String _name;
	private String _address;
	private ClientStatus _status;
	private int _points;
	private List<Notification> _notifications;
	private List<Transaction> _transactions;

	public Client(String id, String name, String address) {
		_id = id;
		_name = name;
		_address = address;
		_status = ClientStatus.NORMAL;
		_points = 0;
		_notifications = new LinkedList<Notification>();
		_transactions = new LinkedList<Transaction>();
	}

	public String getId() {
		return _id;
	}

	public List<Transaction> getTransactions() {
		return _transactions;
	}

	public void addTransaction(Sale sale) {
		_transactions.add(sale);
	}

	public String toString() {
		int amount = 0;
		int amountPaid = 0;
		Sale s;

		for (Transaction t : _transactions) {
			s = (Sale)t;
			amount += s.getCost();
			amountPaid += s.getPaid();
		}

		return String.format("%s|%s|%s|%d|%d", _id, _name, _address, amount, amountPaid);
	}

	public int compareTo(Client other) {
		return _id.toUpperCase().compareTo(other.getId().toUpperCase());
	}

	public void notify(Notification notif) {
		_notifications.add(notif);
	}
}
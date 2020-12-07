package woo.core;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class Client implements Observer {
	/*
	private enum ClientStatus {
	  	NORMAL, ELITE, SELECTION
	}
	*/

	private String _id;
	private String _name;
	private String _address;
	private ClientStatus _status;
	//private int _points;
	private List<Notification> _notifs;
	private List<Transaction> _transactions;

	public Client(String id, String name, String address) {
		_id = id;
		_name = name;
		_address = address;
		_status = new NormalStatus(0);
		//_points = 0;
		_notifs = new ArrayList<Notification>();
		_transactions = new ArrayList<Transaction>();
	}

	public String getId() {
		return _id;
	}

	public List<Transaction> getTransactions() {
		return Collections.unmodifiableList(_transactions);
	}

	public List<Notification> getNotifications() {
		List<Notification> notifs = new ArrayList<Notification>();
		notifs.addAll(_notifs);
		_notifs.clear();
		return notifs;
	}

	void addTransaction(Sale sale) {
		_transactions.add(sale);
	}

	/*
	void addPoints(int points) {
		_points += points;

		if (_points > 25000)
			_status = new EliteStatus();
		else if (_points > 2000)
			_status = new SelectionStatus();
		else
			_status = new NormalStatus();
	}
	*/

	void setStatus(ClientStatus status) {
		_status = status;
	}

	public void notify(Notification notif) {
		_notifs.add(notif);
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
}
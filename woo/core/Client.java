package woo.core;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class Client implements Observer {

	/** Client key. */
	private String _id;

	/** Client's name. */
	private String _name;

	/** Client's address. */
	private String _address;

	/** Current status. */
	private ClientStatus _status;

	/** Received notifications. */
	private List<Notification> _notifs;

	/** Transaction history. */
	private List<Transaction> _transactions;


	public Client(String id, String name, String address) {
		_id = id;
		_name = name;
		_address = address;
		_status = new NormalStatus(this, 0);
		_notifs = new ArrayList<Notification>();
		_transactions = new ArrayList<Transaction>();
	}

	/**
	 * Gets the client's key.
	 * 
	 * @return client key
	 */
	public String getId() {
		return _id;
	}

	/**
	 * Gets the client's transaction history.
	 * 
	 * @return client's transactions
	 */
	public List<Transaction> getTransactions() {
		return Collections.unmodifiableList(_transactions);
	}

	/**
	 * Gets and clears the client's current notifications.
	 * 
	 * @return client's notifications
	 */
	public List<Notification> getNotifications() {
		List<Notification> notifs = new ArrayList<Notification>();
		notifs.addAll(_notifs);
		_notifs.clear();
		return notifs;
	}

	void addTransaction(Sale sale) {
		_transactions.add(sale);
	}

	public double getCurrentCost(Sale sale, int date) {
		int delay = date - sale.getDate();		
		return _status.getCurrentCost(sale, delay);
	}

	double pay(Sale sale) {
		return _status.pay(sale);
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
			amountPaid += s.getAmountPaid();
		}

		return String.format("%s|%s|%s|%d|%d", _id, _name, _address, amount, amountPaid);
	}

	public int compareTo(Client other) {
		return _id.toUpperCase().compareTo(other.getId().toUpperCase());
	}
}
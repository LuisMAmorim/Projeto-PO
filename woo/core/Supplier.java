package woo.core;

import java.util.List;
import java.util.LinkedList;


public class Supplier {
	private String _id;
	private String _name;
	private String _address;
	private boolean _enabled;
	private List<Product> _products;
	private List<Transaction> _orders;

	public Supplier(String id, String name, String address) {
		_id = id;
		_name = name;
		_address = address;
		_enabled = true;
		_products = new LinkedList<Product>();
		_orders = new LinkedList<Transaction>();
	}

	public boolean toggleActivation() {
		_enabled = !_enabled;
		return _enabled;
	}

	public String getId() {
		return _id;
	}

	public boolean isEnabled() {
		return _enabled;
	}

	public List<Transaction> getTransactions() {
		return _orders;
	}

	public String toString() {
		return String.join("|", _id, _name, _address);
	}

	public int compareTo(Supplier other) {
		return _id.toUpperCase().compareTo(other.getId().toUpperCase());
	}
}
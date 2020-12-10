package woo.core;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class Supplier {
	private String _id;
	private String _name;
	private String _address;
	private boolean _enabled;
	private List<Product> _products;
	private List<Transaction> _transactions;

	public Supplier(String id, String name, String address) {
		_id = id;
		_name = name;
		_address = address;
		_enabled = true;
		_products = new ArrayList<Product>();
		_transactions = new ArrayList<Transaction>();
	}

	public String getId() {
		return _id;
	}

	public boolean isEnabled() {
		return _enabled;
	}

	public List<Transaction> getTransactions() {
		return Collections.unmodifiableList(_transactions);
	}

	boolean toggleTransactions() {
		_enabled = !_enabled;
		return _enabled;
	}

	void addProduct(Product p) {
		_products.add(p);
	}

	public String toString() {
		return String.format("%s|%s|%s", _id, _name, _address);
	}

	public int compareTo(Supplier other) {
		return _id.toUpperCase().compareTo(other.getId().toUpperCase());
	}
}
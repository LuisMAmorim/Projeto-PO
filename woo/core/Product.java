package woo.core;

import java.util.List;
import java.util.LinkedList;


public abstract class Product {
	private int _price;
	private int _criticalValue;
	private int _currentQuantity;
	private String _id;
	private Supplier _supplier;
	private List<Client> _clientsMuted;

	public Product(String id, Supplier supplier, int price, int crit, int q) {
		_id = id;
		_price = price;
		_criticalValue = crit;
		_currentQuantity = q;
		_supplier = supplier;
		_clientsMuted = new LinkedList<Client>();
	}

	public String getId() {
		return _id;
	}

	public int getPrice() {
		return _price;
	}

	public int getCriticalValue() {
		return _criticalValue;
	}

	public int getCurrentQuantity() {
		return _currentQuantity;
	}

	public Supplier getSupplier() {
		return _supplier;
	}

	public int compareTo(Product other) {
		return _id.toUpperCase().compareTo(other.getId().toUpperCase());
	}

	public abstract String toString();
}
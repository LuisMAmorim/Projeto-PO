package woo.core;

import java.util.List;
import java.util.LinkedList;
import woo.core.exception.InvalidPriceException;

public abstract class Product {
	private String _id;
	private Supplier _supplier;
	private int _price;
	private int _criticalValue;
	private int _currentQuantity;
	private List<Observer> _observers;

	public Product(String id, Supplier supplier, int price, int crit, int q)
	throws InvalidPriceException {
		_id = id;
		_supplier = supplier;
		setPrice(price);
		_criticalValue = crit;
		_currentQuantity = q;
		_observers = new LinkedList<Observer>();
	}

	public String getId() {
		return _id;
	}

	/*
	public Supplier getSupplier() {
		return _supplier;
	}
	*/

	public int getPrice() {
		return _price;
	}

	public int getCriticalValue() {
		return _criticalValue;
	}

	public int getCurrentQuantity() {
		return _currentQuantity;
	}

	public void setPrice(int price) throws InvalidPriceException {
		if (price > 0)
			_price = price;
		else
			throw new InvalidPriceException();
	}

	public int compareTo(Product other) {
		return _id.toUpperCase().compareTo(other.getId().toUpperCase());
	}

	public final String toString() {
		return String.format("%s|%s|%s|%d|%d|%d|%s",
			getProductType(),
			_id,
			_supplier.getId(),
			_price,
			_criticalValue,
			_currentQuantity,
			getExtraInformation()
		);
	}

	public abstract String getProductType();

	public abstract String getExtraInformation();
}
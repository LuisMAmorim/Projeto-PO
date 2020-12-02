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

	public Product(String id, Supplier supplier, int price, int crit, int q) {
		_id = id;
		_supplier = supplier;
		_price = price;
		_criticalValue = crit;
		_currentQuantity = q;
		_observers = new LinkedList<Observer>();

		_supplier.addProduct(this);
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

	public void setPrice(int newPrice) throws InvalidPriceException {
		if (newPrice <= 0) throw new InvalidPriceException();

		int oldPrice = _price;
		_price = newPrice;

		if (newPrice < oldPrice) {
			Notification bargain = new Notification("BARGAIN", this);
			for (Observer o : _observers) o.notify(bargain);
		}
	}

	public void addObserver(Observer obs) {
		_observers.add(obs);
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
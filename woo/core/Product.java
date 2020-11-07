package woo.core;

public abstract class Product {
	private int _price;
	private int _criticalValue;
	private int _currentQuantity;
	private String _id;
	private Supplier _supplier;
	private List<Client> _clientsMuted;

	public Product(String id, int price, int crit, Supplier supplier) {
		_id = id;
		_price = price;
		_criticalValue = crit;
		_currentQuantity = 0;
		_supplier = supplier;
		_clientsMuted = new ArrayList<Client>;
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

	public abstract String toString()
}
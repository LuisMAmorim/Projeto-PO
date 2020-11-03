package woo.core;

public abstract class Product {
	private int _price;
	private int _criticalValue;
	private int _currentQuantity;
	private String _id;
	private Supplier _supplier;
	private List<Client> _clientsMuted;

	public Product(String id, int price, int crit, int q) {
		_id = id;
		_price = price;
		_criticalValue = crit;
		_currentQuantity = q;
		/* supplier? */
		_clientsMuted = ArrayList<Client>;
	}

	public String getId()

	public abstract String toString()
}
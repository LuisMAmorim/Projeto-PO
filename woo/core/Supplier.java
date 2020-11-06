package woo.core;

public class Supplier {
	private String _id;
	private String _name;
	private String _address;
	private boolean _enabled;
	private List<Product> _products;
	private List<Order> _orders;

	public boolean toggleActivation() {
		
	}

	public List<Transaction> getTransactions() {
		return _orders;
	}

	public String toString() {
		return _id + "|" + _name + "|" + _address + "|" + _enabled;
		/* enabled - yes() ou no() - a adicionar depois no comando */
	}
}
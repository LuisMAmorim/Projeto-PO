public abstract class Product {
	private int _price;
	private int _criticalValue;
	private int _currentQuantity;
	private String _id;
	private Supplier _supplier;
	private List<Client> _followers;

	public Product(String id, int price, int crit, int q) {

	}

	public abstract String toString()
}
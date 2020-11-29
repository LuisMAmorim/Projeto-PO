package woo.core;

public class Notification {
	private String _type;
	private Product _product;

	public Notification(String type, Product p) {
		_type = type;
		_product = p;
	}

	public String toString() {
		return String.format("%s|%s|%d", _type, _product.getId(), _product.getPrice());
	}
}
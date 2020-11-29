package woo.core;

public class Item {
	private int _quantity;
	private Product _product;

	public Item(Product product, int quantity) {
		_product = product;
		_quantity = quantity;
	}

	public int getPrice() {
		return _quantity * _product.getPrice();
	}

	public String toString() {
		return String.format("%s|%d", _product.getId(), _quantity);
	}
}
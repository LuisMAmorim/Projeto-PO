package woo.core;

public class Item {
	private int _quantity;
	private Product _product;

	public int getPrice() {
		return _quantity * _product.getPrice();
	}
}
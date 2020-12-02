package woo.core;

import woo.core.exception.NotEnoughException;

public class Item {
	private int _quantity;
	private Product _product;

	public Item(Product product, int quantity) {
		_product = product;
		_quantity = quantity;
	}

	public void addStock()  {
		_product.addStock(_quantity);
	}

	public void removeStock() throws NotEnoughException {
		_product.removeStock(_quantity);
	}

	public int getPrice() {
		return _quantity * _product.getPrice();
	}

	public String toString() {
		return String.format("%s|%d", _product.getId(), _quantity);
	}
}
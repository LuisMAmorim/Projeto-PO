package woo.core;

import woo.core.exception.NotEnoughStockException;

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

	void addStock()  {
		_product.addStock(_quantity);
	}

	void removeStock() throws NotEnoughStockException {
		_product.removeStock(_quantity);
	}

	public String toString() {
		return String.format("%s|%d", _product.getId(), _quantity);
	}
}
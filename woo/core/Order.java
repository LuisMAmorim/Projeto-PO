package woo.core;

import java.util.List;
import java.util.LinkedList;

public class Order extends Transaction {
	private Supplier _supplier;
	private List<Item> _items;

	public Order(int id, int date, Supplier supplier) {
		super(id, date);
		_supplier = supplier;
		_items = new LinkedList<Item>();
	}

	public void addItem(Item it) {
		_items.add(it);
		it.addStock();
		incCost(it.getPrice());
	}
	
	/*
	public String toString() {

	}
	*/
}
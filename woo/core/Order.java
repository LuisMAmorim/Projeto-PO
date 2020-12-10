package woo.core;

import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Order extends Transaction {
	private Supplier _supplier;
	private List<Item> _items;

	public Order(int id, int date, Supplier supplier) {
		super(id, date);
		_supplier = supplier;
		_items = new ArrayList<Item>();
	}

	void addItem(Item it) {
		_items.add(it);
		it.addStock();
		incCost(it.getPrice());
	}

	@Override
	public double getAvailableBalanceContribution(int date) {
		return -getCost();
	}

	@Override
	public double getAccountingBalanceContribution(int date) {
		return -getCost();
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder(String.format("%d|%s|%d|%d",
			getId(),
			_supplier.getId(),
			getCost(),
			getDate()
		));

		for (Item it : _items) {
			strBuilder.append("\n");
			strBuilder.append(it.toString());
		}

		return strBuilder.toString();
	}
}
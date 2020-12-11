package woo.core;

import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

import woo.core.exception.BadSupplierException;
import woo.core.exception.DisabledSupplierException;

public class Order extends Transaction {
	private Supplier _supplier;
	private List<Item> _items;
	private boolean _finished;

	public Order(int id, int date, Supplier supplier) {
		super(id, date);
		_supplier = supplier;
		_items = new ArrayList<Item>();
		_finished = false;
	}

	void addItem(Item it) throws BadSupplierException {
		if (!it.isSoldBy(_supplier))
			throw new BadSupplierException();

		if (!_finished) {
			_items.add(it);
			it.addStock();
			incCost(it.getPrice());
		}
	}

	void finish() throws DisabledSupplierException {
		_finished = true;
		_supplier.addTransaction(this);
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
	public String toString(String isPaid) {
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
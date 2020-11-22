package woo.core;

import java.util.List;
import java.util.LinkedList;

public class Order extends Transaction {
	private int _paymentDate;
	private int _amountPaid;
	private List<Item> _items;
	private Supplier _supplier;

	public Order(Supplier sup) {

	}

	public void addItem(Item it) {

	}
	
	/*
	public String toString() {

	}
	*/
}
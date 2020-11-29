package woo.core;

public abstract class Transaction {
	private int _cost;
	private int _id;
	private int _date;

	public Transaction(int id, int date) {
		_id = id;
		_date = date;
		_cost = 0;
	}

	public int getCost() {
		return _cost;
	}

	protected void incCost(int cost) {
		_cost += cost;
	}

	/*
	public abstract String toString();
	*/
}
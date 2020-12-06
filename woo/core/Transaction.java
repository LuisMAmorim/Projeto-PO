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

	public int getId() {
		return _id;
	}

	public int getDate() {
		return _date;
	}

	public int getCost() {
		return _cost;
	}

	void incCost(int cost) {
		_cost += cost;
	}

	public abstract String toString();
}
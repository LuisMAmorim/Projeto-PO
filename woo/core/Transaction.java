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

	public boolean getPaid() {
		return true;
	}

	public abstract double getAvailableBalanceContribution(int date);

	public abstract double getAccountingBalanceContribution(int date);

	void incCost(int cost) {
		_cost += cost;
	}

	void pay(int date) {}

	public abstract String toString(int date);
}
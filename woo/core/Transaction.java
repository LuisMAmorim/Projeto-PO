package woo.core;

public abstract class Transaction {
	private int _cost;
	private int _id;
	private int _date;

	public int getCost() {
		return _cost;
	}

	public abstract String toString();
}
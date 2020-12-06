package woo.core;

public class Sale extends Transaction {
	private int _paymentDate;
	private int _amountPaid;
	private Item _item;

	public Sale(int id, int date, Item item) {
		super(id, date);
		_item = item;
		incCost(_item.getPrice());
	}

	public void pay() {

	}

	public int getPaid() {
		return _amountPaid;
	}

	// ADD TOSTRING !!!!
	public String toString() {
		return "";
	}
}
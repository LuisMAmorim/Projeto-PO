package woo.core;

public class Sale extends Transaction {
	private int _paymentDate;
	private int _amountPaid;
	private Item _item;

	public void pay() {

	}

	public int getPaid() {
		return _amountPaid;
	}
}
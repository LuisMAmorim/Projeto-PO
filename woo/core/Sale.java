package woo.core;

public class Sale extends Transaction {
	private Client _client;
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

	// ADD TOSTRING !!!! UNFINISHED!!!!
	@Override
	public String toString() {
		return String.format("%d|%s|%s|%d|%d", 
			getId(),
			_client.getId(),
			_item.toString()
			);
	}
}
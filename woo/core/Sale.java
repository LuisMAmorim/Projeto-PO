package woo.core;

public class Sale extends Transaction {
	private Client _client;
	private int _paymentDate;
	private int _amountPaid;
	private Item _item;

	public Sale(int id, Client client, int date, Item item) {
		super(id, date);
		_client = client;
		_item = item;
		_isPaid = false;
		_amountPaid = 0;
		incCost(_item.getPrice());
	}

	public void pay() {
		if (_amountPaid = 0) {
			_isPaid = true;
		}
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
package woo.core;

public class Sale extends Transaction {
	private Client _client;
	private int _paymentDate;
	private double _amountPaid;
	private Item _item;
	private boolean _isPaid;

	public Sale(int id, Client client, int date, Item item) {
		super(id, date);
		_client = client;
		_item = item;
		_amountPaid = 0;
		_isPaid = false;
		_client.addTransaction(this);
		incCost(_item.getPrice());
	}

	@Override
	public void pay(int date) {
		if (!_isPaid) {
			_paymentDate = date;
			_amountPaid = _client.pay(this);
			_isPaid = true;
		}
	}

	public boolean getPaid() {
		return _isPaid;
	}

	@Override
	public double getAvailableBalanceContribution(int date) {
		return _amountPaid;
	}

	@Override
	public double getAccountingBalanceContribution(int date) {
		if (_isPaid)
			return _amountPaid;
		else
			return _client.getCurrentCost(this, date);
	}

	public double getAmountPaid() {
		return _amountPaid;
	}

	public int getPaymentDate() {
		return _paymentDate;
	}

	public int getDeadlineFactor() {
		return _item.getDeadlineFactor();
	}

	@Override
	public String toString() {
		return String.format("%d|%s|%s|%d|%d", 
			getId(),
			_client.getId(),
			_item.toString(),
			getCost(),
			_amountPaid
		);
	}
}
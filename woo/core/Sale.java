package woo.core;

import woo.core.exception.NotEnoughStockException;

public class Sale extends Transaction {
	private Client _client;
	private int _paymentDate;
	private double _amountPaid;
	private Item _item;
	private boolean _isPaid;

	public Sale(int id, Client client, int date, Item item) throws NotEnoughStockException {
		super(id, date);
		_client = client;
		_item = item;
		_amountPaid = 0;
		_isPaid = false;
		item.removeStock();
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

	@Override
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
	public String toString(int date) {
		String str = String.format("%d|%s|%s|%d|%d|%d", 
			getId(),
			_client.getId(),
			_item.toString(),
			getCost(),
			Math.round(getAccountingBalanceContribution(date)),
			getDate()
		);
		if (_isPaid) str = str + "|" + _paymentDate;
		return str;
	}
}
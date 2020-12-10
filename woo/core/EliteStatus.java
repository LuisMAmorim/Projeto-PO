package woo.core;

public class EliteStatus implements ClientStatus {
	private Client _client;
	private double _points;

	public EliteStatus(Client cl, double points) {
		_client = cl;
		_points = points;
	}

	public void updateStatus(int points) {
		_points += points;
	}

	private void lowerStatus() {
		_client.setStatus(new SelectionStatus(_client, _points - 0.75 * _points));
	}

	public double getCurrentCost(Sale sale, int delay) {
		double cost = sale.getCost();
		int n = sale.getDeadlineFactor();

		if (delay <= 0) cost *= 0.90;
		else if (delay <= n) cost *= 0.95;

		return cost;
	}

	public double pay(Sale sale) {
		int delay = sale.getPaymentDate() - sale.getDate();

		if (delay <= 0) updateStatus(sale.getCost() * 10);
		if (delay > 15) lowerStatus();

		return getCurrentCost(sale, delay);
	}
}
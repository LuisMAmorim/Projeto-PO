package woo.core;

public class SelectionStatus implements ClientStatus {
	private Client _client;
	private double _points;

	public SelectionStatus(Client cl, double points) {
		_client = cl;
		_points = points;
	}

	public void updateStatus(int points) {
		_points += points;

		if (_points > 25000) {
			ClientStatus newStatus = new EliteStatus(_client, _points);
			_client.setStatus(newStatus);
			newStatus.updateStatus(0);
		}
	}

	private void lowerStatus() {
		_client.setStatus(new NormalStatus(_client, _points - 0.90 * _points));
	}

	public double getCurrentCost(Sale sale, int delay) {
		double cost = sale.getCost();
		int n = sale.getDeadlineFactor();

		if (delay <= -n) cost *= 0.90;
		else if (delay <= -2) cost *= 0.95;
		else if (delay <= 1);
		else if (delay <= n) cost += (0.02 * cost) * delay;
		else if (delay <= 2*n) cost += (0.05 * cost) * delay;

		return cost;
	}

	public double pay(Sale sale) {
		int delay = sale.getPaymentDate() - sale.getDate();

		if (delay <= 0) updateStatus(sale.getCost() * 10);
		if (delay > 2) lowerStatus();

		return getCurrentCost(sale, delay);
	}
}
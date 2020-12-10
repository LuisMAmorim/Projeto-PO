package woo.core;

public class NormalStatus implements ClientStatus {
	private Client _client;
	private double _points;

	public NormalStatus(Client cl, double points) {
		_client = cl;
		_points = points;
	}

	public void updateStatus(int points) {
		_points += points;

		if (_points > 2000) {
			ClientStatus newStatus = new SelectionStatus(_client, _points);
			_client.setStatus(newStatus);
			newStatus.updateStatus(0);
		}
	}

	public double getCurrentCost(Sale sale, int delay) {
		double cost = sale.getCost();
		int n = sale.getDeadlineFactor();

		if (delay <= -n) cost *= 0.90;
		else if (delay <= 0);
		else if (delay <= n) cost += (0.05 * cost) * delay;
		else if (delay <= 2*n) cost += (0.10 * cost) * delay;

		return cost;
	}

	public double pay(Sale sale) {
		int delay = sale.getPaymentDate() - sale.getDate();

		if (delay <= 0) updateStatus(sale.getCost() * 10);

		return getCurrentCost(sale, delay);
	}
}
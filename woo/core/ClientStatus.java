package woo.core;

public interface ClientStatus {
	
	void updateStatus(int points);

	double getCurrentCost(Sale sale, int delay);

	double pay(Sale sale);
}
public class EliteStatus implements ClientStatus {
	private Client _client;
	private int _points;

	public NormalStatus(Client cl, int points) {
		_client = cl;
		_points = points;
	}

	void lowerStatus() {
		_client.setStatus(new SelectionStatus(_client, _points - 0.75 * _points));
	}	
}
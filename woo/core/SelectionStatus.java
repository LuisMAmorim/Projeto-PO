public class SelectionStatus implements ClientStatus {
	private Client _client;
	private int _points;

	public SelectionStatus(Client cl, int points) {
		_client = cl;
		_points = points;
	}

	void lowerStatus() {
		_client.setStatus(new NormalStatus(_client, _points - 0.90 * _points));
	}
}
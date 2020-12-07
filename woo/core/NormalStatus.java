public class NormalStatus implements ClientStatus {
	private Client _client;
	private int _points;

	public NormalStatus(Client cl, int points) {
		_client = cl;
		_points = points;
	}

	void lowerStatus() {
		return;
	}
}
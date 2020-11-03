package woo.core;

enum ClientStatus {
  NORMAL,
  ELITE,
  SELECTION
}

public class Client {
	private String _id;
	private String _name;
	private String _address;
	private ClientStatus _status;
	private int _points;
	private List<Notification> _notifications;
	private List<Sale> _sales;

	public Client(String id, String name, String address) {
		_id = id;
		_name = name;
		_adress = adress;
		_status = NORMAL;
		_points = 0;
		_notifications = new ArrayList<Notification>;
		_sales = new ArrayList<Sale>;
	}

	public String getId() {
		return _id;
	}

	public List<Transaction> getTransactions() {
		return _sales;
	}

	public String toString() {
		
	}
}
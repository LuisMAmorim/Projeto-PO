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

	public List<Transaction> getTransactions() {

	}
}
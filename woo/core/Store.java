package woo.core;

import java.io.Serializable;

import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

import woo.core.exception.BadEntryException;

import woo.core.exception.UnknownProductException;
import woo.core.exception.UnknownClientException;
import woo.core.exception.UnknownSupplierException;
import woo.core.exception.UnknownTransactionException;

import woo.core.exception.DuplicateKeyException;

import woo.core.exception.InvalidServiceLevelException;
import woo.core.exception.InvalidServiceQualityException;

import woo.core.exception.NotEnoughStockException;
import woo.core.exception.BadSupplierException;
import woo.core.exception.DisabledSupplierException;

/**
 * Class Store implements a store.
 */
public class Store implements Serializable, Observer {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202009192006L;

	private int _date;
	private List<Product> _products;
	private List<Client> _clients;
	private List<Supplier> _suppliers;
	private List<Transaction> _transactions;
	private List<Notification> _notifs;

	public Store() {
		_products = new LinkedList<Product>();
		_clients = new LinkedList<Client>();
		_suppliers = new LinkedList<Supplier>();
		_transactions = new ArrayList<Transaction>();
		_notifs = new ArrayList<Notification>();
		_date = 0;
	}

	/* Date */

	public int getDate() {
		return _date;
	}

	public void advanceDate(int numDays) {
		_date += numDays;
	}

	/* Balance */

	public double getAvailableBalance() {
		double balance = 0;

		for (Transaction t : _transactions)
			balance += t.getAvailableBalanceContribution(_date);

		return balance;

	}

	public double getAccountingBalance() {
		double balance = 0;

		for (Transaction t : _transactions)
			balance += t.getAccountingBalanceContribution(_date);

		return balance;
	}

	/* Products */

	public Product getProduct(String id) throws UnknownProductException {
		for (Product p : _products)
			if (p.getId().equals(id)) return p;

		throw new UnknownProductException();
	}

	public List<Product> getAllProducts() {
		return Collections.unmodifiableList(_products);
	}

	private void addProduct(Product product)
	throws DuplicateKeyException {
		int i = 0;

		for (Product p : _products) {
			if (product.compareTo(p) == 0)
				throw new DuplicateKeyException();
			if (product.compareTo(p) < 0)
				break;
			i++;
		}

		_products.add(i, product);
		for (Client cl : _clients) product.addObserver(cl);
		product.addObserver(this);
	}

	public void notify(Notification notif) {
		_notifs.add(notif);
	}

	public void registerBox(String id, String s, String supplierId, int price, int crit, int q)
	throws DuplicateKeyException, InvalidServiceLevelException , UnknownSupplierException {
		Box box = new Box(id, s, getSupplier(supplierId), price, crit, q);
		addProduct(box);
	}

	public void registerBook(String id, String title, String author, String isbn, String supplierId, int price, int crit, int q)
	throws DuplicateKeyException, UnknownSupplierException {
		Book book = new Book(id, title, author, isbn, getSupplier(supplierId), price, crit, q);
		addProduct(book);
	}

	public void registerContainer(String id, String s, String quality, String supplierId, int price, int crit, int q)
	throws DuplicateKeyException, InvalidServiceLevelException, InvalidServiceQualityException, UnknownSupplierException {
		Container container = new Container(id, s, quality, getSupplier(supplierId), price, crit, q);
		addProduct(container);
	}

	public void changePrice(String id, int price) throws UnknownProductException {
		getProduct(id).setPrice(price);
	}

	public List<Product> lookupProductsUnderTopPrice(int topPrice) {
		List<Product> results = new ArrayList<Product>();

		for (Product p : _products)
			if (p.getPrice() < topPrice) results.add(p);

		return results;
	}

	/* Clients */

	public Client getClient(String id) throws UnknownClientException {
		for (Client c : _clients)
			if (c.getId().equals(id)) return c;

		throw new UnknownClientException();
	}

	public List<Client> getAllClients() {
		return Collections.unmodifiableList(_clients);
	}

	public void registerClient(String id, String name, String address) throws DuplicateKeyException {
		int i = 0;
		Client client = new Client(id, name, address);

		for (Client cl : _clients) {
			if (client.compareTo(cl) == 0)
				throw new DuplicateKeyException();
			if (client.compareTo(cl) < 0)
				break;
			i++;
		}
		_clients.add(i, client);

		for (Product p : _products) p.addObserver(client);
	}

	public boolean toggleNotifications(String clientId, String productId)
	throws UnknownClientException, UnknownProductException {
		return getProduct(productId).toggleObserver(getClient(clientId));
	}

	/* Suppliers */

	public void registerSupplier(String id, String name, String address) throws DuplicateKeyException {
		int i = 0;
		Supplier supplier = new Supplier(id, name, address);

		for (Supplier s : _suppliers) {
			if (supplier.compareTo(s) == 0)
				throw new DuplicateKeyException();
			if (supplier.compareTo(s) < 0)
				break;
			i++;
		}
		_suppliers.add(i, supplier);
	}

	public List<Supplier> getAllSuppliers() {
		return _suppliers;
	}

	public Supplier getSupplier(String id) throws UnknownSupplierException {
		for (Supplier s : _suppliers)
			if (s.getId().equals(id)) return s;

		throw new UnknownSupplierException();
	}

	public boolean toggleTransactions(String id) throws UnknownSupplierException {
		return getSupplier(id).toggleTransactions();
	}

	/* Transactions */

	public Transaction getTransaction(int id) throws UnknownTransactionException {
		if (id < 0 || id >= _transactions.size())
			throw new UnknownTransactionException();
		return _transactions.get(id);
	}

	public void registerSale(String clientId, int date, String productId, int quantity)
	throws UnknownClientException, UnknownProductException, NotEnoughStockException  {
		Item item = new Item(getProduct(productId), quantity);
		Sale sale = new Sale(_transactions.size(), getClient(clientId), date, item);
		_transactions.add(sale);
	}

	public Order createOrder(String supplierId) throws UnknownSupplierException {
		Order order = new Order(_transactions.size(), _date, getSupplier(supplierId));
		return order;
	}

	public void registerOrder(Order order) throws DisabledSupplierException {
		order.finish();
		_transactions.add(order.getId(), order);
	}

	public void addItemToOrder(Order order, String productId, int quantity)
	throws UnknownProductException, BadSupplierException {
		Item item = new Item(getProduct(productId), quantity);
		order.addItem(item);
	}

	public void pay(int id) throws UnknownTransactionException {
		getTransaction(id).pay(_date);
	}

	/**
	* @param txtfile filename to be loaded.
	* @throws IOException
	* @throws BadEntryException
	*/
	void importFile(String txtfile) throws IOException, BadEntryException /* FIXME maybe other exceptions */ {
		MyParser parser = new MyParser(this);
		parser.parseFile(txtfile);
	}

}
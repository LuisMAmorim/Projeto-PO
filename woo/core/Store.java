package woo.core;

//FIXME import classes (cannot import from pt.tecnico or woo.app)
import java.io.Serializable;

import java.io.IOException;

import java.util.List;
import java.util.LinkedList;

import woo.core.exception.BadEntryException;

import woo.core.exception.UnknownProductException;
import woo.core.exception.UnknownClientException;
import woo.core.exception.UnknownSupplierException;
import woo.core.exception.DuplicateClientException;

import woo.core.exception.InvalidServiceLevelException;
import woo.core.exception.InvalidServiceQualityException;
import woo.core.exception.InvalidPriceException;

/**
 * Class Store implements a store.
 */
public class Store implements Serializable /* throws InvalidDateException */{

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202009192006L;

	// FIXME define attributes
	// FIXME define contructor(s)
	// FIXME define methods
	private int _date;
	private List<Product> _products;
	private List<Client> _clients;
	private List<Supplier> _suppliers;
	private List<Transaction> _transactions;

	public Store() {
		_products = new LinkedList<Product>();
		_clients = new LinkedList<Client>();
		_suppliers = new LinkedList<Supplier>();
		_date = 0;
	}

	/* Date */

	public int getDate() {
		return _date;
	}

	public void advanceDate(int numDays) {
		_date += numDays;
	}

	/* Products */

	public List<Product> getAllProducts() {
		return _products;
	}

	private void registerProduct(Product product) throws InvalidPriceException {
		int i = 0;

		for (Product p : _products) {
			if (product.compareTo(p) < 0)
				break;
			i++;
		}
		_products.add(i, product);
	}

	public void registerBox(String id, String s, String supplierId, int price, int crit, int q)
	throws InvalidServiceLevelException , UnknownSupplierException, InvalidPriceException {
		Box box = new Box(id, s, getSupplier(supplierId), price, crit, q);
		registerProduct(box);
	}

	public void registerBook(String id, String title, String author, String isbn, String supplierId, int price, int crit, int q)
	throws UnknownSupplierException, InvalidPriceException {
		Book book = new Book(id, title, author, isbn, getSupplier(supplierId), price, crit, q);
		registerProduct(book);
	}

	public void registerContainer(String id, String s, String quality, String supplierId, int price, int crit, int q)
	throws InvalidServiceLevelException, InvalidServiceQualityException, UnknownSupplierException, InvalidPriceException {
		Container container = new Container(id, s, quality, getSupplier(supplierId), price, crit, q);
		registerProduct(container);
	}

	public void changePrice(String productId, int price)
	throws UnknownProductException, InvalidPriceException {
		for (Product p : _products)
			if (p.getId().equals(productId)) p.setPrice(price);

		throw new UnknownProductException();
	}

	/* Clients */

	public Client getClient(String id) throws UnknownClientException {
		for (Client c : _clients)
			if (c.getId().equals(id)) return c;

		throw new UnknownClientException();
	}

	public List<Client> getAllClients() {
		return _clients;
	}

	public void registerClient(String id, String name, String address) throws DuplicateClientException {
		int i = 0;
		Client client = new Client(id, name, address);

		for (Client cl : _clients) {
			if (client.compareTo(cl) == 0)
				throw new DuplicateClientException();
			if (client.compareTo(cl) < 0)
				break;
			i++;
		}
		_clients.add(i, client);
	}	

	/* Suppliers */

	public void registerSupplier(String id, String name, String address) {
		int i = 0;
		Supplier supplier = new Supplier(id, name, address);

		for (Supplier s : _suppliers) {
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
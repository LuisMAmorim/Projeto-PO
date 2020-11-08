package woo.core;

//FIXME import classes (cannot import from pt.tecnico or woo.app)
import java.io.Serializable;

import java.io.IOException;

import woo.core.exception.BadEntryException;

import woo.core.Product;
import woo.core.Box;

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

	}

	/* Date */

	public int getDate() {
		return _date;
	}

	public void advanceDate(int numDays) {
		_date += numDays;
	}

	/* Products */

	private void registerProduct(Product product) {
		int i = 0;

		for (Supplier p : _products) {
			if (product.compareTo(s) < 0)
				break;
			i++;
		}
		_product.add(i, product);
	}

	public void registerBox(String id, ServiceLevel serviceLevel, String supplierId, int price, int crit, int q) {
		Box box = new Box(id, serviceLevel, getSupplier(supplierId), price, crit, q);
		registerProduct(box);
	}

	public void registerBook(String id, String title, String author, String isbn, String supplierId, int price, int crit, int q) {
		Book book = new Book(id, title, author, isbn, getSupplier(supplierId), price, crit, q);
		registerProduct(book);
	}

	public void registerContainer(String id, ServiceLevel serviceLevel, ServiceQuality serviceQuality, String supplierId, int price, int crit, int q) {
		Container container = new Container(id, serviceLevel, serviceQuality, getSupplier(supplierId), price, crit, q);
		registerProduct(container);
	}

	public List<Product> getAllProducts() {
		return _products;
	}

	/* Clients */

	public void registerClient(String id, String name, String address) {

	}

	public List<Client> getAllClients() {
		return _clients;
	}

	public Client getClient(String id) {
		for (Client c : _clients)
			if (c.getId().equals(id))
				return c;
		/* se nao existir? */
	}

	/* Suppliers */

	public void registerSupplier(String id, String name, String address) {
		int i = 0;
		Supplier supplier = new Supplier(id, name, adress);

		for (Supplier s : _suppliers) {
			if (supplier.compareTo(s) < 0)
				break;
			i++;
		}
		_suppliers.add(i, supplier);
	}

	public List<Supplier> getSuppliers() {
		return _suppliers;
	}

	public Supplier getSupplier(String id) {
		for (Client s : _suppliers)
			if (s.getId().equals(id))
				return s;
	}

	/* ... */


	/**
	* @param txtfile filename to be loaded.
	* @throws IOException
	* @throws BadEntryException
	*/
	void importFile(String txtfile) throws IOException, BadEntryException /* FIXME maybe other exceptions */ {
	//FIXME implement method
	}

}
package woo.core;

//FIXME import classes (cannot import from pt.tecnico or woo.app)
import java.io.Serializable;

import java.io.IOException;

import woo.core.exception.BadEntryException;

import woo.core.exception.AdvanceDateException; /* funciona??? */

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
	private List<Transactions> _transactions;

	public Store() {

	}

	/* Date */

	public int getDate() {
		return _date;
	}

	public void advanceDate(int numDays) throws AdvanceDateException {
		_date += numDays;
	}

	/* Products */

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
			if (c.getId() == id)
				return c;
		/* se nao existir? */
	}

	/* Suppliers */

	public void registerSupplier(String id, String name, String address) {
		int i = 0;
		Supplier supplier = new Supplier(id, name, adress);

		for (Supplier s : _suppliers) {
			if (supplier.compareTo(s) < 0) {
				_suppliers.add(i, supplier);
				break;
			}
			i++;
		} 
	}

	public List<Supplier> getSuppliers() {
		return _suppliers;
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
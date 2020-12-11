package woo.core;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.DeflaterOutputStream;
import java.io.FileOutputStream;
import java.util.List;

import woo.core.exception.UnavailableFileException;
import woo.core.exception.MissingFileAssociationException;
import woo.core.exception.ImportFileException;
import woo.core.exception.BadEntryException;

import woo.core.exception.UnknownProductException;
import woo.core.exception.UnknownClientException;
import woo.core.exception.UnknownSupplierException;
import woo.core.exception.UnknownTransactionException;

import woo.core.exception.DuplicateKeyException;

import woo.core.exception.InvalidServiceLevelException;
import woo.core.exception.InvalidServiceQualityException;

import woo.core.exception.BadSupplierException;
import woo.core.exception.DisabledSupplierException;
import woo.core.exception.NotEnoughStockException;

/**
 * StoreManager: façade for the core classes.
 */
public class StoreManager {

	/** Current filename. */
	private String _filename = "";

	/** The actual store. */
	private Store _store;

	public StoreManager() {
		_store = new Store();
	}

	/* DATE */

	/**
	 * Gets the system's current date.
	 * 
	 * @return current date
	 */
	public int getDate() {
    	return _store.getDate();
  	}

  	/**
	 * Advances the system's current date by the number of days specified.
	 * 
	 * @param days number of days to advance
	 */
  	public void advanceDate(int days) {
		_store.advanceDate(days);
	}

	/* BALANCE */

	public double getAvailableBalance() {
		return _store.getAvailableBalance();

	}

	public double getAccountingBalance() {
		return _store.getAccountingBalance();
	}

	/* Products */

	public Product getProduct(String id) throws UnknownProductException {
		return _store.getProduct(id);
	}

	public List<Product> getAllProducts() {
		return _store.getAllProducts();
	}

	public void changePrice(String productId, int price)
	throws UnknownProductException {
		_store.changePrice(productId, price);
	}

	public void registerBox(String id, String s, String supplierId, int price, int crit, int q)
	throws DuplicateKeyException, InvalidServiceLevelException , UnknownSupplierException {
		_store.registerBox(id, s, supplierId, price, crit, q);
	}

	public void registerBook(String id, String title, String author, String isbn, String supplierId, int price, int crit, int q)
	throws DuplicateKeyException, UnknownSupplierException {
		_store.registerBook(id, title, author, isbn, supplierId, price, crit, q);
	}

	public void registerContainer(String id, String s, String quality, String supplierId, int price, int crit, int q)
	throws DuplicateKeyException, InvalidServiceLevelException, InvalidServiceQualityException, UnknownSupplierException {
		_store.registerContainer(id, s, quality, supplierId, price, crit, q);
	}

	public List<Product> lookupProductsUnderTopPrice(int topPrice) {
		return _store.lookupProductsUnderTopPrice(topPrice);
	}

	/* Clients */

	public Client getClient(String id) throws UnknownClientException {
		return _store.getClient(id);
	}

	public List<Client> getAllClients() {
		return _store.getAllClients();
	}

	public void registerClient(String id, String name, String address) throws DuplicateKeyException {
		_store.registerClient(id, name, address);
	}

	public boolean toggleNotifications(String clientId, String productId)
	throws UnknownClientException, UnknownProductException {
		return _store.toggleNotifications(clientId, productId);
	}
	
	/* Suppliers */

	public Supplier getSupplier(String id) throws UnknownSupplierException {
		return _store.getSupplier(id);
	}

	public List<Supplier> getSuppliers() {
		return _store.getAllSuppliers();
	}

	public void registerSupplier(String id, String name, String address) throws DuplicateKeyException {
		_store.registerSupplier(id, name, address);
	}

	public boolean toggleTransactions(String id) throws UnknownSupplierException {
		return _store.toggleTransactions(id);
	}

	/* Transactions */

	public Transaction getTransaction(int id) throws UnknownTransactionException {
		return _store.getTransaction(id);
	}

	public void registerSale(String clientId, int date, String productId, int quantity)
	throws UnknownClientException, UnknownProductException, NotEnoughStockException {
		_store.registerSale(clientId, date, productId, quantity);
	}

	public Order createOrder(String supplierId) throws UnknownSupplierException {
		return _store.createOrder(supplierId);
	}

	public void addItemToOrder(Order order, String productId, int quantity)
	throws UnknownProductException, BadSupplierException {
		_store.addItemToOrder(order, productId, quantity);
	}

	public void registerOrder(Order order) throws DisabledSupplierException {
		_store.registerOrder(order);
	}

	public void pay(int id) throws UnknownTransactionException {
		_store.pay(id);
	}


	/**
	* @throws IOException
	* @throws FileNotFoundException
	* @throws MissingFileAssociationException
	*/
	public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
		ObjectOutputStream obOut = null;

		if (_filename == "") throw new MissingFileAssociationException();

		try {
			FileOutputStream fpout = new FileOutputStream(_filename);
			DeflaterOutputStream dOut = new DeflaterOutputStream(fpout);
			obOut = new ObjectOutputStream(dOut);
			obOut.writeObject(_store);
		} finally {
			if (obOut!= null)
				obOut.close();
		}
	}

	/**
	* @param filename
	* @throws MissingFileAssociationException
	* @throws IOException
	* @throws FileNotFoundException
	*/
	public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
		_filename = filename;
		save();
	}

	/**
	* @param filename
	* @throws UnavailableFileException
	*/
	public void load(String filename) throws UnavailableFileException {
		_filename = filename;

		try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(filename))) {
			_store = (Store)objIn.readObject();
		}
		catch (IOException | ClassNotFoundException x) {
			throw new UnavailableFileException(filename);
		}
	}

	/**
	* @param textfile
	* @throws ImportFileException
	*/
	public void importFile(String textfile) throws ImportFileException {
		try {
		  _store.importFile(textfile);
		} catch (IOException | BadEntryException /* FIXME maybe other exceptions */ e) {
		  throw new ImportFileException(textfile);
		}
	}

}
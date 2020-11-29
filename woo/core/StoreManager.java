package woo.core;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.List;
import java.util.LinkedList;

import woo.core.exception.UnavailableFileException;
import woo.core.exception.MissingFileAssociationException;
import woo.core.exception.ImportFileException;
import woo.core.exception.BadEntryException;

import woo.core.exception.UnknownProductException;
import woo.core.exception.UnknownClientException;
import woo.core.exception.UnknownSupplierException;
import woo.core.exception.DuplicateKeyException;

import woo.core.exception.InvalidServiceLevelException;
import woo.core.exception.InvalidServiceQualityException;
import woo.core.exception.InvalidPriceException;

/**
 * StoreManager: façade for the core classes.
 */
public class StoreManager {

	/** Current filename. */
	private String _filename = "";

	/** The actual store. */
	private Store _store = new Store();

	//FIXME define other attributes
	//FIXME define constructor(s)
	//FIXME define other methods
	public StoreManager() {

	}

	/* Date */

	public int getDate() {
    	return _store.getDate();
  	}

  	public void advanceDate(int numDays) {
		_store.advanceDate(numDays);
	}

	/* Products */

	public List<Product> getAllProducts() {
		return _store.getAllProducts();
	}

	public void changePrice(String productId, int price)
	throws UnknownProductException, InvalidPriceException {
		_store.changePrice(productId, price);
	}

	public void registerBox(String id, String s, String supplierId, int price, int crit, int q)
	throws DuplicateKeyException, InvalidServiceLevelException , UnknownSupplierException, InvalidPriceException {
		_store.registerBox(id, s, supplierId, price, crit, q);
	}

	public void registerBook(String id, String title, String author, String isbn, String supplierId, int price, int crit, int q)
	throws DuplicateKeyException, UnknownSupplierException, InvalidPriceException {
		_store.registerBook(id, title, author, isbn, supplierId, price, crit, q);
	}

	public void registerContainer(String id, String s, String quality, String supplierId, int price, int crit, int q)
	throws DuplicateKeyException, InvalidServiceLevelException, InvalidServiceQualityException, UnknownSupplierException, InvalidPriceException {
		_store.registerContainer(id, s, quality, supplierId, price, crit, q);
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
	
	/* Suppliers */

	public List<Supplier> getSuppliers() {
		return _store.getAllSuppliers();
	}

	

	/* ... */

	/**
	* @throws IOException
	* @throws FileNotFoundException
	* @throws MissingFileAssociationException
	*/
	public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
	//FIXME implement serialization method
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
		//FIXME implement serialization method
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
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
import woo.core.exception.DuplicateKeyException;

import woo.core.exception.InvalidServiceLevelException;
import woo.core.exception.InvalidServiceQualityException;

/**
 * StoreManager: façade for the core classes.
 */
public class StoreManager {

	/** Current filename. */
	private String _filename = "";

	/** The actual store. */
	private Store _store = new Store();

	//FIXME define constructor(s)
	public StoreManager() {

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

	/* Products */

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

	/*public Client getClient(String id) throws UnknownClientException {
		return _store.getClient(id);
	}*/

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

	public void registerSupplier(String id, String name, String address) throws DuplicateKeyException {
		_store.registerSupplier(id, name, address);
	}

	/* ... */
	
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
	public void load(String filename) throws UnavailableFileException, FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream objIn = null;
		_filename = filename;

		try {
			objIn = new ObjectInputStream(new FileInputStream(filename));
			_store = (Store)objIn.readObject();
		} finally {
			if (objIn!= null)
				objIn.close();
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
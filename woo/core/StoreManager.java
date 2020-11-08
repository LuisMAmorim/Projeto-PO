package woo.core;

import java.io.IOException;
import java.io.FileNotFoundException;

import woo.core.exception.UnavailableFileException;
import woo.core.exception.MissingFileAssociationException;
import woo.core.exception.ImportFileException;
import woo.core.exception.BadEntryException;

import woo.core.exception.AdvanceDateException;

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

	public void registerClient(String id, String name, String address) {
		_store.registerClient(id, name, address);
	}

	public List<Client> getAllClients() {
		return _store.getAllClients();
	}

	public Client getClient(String id) {
		return _store.getClient(id);
	}

	public void updatePrice(String productId, int price) {
		
	}

	public int getDate() {
    	return _store.getDate();
  	}

  	public void advanceDate(int numDays) {
		_store.advanceDate(numDays);
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